package tests;
import simulation.*;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.*;

/**
 * Test - Junit test that makes sure that the Node class works as intended
 * Created by grupp 8 on 2017-05-05.
 */
public class NodeTest {

    /**
     * Makes sure a node can be created
     */
    @Test
    public void canCreateNode(){
        new Node(new Position(0,0));
    }

    /**
     * makes sure a created node is on its expecte position
     */
    @Test
    public void shouldHaveExpectedPos(){
        assertTrue((new Node(new Position(10,10))).getPos().equals(new Position(10,10)));
    }

    /**
     * makes sure the method addEvent adds an event, and makes sure its reachable by requests
     */
    @Test
    public void addEventShouldAddEvent(){
        Node a= new Node(new Position(0,0));
        a.addEvent(new Event(a.getPos(), 1,10));
        int i=a.returnTimeIfEventExists(1);
        assertEquals(i, 10);
    }

    /**
     * makes sure nodes handles neighbours correctly
     */
    @Test
    public void canHandleNeighbours(){
        Node a = new Node(new Position(0,0));
        Node b = new Node(new Position(0,10));
        Node c = new Node(new Position(0,20));

        assertEquals(a.getNeighbours().size(), 0);

        a.addNeighbour(b);
        assertEquals(a.getNeighbours().get(0), b);
        assertEquals(a.getNeighbours().size(), 1);
        a.addNeighbour(c);
        assertEquals(a.getNeighbours().size(), 2);
        assertEquals(a.getNeighbours().get(1), c);
    }

    /**
     * makes sure a node can create a request that is reachable and working.
     * Requires that Request has a working constructor
     */
    @Test
    public void createRequestCreatesRequest(){
        Request r=(new Node(new Position(0,0))).createRequest(1, 10);
        assertEquals(r.getEventId(), 1);
    }

    /**
     * Makes sure that the update method works as intended, using a request
     */
    @Test
    public void updateUpdatesNodeCorrectly(){
        Node a= new Node(new Position(0,0));
        Node b= new Node(new Position(0,10));
        a.addNeighbour(b);
        b.addNeighbour(a);

        //This should do nothing, as nothing is put in the queue
        a.update();
        b.update();

        a.createRequest(1, 10); //For this to work we need to test simulation.Request
        assertEquals(a.numberOfElementsInMessageQueue(), 1);
        a.update();
        assertEquals(a.numberOfElementsInMessageQueue(), 0);
        assertEquals(b.numberOfElementsInMessageQueue(), 1);
        b.update();
        assertEquals(a.numberOfElementsInMessageQueue(), 1);
        assertEquals(b.numberOfElementsInMessageQueue(), 0);
    }

    /**
     * Makes sure requests, nodes and agents works together in a correct manner
     */
    @Test
    public void requestsCanDoTheirJob() {
        Node a = new Node(new Position (0, 0));
        Node b = new Node(new Position(0, 10));
        a.addNeighbour(b);
        b.addNeighbour(a);
        b.addEvent(new Event(b.getPos(), 1, 1));
        Agent agent = new Agent(b, 3);
        b.update();
        a.update();

        Request r=a.createRequest(1, 5);
        a.update();
        b.update();
        a.update();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        a.update();
        assertEquals(out.toString(), "Position: (0, 10) time: 1 event id: 1\n");
        assertTrue(r.hasReturned());
    }

    /**
     * Makes sure that compareTable method works by creating a node, a routingTable, comparing them
     * editing them, then comparing them again
     */
    @Test
    public void shouldUpdateTableCorrectly(){
        HashMap<Integer, Guide> routingTable=new HashMap<>();
        Node node = new Node(new Position(0,0));

        routingTable.put(1, new Guide(10,3));
        routingTable.put(2, new Guide(5,1));
        routingTable.put(3, new Guide(10,5));
        routingTable.put(4, new Guide(200,1));

        node.compareTable(routingTable);

        routingTable.get(1).setStepsAndDirection(11,1);
        routingTable.get(2).setStepsAndDirection(4,13);
        routingTable.get(3).setStepsAndDirection(11,1);
        routingTable.get(4).setStepsAndDirection(5,2);

        node.compareTable(routingTable);

        assertTrue(routingTable.get(1).equals(new Guide(10,3)));
        assertTrue(routingTable.get(2).equals(new Guide(4,13)));
        assertTrue(routingTable.get(3).equals(new Guide(10,5)));
        assertTrue(routingTable.get(4).equals(new Guide(5,2)));
    }

    /**
     * Makes sure the messageQueues works as intended
     */
    @Test
    public void sendingTwoRequestsToSameNodeShouldPutOneInQueue(){
        Node a=new Node(new Position(0,0));
        Node b=new Node(new Position(0,1));
        Node c=new Node(new Position(0,2));
        Node d=new Node(new Position(0,3));

        a.addNeighbour(b);
        c.addNeighbour(b);
        b.addNeighbour(d);

        a.createRequest(1, 10);
        c.createRequest(2, 10);
        a.update();
        c.update();

        assertEquals(b.numberOfElementsInMessageQueue(), 1);
        assertEquals(a.numberOfElementsInMessageQueue(), 0);
        assertEquals(c.numberOfElementsInMessageQueue(), 1);

        b.update();
        c.update();

        assertEquals(b.numberOfElementsInMessageQueue(), 1);
        assertEquals(d.numberOfElementsInMessageQueue(), 1);
        b.createRequest(3, 10);
        assertEquals(b.numberOfElementsInMessageQueue(), 2);
        b.update();
        assertEquals(d.numberOfElementsInMessageQueue(), 1);
        assertEquals(b.numberOfElementsInMessageQueue(), 2);
    }
}
