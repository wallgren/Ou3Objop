/**
 * Class - Request class
 */
public class Request extends Message{
    private int eventId;
    private String message;
    private boolean idFound;
    private int directionNext;

    /**
     * Constructor        - Creates a Request from a given node
     * @param requestFrom
     * @param eventId
     * @param MAXJUMPS
     */
    public Request(Node requestFrom, int eventId, int MAXJUMPS){
        super(requestFrom, MAXJUMPS);
        this.eventId = eventId;
    }

    /**
     * Method  - Returns the message of this Request
     * @return String message
     */
    public String getMessage(){ return message; }


    /**
     * Method - Updates the Request
     */
    public void update(){
        if(currNode.returnTimeIfEventExists(eventId) != null){
            message = "Position: (" + currNode.getPos().getX() + ", " + currNode.getPos().getY() + ") " +
                    "time: " + currNode.returnTimeIfEventExists(eventId) + " event id: " + eventId;
            nextNode=findNextNode();
        }
        else if(jumps < MAXJUMPS)
            nextNode = findNextNode();
        else
            currNode.removeFirstElement();
    }

    /**
     * Method - Finds the next node for the Request to move to
     * @return Node
     */
    @Override
    public Node findNextNode(){
        if(message == null ){
            if(currNode.getEventInfo(eventId) != null){
                idFound = true;
                directionNext = currNode.getEventInfo(eventId).get(1);
                return currNode.getNeighbours().get(directionNext);
            } else
                return currNode.getNeighbours().get(random.nextInt(currNode.getNeighbours().size()));
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
     * Method - Returns eventId
     * @return int
     */
    public int getEventId(){
        return eventId;
    }

    /**
     * Method - Returns MAXJUMPS of the Request
     * @return int
     */
    public int getMaxJumps(){
        return MAXJUMPS;
    }

    /**
     * Method  - Returns true if the Request have returned
     *           to the Node where Request was initialized
     * @return boolean
     */
    public boolean hasReturned(){
        return path.isEmpty();
    }
}