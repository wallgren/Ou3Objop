package simulation;

import java.util.ArrayList;

/**
 * Created by grupp 8 on 2017-05-09.
 * Minimal working example of the simulation
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
        config.setTimeEachRequestsIsSent(400);
        Grid g = new Grid(config);

        for (int i = 0; i < 10000; i++) {
            g.eventHappening();
            g.updateNodes();
        }
    }
}
