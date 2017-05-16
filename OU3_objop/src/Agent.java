import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by oi12mnd on 2017-05-05.
 */
public class Agent extends Message {
    private HashMap<Integer, Guide> routingTable = new HashMap<>();

    /** Sets the maximum number of jumps of an agent. Adds the starting node to visited nodes.
     * @param startNode The node where the agent is created.
     * @param MAXJUMPS The number of */
    public Agent(Node startNode, int MAXJUMPS){
        super(startNode,MAXJUMPS);
    }

    /**
     * Method - Returns the nodes that the agent can walk to
     * @return ArrayList<Node>
     */
    /* Check which nodes are valid moves */
    private ArrayList<Node> getMovableNeighbours() {
        ArrayList<Node> movableNeighbours = new ArrayList<>();
        for (Node node : currNode.getNeighbours()) {
            if (!path.contains(node)) {
                movableNeighbours.add(node);
            }
        }
        return movableNeighbours;
    }

    /** Synchronises the agent's information with a node. */
    public void update(){
        if (jumps==MAXJUMPS){
            currNode.removeFirstElement();
        }
        else {
            if(jumps!=0)
                updateOwnTable();
            currNode.compareTable(routingTable);
            nextNode=findNextNode();
        }
    }

    /**
     * Updates the agents table of events, increasing the distance by 1 jump and adding direction for the latest node.
     */
    public void updateOwnTable(){
        for(int key: routingTable.keySet())
            routingTable.get(key).setStepsAndDirection(routingTable.get(key).getSteps()+1,
                    currNode.getNeighbours().indexOf(previousNode));
    }

    /**
     * Finds a new node for the agent to move to.
     * If all the neighbours already have been visited, one of those will randomly chosen.
     * Otherwise one of the neighbours that was not already visited will be chosen, also by random.
     * @return The next node for the agent to move to.
     */
    public Node findNextNode(){
        Node nextNode;
        ArrayList<Node> movableN=getMovableNeighbours();
        ArrayList<Node> allN=currNode.getNeighbours();


        if(movableN.isEmpty()) {
            nextNode = allN.get(random.nextInt(allN.size()));
        }
        else {
            nextNode=movableN.get(random.nextInt(movableN.size()));
        }
        return nextNode;
    }
}