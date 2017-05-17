package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class - Grid class that represents the network with all the nodes in it.
 * Created by grupp 8 on 2017-05-05.
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
    private int timeEachRequestIsSent;
    private Random randomGen = new Random();
    private int nextIdGenertor;

    /**
     * Constructor - Creates the grid with a config class.
     * @param config is the configuration of the system.
     * @throws IllegalStateException if timeEachRequestIsSent is zero.
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
        this.timeEachRequestIsSent = config.getTimeEachRequestsIsSent();
        if(timeEachRequestIsSent == 0){
            throw new IllegalStateException("timeEachRequestIsSent equal to zero");
        }
        fixNeighbours();
        randomNodes = (ArrayList<Node>)listOfNodes.clone();
        Collections.shuffle(randomNodes);
        for (int i = 0; i < 4; i++) {
            fourRandomNodes.add(randomNodes.get(i));
        }
    }

    /**
     * Method - This method is called everytime a time tick is represented,
     *          the method spawns events and agents on a given probability.
     *          The method also spawns requests at given times according to the variable timeEachRequestIsSent.
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
         * If timeStep is on n*timeEachRequestIsSent for any given n
         * it should send out four Requests from four random
         * nodes in the network.
         */
        if(timeStep % timeEachRequestIsSent == 0){
            int eventId;
            if(listOfEvents.size() > 0) {
                eventId = randomGen.nextInt(listOfEvents.size());
                for (int i = 0; i < 4; i++) {
                    fourRandomNodes.get(i).createRequest(eventId, MAXJUMPSREQUEST);
                }
            }
        }
    }

    /**
     * Method - Updates all the nodes.
     */
    public void updateNodes(){
        for(Node node : listOfNodes)
            node.update();
    }

    /**
     * Method  - Checks if an event is happening.
     * @return boolean Returns true if an Event is happening, false otherwise.
     */
    private boolean detectEvent(){
        return randomGen.nextDouble()<= PROBABILITYEVENT;
    }

    /**
     * Method  - Checks if an agent will be sent.
     * @return boolean Returns true if an Agent is sent, false otherwise.
     */
    private boolean detectAgent(){
        return randomGen.nextDouble() <= PROBABILITYAGENT;
    }

    /**
     * Method - Sets the neighbours for each Node in the Grid.
     * @throws IllegalStateException if any node does not have any neighbours.
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
     * Method - Increments the timeStep.
     */
    public void timeStepIncrement(){ timeStep++; }

}
