import java.util.Stack;

/**
 * Class - Request class
 */
public class Request extends Message{
    private int MAXJUMPS;
    private Stack<Node> path = new Stack<Node>();
    private int eventId;
    private String message;
    private boolean idFound;
    private int directionNext;
    private int jumps;

    /**
     * Constructor        - Creates a Request from a given node
     * @param requestFrom
     * @param eventId
     * @param MAXJUMPS
     */
    public Request(Node requestFrom, int eventId, int MAXJUMPS){
        path.push(requestFrom);
        this.eventId = eventId;
        this.MAXJUMPS = MAXJUMPS;
    }

    /**
     * Method  - Returns the message of this Request
     * @return String message
     */
    public String getMessage(){ return message; }

    /**
     * Method  - moves the Request one step
     */
    @Override
    public void move(){
        if(jumps < MAXJUMPS*8) {
            Node currentNode = path.peek();
            if (!idFound) {
                path.add(currentNode.getNeighbours().get(rand.nextInt(currentNode.getNeighbours().size())));
                currentNode = path.peek();
                if (currentNode.getEventInfo(eventId) != null) {
                    idFound = true;
                    directionNext = currentNode.getEventInfo(eventId).get(1);
                }
            } else {
                path.add(currentNode.getNeighbours().get(directionNext));
            }
            jumps++;
            if(currentNode.eventExistsHere(eventId)){
                message = "Position: (" + currentNode.getPos().getX() + ", " + currentNode.getPos().getY() + ") " +
                        "time: " + currentNode.getEvent(eventId).getTime() + " event id: " + eventId;
            }
        }
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
