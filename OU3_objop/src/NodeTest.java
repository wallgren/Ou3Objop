import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.*;

/**
 * Created by c16gwn on 2017-05-15.
 */
public class NodeTest {

    @Test
    public void canCreateNode(){
        new Node(new Position(0,0));
    }

    @Test
    public void shouldHaveExpectedPos(){
        assertTrue((new Node(new Position(10,10))).getPos().equals(new Position(10,10)));
    }

    @Test
    public void addEventShouldAddEvent(){
        Node a= new Node(new Position(0,0));
        a.addEvent(new Event(a.getPos(), 1,10));
        int i=a.returnTimeIfEventExists(1);
        assertEquals(i, 10);
    }

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

    @Test
    public void canCreateRequest(){
        Node a= new Node(new Position(0,0));
        a.createRequest(1, 10);
    }

    @Test
    public void createRequestCreatesRequest(){
        Request r=(new Node(new Position(0,0))).createRequest(1, 10);
        assertEquals(r.getEventId(), 1);
    }

    @Test
    public void updateUpdatesNodeCorrectly(){
        Node a= new Node(new Position(0,0));
        Node b= new Node(new Position(0,10));
        a.addNeighbour(b);
        b.addNeighbour(a);

        //This should do nothing, as nothing is put in the queue
        a.update();
        b.update();

        a.createRequest(1, 10); //For this to work we need to test Request
        assertEquals(a.numberOfElementsInMessageQueue(), 1);
        a.update();
        assertEquals(a.numberOfElementsInMessageQueue(), 0);
        assertEquals(b.numberOfElementsInMessageQueue(), 1);
        b.update();
        assertEquals(a.numberOfElementsInMessageQueue(), 1);
        assertEquals(b.numberOfElementsInMessageQueue(), 0);
    }

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

        a.createRequest(1, 5);
        a.update();
        b.update();
        a.update();
        a.update();
    }

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



}
