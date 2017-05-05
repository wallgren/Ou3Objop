import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class Node {
    private ArrayList<Node> neighbours;
    private HashMap<Integer, ArrayList<Integer>> routingTable;
    private Position pos;
    private ArrayList<Agent> agentQueue;
    private HashMap<Integer, Event> eventsHere;
    private int timeSinceRequest;
    private Request currentRequest;

    public Node(Position p){
        routingTable=new HashMap<>();
        neighbours=new ArrayList<>();
        pos=p;
        timeSinceRequest=0;
        eventsHere=new HashMap<>();
    }

    /**
     * Description: Compare two routingTables with each other, update with the shortest path to the event
     * @param agentRT : the routingTable to compare.
     */
    public void compareTable(HashMap<Integer,ArrayList<Integer>> agentRT){
        for(int key: routingTable.keySet()){
            if(agentRT.containsKey(key)){
                if(agentRT.get(key).get(0)<routingTable.get(key).get(0)){
                    //If agent has a shorter path, update the routingTable in the node.
                    routingTable.get(key).set(0,agentRT.get(key).get(0));
                    routingTable.get(key).set(1,agentRT.get(key).get(1));
                }
                else if(agentRT.get(key).get(0)>routingTable.get(key).get(0)){
                    //If node has a shorter path, update the routingTable in the agent.
                    agentRT.get(key).set(0,routingTable.get(key).get(0));
                    agentRT.get(key).set(1,routingTable.get(key).get(1));
                }
            }
            else{
                //If the node has an event the agent doesn't, add it.
                agentRT.put(key, routingTable.get(key));
            }
        }
        for(int key: agentRT.keySet()){
            if(routingTable.containsKey(key)){
                if(agentRT.get(key).get(0)<routingTable.get(key).get(0)){
                    //If agent has a shorter path, update the routingTable in the node.
                    routingTable.get(key).set(0,agentRT.get(key).get(0));
                    routingTable.get(key).set(1,agentRT.get(key).get(1));
                }
                else if(agentRT.get(key).get(0)>routingTable.get(key).get(0)){
                    //If node has a shorter path, update the routingTable in the agent.
                    agentRT.get(key).set(0,routingTable.get(key).get(0));
                    agentRT.get(key).set(1,routingTable.get(key).get(1));
                }
            }
            else{
                //If the agent has an event the node doesn't, add it.
                routingTable.put(key, agentRT.get(key));
            }
        }
    }

    /**
     * Description: Adds an event to this node as a new event, both in routingTable and in eventsHere-list
     * @param e : the event to add
     */
    public void addEvent(Event e){
        eventsHere.put(e.getId(),e);
        ArrayList<Integer> a = new ArrayList<>();
        a.add(0,0);
        a.add(1,0);
        routingTable.put(e.getId(),a);
    }

    /**
     * Description: Returns the informaiton associated with an event-id in the routingTable.
     * @param id : the id we want info about
     * @return an arraylist with the info. index 0: The distance to event, index 1: the direction to event, described
     * as the position in this nodes neighbourList.
     */
    public ArrayList<Integer> getEventInfo(int id){
        return routingTable.get(id);
    }

    /**
     * Description: Returns the position of the node
     * @return the position
     */
    public Position getPos(){
        return pos;
    }

    /**
     * Description: Adds node to the nodes list of neighbours
     * @param neigh: the node to add as neighbour
     */
    public void addNeighbour(Node neigh){
        neighbours.add(neigh);
    }

    /**
     * Description: returns the list of neighbours
     * @return the list of neighbours
     */
    public ArrayList<Node> getNeighbours(){
        return neighbours;
    }

    /**
     * Description: Creates a request from this node, with a destination "id". This method is called from grid.
     * Returns the request so grid can keep track of all requests.
     * @param id : the id of the event the request tracks
     * @param MAXJUMPS : the maximum amount of jumps a request is allowed to take.
     * @return : the request created
     */
    public Request createRequest(int id, int MAXJUMPS) throws Exception{
        //If the node already has an active request, throw an error as this ain't supposed to happen
        if(currentRequest!=null){
            throw new IllegalStateException("Two requests created from same node");
        }
        Request r = new Request(this, id,MAXJUMPS);
        currentRequest=r;
        timeSinceRequest=0;
        return r;
    }




}
