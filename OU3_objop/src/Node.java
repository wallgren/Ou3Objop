import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class Node {
    private ArrayList<Node> neighbours;
    private HashMap<Integer, ArrayList<Integer>> routingTable;
    private Position pos;
    private ArrayList<Message> messageQueue;
    private HashMap<Integer, Event> eventsHere;
    private int timeSinceRequest;
    private Request currentRequest;
    private boolean sentTwice;

    public Node(Position p){
        routingTable=new HashMap<>();
        neighbours=new ArrayList<>();
        messageQueue=new ArrayList<>();
        pos=p;
        timeSinceRequest=0;
        eventsHere=new HashMap<>();
        currentRequest=null;
        sentTwice=false;
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
    public Request createRequest(int id, int MAXJUMPS) throws IllegalStateException{
        //If the node already has an active request, throw an error as this ain't supposed to happen
        if(currentRequest!=null){
            throw new IllegalStateException("Two requests created from same node");
        }
        Request r = new Request(this, id,MAXJUMPS);
        currentRequest=r;
        timeSinceRequest=0;
        r.update();
        return r;
    }

    /**
     * Description: remove the first element from the messageQueue
     */
    public void removeFirstElement(){
        messageQueue.remove(0);
    }

    /**
     * Description: adds a message to the messageQueue
     * @param m : the message to add.
     */
    public void addMessageToQueue(Message m){
        messageQueue.add(m);
    }

    /**
     * Description: This pubic method checks if theres any messages in the queue. If there are any, it calls upon them
     * to move and update.
     */
    private void moveMessage(){
        if(messageQueue.size()!=0)
            messageQueue.get(0).move();
        if(messageQueue.size()!=0)
            messageQueue.get(0).update();
    }

    /**
     * Description: This method checks if a specific event has occured on this node. If it has, it returns the time
     * of the event, if it hasn't, it returns null.
     * @param id: The if of the event to look for
     * @return null if event hasn't happened here, the time of the event if it has.
     */
    public Integer returnTimeIfEventExists(int id){
        if(eventsHere.containsKey(id))
            return eventsHere.get(id).getTime();
        return null;
    }

    /**
     * Description: If this node has sent out a request, this method checks that requests status, prints its message
     * if it has returned, and removes it if enough time has passed.
     */
    private void checkRequest(){
        if(currentRequest!=null){
            if(timeSinceRequest<=currentRequest.getMaxJumps()*8){
                if(currentRequest.hasReturned()){
                    System.out.println(currentRequest.getMessage());
                    currentRequest=null;
                    timeSinceRequest=0;
                    sentTwice=false;
                }else
                    timeSinceRequest++;
            }else if(!sentTwice){
                //If a request times out we should send out a new request for the same event once more.
                sentTwice=true;
                currentRequest=createRequest(currentRequest.getEventId(),currentRequest.getMaxJumps());
            }else{
                currentRequest=null;
                timeSinceRequest=0;
                sentTwice=false;
            }
        }
    }

    /**
     *Description: Updates messages in the queue of this node and the request started from this node if such exists.
     */
    public void update(){
        moveMessage();
        checkRequest();
    }

}
