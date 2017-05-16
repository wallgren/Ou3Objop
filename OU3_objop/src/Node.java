import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class Node {
    private ArrayList<Node> neighbours;
    private HashMap<Integer, Guide> routingTable;
    private Position pos;
    private ArrayList<Message> messageQueue;
    private HashMap<Integer, Event> eventsHere;
    private ArrayList<Integer> timeSinceRequests;
    private ArrayList<Request> currentRequests;
    private ArrayList<Boolean> sentTwice;
    private static long totalTime;

    public Node(Position p){
        routingTable=new HashMap<>();
        neighbours=new ArrayList<>();
        messageQueue=new ArrayList<>();
        pos=p;
        timeSinceRequests=new ArrayList<>();
        eventsHere=new HashMap<>();
        currentRequests=new ArrayList<>();
        sentTwice=new ArrayList<>();
    }

    /**
     * Description: Compare two routingTables with each other, update with the shortest path to the event
     * @param agentRT : the routingTable to compare.
     */
    public void compareTable(HashMap<Integer,Guide> agentRT){
        long timeBefore = System.nanoTime();
        for(int key: routingTable.keySet()){
            if(agentRT.containsKey(key)){
                //If agent has a shorter path, update the routingTable in the node.
                if(agentRT.get(key).getSteps()<routingTable.get(key).getSteps()) {
                    Guide guide = routingTable.get(key);
                    guide.setStepsAndDirection(agentRT.get(key).getSteps(), agentRT.get(key).getDirection());
                }
                else if(routingTable.get(key).getSteps()<agentRT.get(key).getSteps()){
                    Guide guide = agentRT.get(key);
                    guide.setStepsAndDirection(routingTable.get(key).getSteps(), routingTable.get(key).getDirection());
                }
            }
            else{
                //If the node has an event the agent doesn't, add it.
                agentRT.put(key, new Guide(routingTable.get(key).getSteps(), routingTable.get(key).getDirection()));
            }
        }
        for(int key: agentRT.keySet()){
            if(routingTable.containsKey(key)){
                if(agentRT.get(key).getSteps()<routingTable.get(key).getSteps()){
                    Guide guide = routingTable.get(key);
                    guide.setStepsAndDirection(agentRT.get(key).getSteps(), agentRT.get(key).getDirection());
                }


                else if(routingTable.get(key).getSteps()<agentRT.get(key).getSteps()){
                    Guide guide = agentRT.get(key);
                    guide.setStepsAndDirection(routingTable.get(key).getSteps(), routingTable.get(key).getDirection());
                }

            }
            else{
                routingTable.put(key, new Guide(agentRT.get(key).getSteps(), agentRT.get(key).getDirection()));
            }
        }
        long timeAfter = System.nanoTime();
        totalTime += timeAfter - timeBefore;
    }

    public long getTotalTime(){return totalTime;}

    /**
     * Description: Adds an event to this node as a new event, both in routingTable and in eventsHere-list
     * @param e : the event to add
     */
    public void addEvent(Event e){
        eventsHere.put(e.getId(),e);
        routingTable.put(e.getId(), new Guide(0, 0));
    }

    /**
     * Description: Returns the informaiton associated with an event-id in the routingTable.
     * @param id : the id we want info about
     * @return an arraylist with the info. index 0: The distance to event, index 1: the direction to event, described
     * as the position in this nodes neighbourList.
     */
    public Guide getEventInfo(int id){
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
        Request r = new Request(this, id,MAXJUMPS);
        currentRequests.add(r);
        timeSinceRequests.add(0);
        sentTwice.add(false);
        addMessageToQueue(r);
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
        int previousSize = messageQueue.size();

        //Update the first message in the queue
        if(previousSize != 0){
            messageQueue.get(0).update();
        }

        //Make sure that if the first message in the queue has reached its final step, the next message is also updated
        while(previousSize != messageQueue.size()) {
            previousSize = messageQueue.size();
            if(previousSize != 0){
                messageQueue.get(0).update();
            }
        }

        //Move the first message in the queue
        if (messageQueue.size() != 0 && !messageQueue.get(0).nextNodeIsBusy()){
            messageQueue.get(0).move();
        }

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


    public boolean eventsHereIsEmpty(){ return eventsHere.isEmpty(); }

    /**
     * Description: If this node has sent out a request, this method checks that requests status, prints its message
     * if it has returned, and removes it if enough time has passed.
     */
    private void checkRequest(){
        for (int i = 0; i < currentRequests.size(); i++) {
            if(timeSinceRequests.get(i)<=currentRequests.get(i).getMaxJumps()*8){
                if(currentRequests.get(i).hasReturned()){
                    System.out.println(currentRequests.get(i).getMessage());
                    currentRequests.remove(i);
                    timeSinceRequests.remove(i);
                    sentTwice.set(i, false);
                }else
                    timeSinceRequests.set(i, timeSinceRequests.get(i)+1);
            }else if(sentTwice.get(i)==false){
                //If a request times out we should send out a new request for the same event once more.
                sentTwice.set(i, true);
                int id = currentRequests.get(i).getEventId();
                int maxJ=currentRequests.get(i).getMaxJumps();
                currentRequests.set(i,createRequest(id, maxJ));
            }else{
                currentRequests.remove(i);
                timeSinceRequests.remove(i);
                sentTwice.remove(i);
            }
        }
    }

    public Message getMessageOnTop(){
        if(!messageQueue.isEmpty())
            return messageQueue.get(0);
        return null;
    }

    public int numberOfElementsInMessageQueue(){
        return messageQueue.size();
    }

    /**
     *Description: Updates messages in the queue of this node and the request started from this node if such exists.
     */
    public void update(){
        moveMessage();
        checkRequest();
    }

}