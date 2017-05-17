package simulation;

/**
 * Class - Request class that simulates a request message.
 * Created by grupp 8 on 2017-05-05
 */
public class Request extends Message{
    private int eventId;
    private String message;
    private int directionNext;

    /**
     * Constructor - Creates a Request from a given node.
     * @param requestFrom: Is the node where the request was sent from.
     * @param eventId: Is the ID of the event that a request is looking for.
     * @param MAXJUMPS: Is the maximum amount of jumps that a request can
     *                  perform.
     */
    public Request(Node requestFrom, int eventId, int MAXJUMPS){
        super(requestFrom, MAXJUMPS);
        this.eventId = eventId;
    }

    /**
     * Method - Returns the message of this Request.
     * @return The string message.
     */
    public String getMessage(){ return message; }


    /**
     * Method - Updates the Request.
     */
    public void update(){
        if(currNode.returnTimeIfEventExists(eventId) != null){
            message = "Position: (" + currNode.getPos().getX()
                    + ", " + currNode.getPos().getY() + ") " +
                    "time: " + currNode.returnTimeIfEventExists(eventId)
                    + " event id: " + eventId;
            nextNode=findNextNode();
        }
        else if(jumps < MAXJUMPS)
            nextNode = findNextNode();
        else
            currNode.removeFirstElement();
    }

    /**
     * Method - Finds the next node for the Request to move to.
     * @return The next node for a request to move to.
     */
    @Override
    protected Node findNextNode(){
        if(message == null ){
            if(currNode.getEventInfo(eventId) != null){
                directionNext = currNode.getEventInfo(eventId).getDirection();
                return currNode.getNeighbours().get(directionNext);
            } else
                return currNode.getNeighbours().
                        get(random.nextInt(currNode.getNeighbours().size()));
        } else{
            path.pop();
            if(!path.isEmpty())
                return path.peek();
            else
                currNode.removeFirstElement();
            return currNode;
        }
    }

    /**
     * Method - Returns eventId.
     * @return int: The ID of the event.
     */
    public int getEventId(){
        return eventId;
    }

    /**
     * Method - Returns MAXJUMPS of the Request.
     * @return int: The amount of jumps that a request can perform.
     */
    public int getMaxJumps(){
        return MAXJUMPS;
    }

    /**
     * Method  - Returns true if the Request have returned
     *           to the Node where Request was initialized.
     * @return boolean: True if a request has returned, otherwise false.
     */
    public boolean hasReturned(){
        return path.isEmpty();
    }
}