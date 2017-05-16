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
        Configuration config = new Configuration();
        config.setNodes(nodes);
        config.setAgentProbability(0);
        config.setEventProbability(1);
        config.setComlength(5);
        config.setMaxJumpsAgent(0);
        config.setMaxJumpsRequest(1);
        testGrid = new Grid(config);
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
