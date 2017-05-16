import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dv16mhg on 2017-05-09.
 */
public class main {
    public static void main(String[] args) throws Exception {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 500; i+=10) {
            for (int j = 0; j < 500; j += 10) {
                nodes.add(new Node(new Position(j,i)));
            }
        }
        Configuration config = new Configuration();
        config.setNodes(nodes);
        config.setAgentProbability(0.5);
        config.setEventProbability(0.0001);
        config.setComlength(15);
        config.setMaxJumpsAgent(50);
        config.setMaxJumpsRequest(45);

        Grid g = new Grid(config, false);
        long timeBefore = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            g.updateNodes();
            g.eventHappening();
            if(i % 1000 == 0)
              System.out.println(i);
        }
        System.out.printf("total time for compare table: %.6f \n", (double)g.getTotalTime()/Math.pow(10,9));
        long timeAfter = System.nanoTime();
        System.out.printf("Time: %.6f seconds.", (double)(timeAfter - timeBefore)/Math.pow(10, 9));
    }
}