import java.util.Stack;

/**
 * Class - Request class
 */
public class Request {
    private int MAXJUMPS;
    private Stack<Node> path = new Stack<Node>();
    private int eventId;
    private String message;

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
    public void move(){

    }

    /**
     * Method  - Returns true if the Request have returned
     *           to the Node where Request was initialized
     * @return boolean
     */
    public boolean hasReturned(){

        return true;
    }
}
