import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by c16gwn on 2017-05-05.
 */
public class Node {
    private ArrayList<Node> neighbours;
    private HashMap<Integer, ArrayList<Integer>> routingTable;
    private Position pos;
    //private ArrayList<Agent> agentQueue
    private HashMap<Integer, Event> eventsHere;
    private int timeSinceRequest;
    //private Request currentRequest;

    public Node(Position p){
        routingTable=new HashMap<>();
        neighbours=new ArrayList<>();
        pos=p;

    }





}
