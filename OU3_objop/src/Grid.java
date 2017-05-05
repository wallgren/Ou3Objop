import java.util.ArrayList;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid {
    private ArrayList<Node> listOfNodes;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Request> listOfRequests;
    private double PROBABILITYAGENT;
    private double PROBABILITYEVENT;
    private int COMLENGTH;
    private int MAXJUMPS;

    /**
     * Constructor        - Creates the grid with a list of nodes
     * @param listOfNodes
     */
    public Grid(ArrayList<Node> listOfNodes){
        this.listOfNodes = listOfNodes;
    }

    /**
     * Method
     */
    public void eventHappening(){

    }

    /**
     * Method  - Returns true if an Event is happening
     * @return boolean
     */
    private boolean detectEvent(){
        Random r = new Random;
        return r.nextDouble() <= PROBABILITYEVENT;
    }

    /**
     * Method  - Returns true if an Agent is sent
     * @return boolean
     */
    private boolean detectAgent(){
        Random r = new Random;
        return r.nextDouble() <= PROBABILITYAGENT;
    }

    /**
     * Method - Sets the neighbours for each Node in the Grid
     */
    private void fixNeighbours(){

    }

    /**
     * Method             - Creates a Request from a given Node
     * @param requestFrom
     * @param eventId
     * @param MAXJUMPS
     */
    public void createRequest(Node requestFrom, int eventId, int MAXJUMPS){
        
    }

    /**
     * Method - Moves all the Agents in the Grid
     */
    public void moveAgents(){

    }

    /**
     * Method - Moves all the Requests in the Grid
     */
    public void moveRequests(){

    }
}
