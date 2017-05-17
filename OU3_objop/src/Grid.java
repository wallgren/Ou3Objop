import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid {
    private int timeStep;
    private ArrayList<Node> listOfNodes;
    private ArrayList<Node> randomNodes;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Node> fourRandomNodes;
    private double PROBABILITYAGENT;
    private double PROBABILITYEVENT;
    private int COMLENGTH;
    private int MAXJUMPSAGENT;
    private int MAXJUMPSREQUEST;
    private Random randomGen = new Random();
    private int nextIdGenertor;

    /**
     * Constructor        - Creates the grid with a config class
     * @param config
     * @throws IllegalStateException
     */
    public Grid(Configuration config) throws IllegalStateException{
        this.listOfNodes = config.getNodes();
        this.listOfEvents = new ArrayList<>();
        this.fourRandomNodes = new ArrayList<>();
        this.PROBABILITYAGENT = config.getAgentProbability();
        this.PROBABILITYEVENT = config.getEventProbability();
        this.COMLENGTH = config.getComlength();
        this.MAXJUMPSAGENT = config.getMaxJumpsAgent();
        this.MAXJUMPSREQUEST = config.getMaxJumpsRequest();
        fixNeighbours();
        randomNodes = (ArrayList<Node>)listOfNodes.clone();
        Collections.shuffle(randomNodes);
        for (int i = 0; i < 4; i++) {
            fourRandomNodes.add(randomNodes.get(i));
        }
    }

    /**
     * Method - This method is called everytime a time tick is represented,
     *          the method spawns events and agents on a given probability,
     *          every 400 time step four Requests is sent out in the network
     *          from four random nodes
     *
     */
    public void eventHappening() {
        timeStepIncrement();

        for(Node node : listOfNodes){
            if(detectEvent()){
                nextIdGenertor++;
                Event e= new Event(node.getPos(), nextIdGenertor, timeStep);
                node.addEvent(e);
                listOfEvents.add(e);
                if(detectAgent()){
                    Agent agent = new Agent(node, MAXJUMPSAGENT);
                    node.addMessageToQueue(agent);
                }
            }
        }
        /**
         * If timeStep is on 400, 800, 1200...
         * it should send out four Requests from four random
         * nodes in the network
         */
        if(timeStep % 400 == 0){
            int eventId;
            if(listOfEvents.size() > 0) {
                eventId = randomGen.nextInt(listOfEvents.size());
                for (int i = 0; i <4; i++) {
                    fourRandomNodes.get(i).createRequest(eventId, MAXJUMPSREQUEST);
                }
            }
        }
    }

    /**
     * Method - Updates all the nodes
     */
    public void updateNodes(){
        for(Node node : listOfNodes)
            node.update();
    }

    /**
     * Method  - Returns true if an Event is happening
     * @return boolean
     */
    private boolean detectEvent(){
        return randomGen.nextDouble()<= PROBABILITYEVENT;
    }

    /**
     * Method  - Returns true if an Agent is sent
     * @return boolean
     */
    private boolean detectAgent(){
        return randomGen.nextDouble() <= PROBABILITYAGENT;
    }

    /**
     * Method - Sets the neighbours for each Node in the Grid
     * @throws IllegalStateException
     */
    private void fixNeighbours() throws IllegalStateException{
        for (int i = 0; i < listOfNodes.size(); i++) {
            Node node = listOfNodes.get(i);
            for (int j = 0; j < listOfNodes.size(); j++) {
                Node compareNode = listOfNodes.get(j);
                if (i != j) {
                    int xlength = node.getPos().getX() - compareNode.getPos().getX();
                    int ylength = node.getPos().getY() - compareNode.getPos().getY();
                    if (Math.sqrt(Math.pow(xlength, 2) + Math.pow(ylength, 2)) <= COMLENGTH) {
                        node.addNeighbour(compareNode);
                    }
                }
            }
        }
        for(Node n:listOfNodes){
            if(n.getNeighbours().isEmpty())
                throw new IllegalStateException("Node on position" + n.getPos().getX()+","+n.getPos().getY()+" has " +
                        "no neighbours");
        }
    }

    /**
     * Method - Increments the timeStep
     */
    public void timeStepIncrement(){ timeStep++; }

}
