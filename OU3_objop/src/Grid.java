import java.util.ArrayList;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid {
    private ArrayList<Node> listOfNodes;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Request> listOfRequests;
    private ArrayList<Agent> listOfAgents;
    private double PROBABILITYAGENT;
    private double PROBABILITYEVENT;
    private int COMLENGTH;
    private int MAXJUMPS;
    private int timeStep;

    /**
     * Constructor        - Creates the grid with a list of nodes
     * @param listOfNodes
     * @param PROBABILITYAGENT
     * @param PROBABILITYEVENT
     * @param COMLENGTH
     * @param MAXJUMPS
     */
    public Grid(ArrayList<Node> listOfNodes, double PROBABILITYAGENT,
                double PROBABILITYEVENT, int COMLENGTH, int MAXJUMPS){
        this.listOfNodes = listOfNodes;
        this.PROBABILITYAGENT = PROBABILITYAGENT;
        this.PROBABILITYEVENT = PROBABILITYEVENT;
        this.COMLENGTH = COMLENGTH;
        this.MAXJUMPS = MAXJUMPS;
        fixNeighbours();
    }

    /**
     * INTE KLAR
     */
    public void eventHappening(){
        for(Node node : listOfNodes){
            if(detectEvent()){
                if(detectAgent()){

                }
            }
        }
    }

    /**
     * Method  - Returns true if an Event is happening
     * @return boolean
     */
    private boolean detectEvent(){
        Random r = new Random();
        return r.nextDouble() <= PROBABILITYEVENT;
    }

    /**
     * Method  - Returns true if an Agent is sent
     * @return boolean
     */
    private boolean detectAgent(){
        Random r = new Random();
        return r.nextDouble() <= PROBABILITYAGENT;
    }

    /**
     * Method - Sets the neighbours for each Node in the Grid
     */
    private void fixNeighbours(){
        for (int i = 0; i < listOfNodes.size(); i++) {
            Node node = listOfNodes.get(i);
            for (int j = 0; j < listOfNodes.size(); j++) {
                Node compareNode = listOfNodes.get(j);
                if(i != j){
                    int xlength = node.getPos().getX() - compareNode.getPos().getX();
                    int ylength = node.getPos().getY() - compareNode.getPos().getY();
                    if(Math.sqrt(Math.pow(xlength, 2) + Math.pow(ylength, 2)) <= 15){
                        node.addNeighbour(compareNode);
                    }
                }
            }
        }
    }

    /**
     * Method             - Creates a Request from a given Node
     * @param requestFrom
     * @param eventId
     * @param MAXJUMPS
     */
    public void createRequest(Node requestFrom, int eventId, int MAXJUMPS) throws Exception {
        listOfRequests.add(requestFrom.createRequest(eventId, MAXJUMPS));
    }

    /**
     * Method - Moves all the Agents in the Grid
     */
    public void moveAgents(){
        for(Agent agent : listOfAgents)
            agent.move();
    }

    /**
     * Method - Moves all the Requests in the Grid
     */
    public void moveRequests(){
        for(Request request : listOfRequests)
            request.move();
    }

    /**
     * Method  - Returns the timeStep of the Grid
     * @return int
     */
    public int getTimeStep(){ return timeStep; }

    /**
     * Method - Increments the timeStep
     */
    public void timeStepIncrement(){ timeStep++; }
}
