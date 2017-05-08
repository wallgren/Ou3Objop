import java.util.ArrayList;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid {
    private ArrayList<Node> listOfNodes;
    private ArrayList<Node> fourRandomNodes;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Request> listOfRequests;
    private ArrayList<Agent> listOfAgents;
    private double PROBABILITYAGENT;
    private double PROBABILITYEVENT;
    private int COMLENGTH;
    private int MAXJUMPSAGENT;
    private int MAXJUMPSREQUEST;
    private int timeStep;
    private Random randomGenerator = new Random();

    /**
     * Constructor        - Creates the grid with a list of nodes
     * @param listOfNodes
     * @param PROBABILITYAGENT
     * @param PROBABILITYEVENT
     * @param COMLENGTH
     * @param MAXJUMPSAGENT
     * @param MAXJUMPSREQUEST
     */
    public Grid(ArrayList<Node> listOfNodes, double PROBABILITYAGENT,
                double PROBABILITYEVENT, int COMLENGTH, int MAXJUMPSAGENT, int MAXJUMPSREQUEST){
        this.listOfNodes = listOfNodes;
        this.PROBABILITYAGENT = PROBABILITYAGENT;
        this.PROBABILITYEVENT = PROBABILITYEVENT;
        this.COMLENGTH = COMLENGTH;
        this.MAXJUMPSAGENT = MAXJUMPSAGENT;
        this.MAXJUMPSREQUEST = MAXJUMPSREQUEST;
        fixNeighbours();
        for (int i = 0; i < 4; i++) {
            fourRandomNodes.add(listOfNodes.get(randomGenerator.nextInt(listOfNodes.size())));
        }
    }

    /**
     * Method - This method is called everytime a time tick is represented,
     *          the method spawns events and agents on a given probability,
     *          every 400 time step four Requests is sent out in the network
     *          from four random nodes
     *
     */
    public void eventHappening(){
        timeStepIncrement();
        for(Node node : listOfNodes){
            if(detectEvent()){
                if(detectAgent()){
                    listOfAgents.add(new Agent(node, MAXJUMPSAGENT));
                }
                listOfEvents.add(new Event(node.getPos(), randomGenerator.nextInt(1000), timeStep));
            }
        }
        /**
         * If timeStep is on 400, 800, 1200...
         * it should send out four Requests from four random
         * nodes in the network
         */
        if(timeStep % 400 == 0){
            for (int i = 0; i < 4; i++) {
                listOfRequests.add(new Request(fourRandomNodes.get(i),
                        listOfEvents.get(randomGenerator.nextInt(listOfEvents.size())).getId(), MAXJUMPSREQUEST));
                fourRandomNodes.remove(i);
            }
            /**
             * Randomizes the four next nodes
             */
            for (int i = 0; i < 4; i++) {
                fourRandomNodes.add(listOfNodes.get(randomGenerator.nextInt(listOfNodes.size())));
            }
        }
    }

    /**
     * Method  - Returns true if an Event is happening
     * @return boolean
     */
    private boolean detectEvent(){
        return randomGenerator.nextDouble() <= PROBABILITYEVENT;
    }

    /**
     * Method  - Returns true if an Agent is sent
     * @return boolean
     */
    private boolean detectAgent(){
        return randomGenerator.nextDouble() <= PROBABILITYAGENT;
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
     * Method - Increments the timeStep
     */
    private void timeStepIncrement(){ timeStep++; }
}
