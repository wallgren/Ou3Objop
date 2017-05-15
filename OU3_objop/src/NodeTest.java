import org.junit.Test;
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
    public void canHandleNeighbours(){
        Node a = new Node(new Position(0,0));
        Node b = new Node(new Position(0,10));
        Node c = new Node(new Position(0,20));

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
    public void requestsCanDoTheirJob() {
        Node a = new Node(new Position (0, 0));
        Node b = new Node(new Position(0, 10));
        a.addNeighbour(b);
        b.addNeighbour(a);
        b.addEvent(new Event(b.getPos(), 1, 1));
        Agent agent = new Agent(b, 3);
        b.update();
        a.update();
        b.update();

        a.createRequest(1, 5);
        a.update();
        b.update();
        a.update();
        b.update();
        a.update();
    }




}
