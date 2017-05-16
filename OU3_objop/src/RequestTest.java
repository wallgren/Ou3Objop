import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by oi12mnd on 2017-05-16.
 */
public class RequestTest {

    private Grid testGrid;

    @Before
    public void createTestGrid(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(new Position(0,0)));
        nodes.add(new Node(new Position(0,1)));
        nodes.add(new Node(new Position(1,0)));
        nodes.add(new Node(new Position(1,1)));
        testGrid = new Grid(nodes, 0, 1, 5, 0, 1);
    }

    @Test
    public void messageIsPrinted() throws Exception{
        int t = 0;
        while (t < 401) {
            testGrid.updateNodes();
            testGrid.eventHappening();
            t++;
        }
    }
}
