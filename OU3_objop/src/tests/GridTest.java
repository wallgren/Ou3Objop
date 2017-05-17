package tests; /**
 * Created by dv16mhg on 2017-05-17.
 */

import simulation.Configuration;
import simulation.Grid;
import simulation.Node;
import simulation.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GridTest {

    private Configuration config;
    private ArrayList<Node> nodes;
    private int size;

    /**
     * Initializer of the standard test grid
     */
    @Before
    public void init(){
        nodes = new ArrayList<>();
        config = new Configuration();
        for (int i = 0; i < 500; i+=10) {
            for (int j = 0; j < 500; j += 10) {
                nodes.add(new Node(new Position(j,i)));
            }
        }
        config.setNodes(nodes);
        config.setAgentProbability(0.5);
        config.setEventProbability(0.0001);
        config.setComlength(15);
        config.setMaxJumpsAgent(50);
        config.setMaxJumpsRequest(45);
        config.setTimeEachRequestsIsSent(400);
        size = 500;
    }



    /**
     * Test - tests if the nodes have expected neighbours
     */
    @Test
    public void nodeShouldHaveCorrectAmountOfNeighbours(){
        new Grid(config);
        int next = 0;
        for (int y = 0; y < size; y += 10) {
            for (int x = 0; x < size; x += 10) {
                Node node = nodes.get(next);
                next++;
                if((y == 0 || y == size - 10) && (x == 0 || x == size - 10))
                    assertEquals(3, node.getNeighbours().size());
                else if(y == 0 || x == 0 || y == size - 10 || x == size - 10)
                    assertEquals(5, node.getNeighbours().size());
                else
                    assertEquals(8, node.getNeighbours().size());

            }
        }
    }

    /**
     * Test - tests if when node have zero neighbours will throw an exception
     */
    @Test (expected = IllegalStateException.class)
    public void shouldThrowIfZeroNeighbours(){
        config.setComlength(0);
        config.setNodes(nodes);
        new Grid(config);
    }

    /**
     * Test - tests if four request is generated after 400 time steps
     */
    @Test
    public void fourRequestShouldBeSentOut(){
        config.setAgentProbability(0);
        Grid grid = new Grid(config);
        int counter = 0;
        for (int i = 0; i < 401; i++) {
            grid.eventHappening();
            grid.updateNodes();
            if(i == 400){
                for(Node node : nodes){
                    try{
                        node.removeFirstElement();
                    }
                    catch(Exception e){
                        counter--;
                    }
                    counter++;
                }
                assertEquals(4, counter);
            }
        }
    }

    /**
     * Test - tests if event spawned
     */
    @Test
    public void eventShouldSpawn(){
        config.setEventProbability(1);
        Grid grid = new Grid(config);
        grid.eventHappening();
        assertEquals(1, (int)nodes.get(0).returnTimeIfEventExists(1));
    }
}
