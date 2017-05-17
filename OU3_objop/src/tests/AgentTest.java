package tests;

import simulation.*;
import org.junit.Test;
import static junit.framework.TestCase.*;


/**
 * Created by c16gwn on 2017-05-15.
 */
public class AgentTest {

    @Test
    public void canCreateAgent(){
        new Agent(new Node(new Position(0,0)),50);
    }

    @Test
    public void agentCanMove(){
        Node n1= new Node(new Position(0,0));
        Node n2= new Node(new Position(0,20));
        Node n3= new Node(new Position(0,30));
        n1.addNeighbour(n2);
        n1.addNeighbour(n3);

        Agent a=new Agent(n1, 10);
        n1.addMessageToQueue(a);

        assertEquals(n1.numberOfElementsInMessageQueue(), 1);
        n1.update();
        assertEquals(n1.numberOfElementsInMessageQueue(), 0);

        if(n2.numberOfElementsInMessageQueue()==0){
            assertEquals(n3.numberOfElementsInMessageQueue(), 1);
        }else{
            assertEquals(n2.numberOfElementsInMessageQueue(), 1);
        }
    }

    @Test
    public void canCompareTable(){
        Node n1= new Node(new Position(0,0));
        Node n2= new Node(new Position(0,10));
        n1.addNeighbour(n2);
        n2.addNeighbour(n1);

        n1.addEvent(new Event(n1.getPos(), 1,1));

        Agent a=new Agent(n1, 10);
        n1.addMessageToQueue(a);
        n1.update();
        assertEquals(n1.getEventInfo(1), new Guide(0, 0));
        n2.update();
        assertEquals(n2.getEventInfo(1), new Guide(1, 0));
    }

    @Test
    public void shouldPrioritizeNonVisitedNodes(){
        //To eliminate, as much as possible, the dependency of random we run the test many times.
        for(int i=0; i<1000; i++) {
            Node n1 = new Node(new Position(0, 0));
            Node n2 = new Node(new Position(0, 0));
            Node n3 = new Node(new Position(0, 0));
            Node n4 = new Node(new Position(0, 0));
            Node n5 = new Node(new Position(0, 0));

            n1.addNeighbour(n2);
            n2.addNeighbour(n3);
            n3.addNeighbour(n4);

            n4.addNeighbour(n1);
            n4.addNeighbour(n2);
            n4.addNeighbour(n3);
            n4.addNeighbour(n5);

            n1.addMessageToQueue(new Agent(n1, 10));
            n1.update();
            n2.update();
            n3.update();
            n4.update();

            assertEquals(n5.numberOfElementsInMessageQueue(), 1);
        }
    }

    @Test
    public void currNodeShouldChange(){
        Node n1=new Node(new Position(0,0));
        Node n2=new Node(new Position(0,0));
        n1.addNeighbour(n2);
        Agent a = new Agent(n1, 10);
        n1.addMessageToQueue(a);

        assertEquals(a.getCurrNodeForTesting(),n1);
        n1.update();
        assertEquals(a.getCurrNodeForTesting(), n2);
    }

}
