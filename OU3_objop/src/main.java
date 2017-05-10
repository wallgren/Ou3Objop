import java.util.ArrayList;

/**
 * Created by dv16mhg on 2017-05-09.
 */
public class main {
    public static void main(String[] args) throws Exception {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 40; i += 10) {
            for (int j = 0; j < 40; j += 10) {
                nodes.add(new Node(new Position(i, j)));
            }
        }
        Grid g = new Grid(nodes, 0.5, 0.01, 15, 50, 45);

        for (int i = 0; i < 1000; i++) {
            g.updateNodes();
            g.eventHappening();
        }
    }
}
