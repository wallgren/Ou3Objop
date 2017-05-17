/**
 * Created by dv16mhg on 2017-05-17.
 */

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void nodeShouldHaveCorrectAmountOfNeighbours() {
        Configuration config = new Configuration();
        config.setComlength(15);
        ArrayList<Node> nodes = new ArrayList<>();
        int size = 500;
        for (int y = 0; y < size; y += 10) {
            for (int x = 0; x < size; x += 10) {
                nodes.add(new Node(new Position(x, y)));
            }
        }
        config.setNodes(nodes);
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


    @Test (expected = IllegalStateException.class)
    public void shouldHaveZeroNeighbours(){
        Configuration config = new Configuration();
        config.setComlength(0);
        ArrayList<Node> nodes = new ArrayList<>();
        int size = 500;
        for (int y = 0; y < size; y += 10) {
            for (int x = 0; x < size; x += 10) {
                nodes.add(new Node(new Position(x, y)));
            }
        }
        config.setNodes(nodes);
        new Grid(config);
    }




}
