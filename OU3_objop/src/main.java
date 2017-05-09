import java.util.ArrayList;

/**
 * Created by dv16mhg on 2017-05-09.
 */
public class main {
    public static void main(String[] args) throws Exception{
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                nodes.add(new Node(new Position(i, j)));
            }
        }
        Grid g = new Grid(nodes, 0.5, 0.01, 15, 50, 45);
        for (int i = 0; i < 10000; i++) {
            g.updateNodes();
            g.eventHappening();
        }
    }
}
