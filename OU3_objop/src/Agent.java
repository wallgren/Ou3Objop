import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by oi12mnd on 2017-05-05.
 */
public class Agent {

    private HashMap routingTable = new HashMap<Integer, ArrayList<Integer>>();
    private Node currNode, nextNode;
    private int jumps = 0, MAXJUMPS;
    private ArrayList<Node> visitedNodes = new ArrayList<Node>(); // index 0 - distance, 1 - direction.
    private Random random = new Random();

    /** Sets the maximum number of jumps of an agent. Adds the starting node to visited nodes.
     * @param startNode The node where the agent is created.
     * @param maxJumps The number of */
    public Agent(Node startNode, int maxJumps){
        MAXJUMPS = maxJumps;
        visitedNodes.add(startNode);
        currNode = startNode;
    }

    /** Check if the maximum number of moves is reached.
     * @return True if the agent can move, false otherwise. */
    public boolean canMove(){
        return jumps < MAXJUMPS;
    }

    /* Check which nodes are valid moves */
    private ArrayList<Node> getMovableNeighbours() {
        ArrayList<Node> movableNeighbours = new ArrayList<Node>();
        for (Node node : currNode.getNeighbours()) {
            if (!visitedNodes.contains(node)) {
                movableNeighbours.add(node);
            }
        }
        return movableNeighbours;
    }

    /* Pick a random node from list */
    private Node getRandomNode(ArrayList<Node> nodes){
        int nextNodeIndex = random.nextInt(nodes.size());
        return nodes.get(nextNodeIndex);
    }

    /** Moves the agent one step. */
    public void move(){
        ArrayList<Node> movableNeighbours = getMovableNeighbours();

        // No unvisited neighbours: jump to random visited node.
        // Else: jump to random unvisited node.
        if (movableNeighbours.isEmpty()) {
            nextNode = getRandomNode(currNode.getNeighbours());
        } else {
            nextNode = getRandomNode(movableNeighbours);
        }
        currNode = nextNode;
        jumps++;
    }

    /** Synchronises the agent's information with a node. */
    public void update(){

    }
}
