import java.util.ArrayList;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid {
    private int timeStep;
    private ArrayList<Node> listOfNodes;
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
        this.listOfEvents = new ArrayList<>();
        this.fourRandomNodes = new ArrayList<>();
        this.PROBABILITYAGENT = PROBABILITYAGENT;
        this.PROBABILITYEVENT = PROBABILITYEVENT;
        this.COMLENGTH = COMLENGTH;
        this.MAXJUMPSAGENT = MAXJUMPSAGENT;
        this.MAXJUMPSREQUEST = MAXJUMPSREQUEST;
        fixNeighbours();
        int count = 0;
        while(count < 4){
            int rN=randomGen.nextInt(listOfNodes.size());
            if(!fourRandomNodes.contains(listOfNodes.get(rN))) {
                fourRandomNodes.add(listOfNodes.get(rN));
                count++;
            }
        }
    }

    /**
     * Method - This method is called everytime a time tick is represented,
     *          the method spawns events and agents on a given probability,
     *          every 400 time step four Requests is sent out in the network
     *          from four random nodes
     *
     */
    public void eventHappening() throws Exception {
        timeStepIncrement();
        System.out.println(timeStep);

        for(Node node : listOfNodes){
            node.setBusy(false);
            if(detectEvent()){
                nextIdGenertor++;
                Event e= new Event(node.getPos(), nextIdGenertor, timeStep);
                node.addEvent(e);
                listOfEvents.add(e);
                if(detectAgent()){
                    //System.out.println("Agent created at:"+node.getPos().getX()+";"+node.getPos().getY());
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
            int eventId = 0;
            if(listOfEvents.size() > 0) {
                eventId = randomGen.nextInt(listOfEvents.size());
                for (int i = 0; i <4; i++) {
                    fourRandomNodes.get(i).createRequest(eventId, MAXJUMPSREQUEST);
                }
            }
            fourRandomNodes.clear();
            /**
             * Randomizes the four next nodes
             */
            int count = 0;
            while(count < 4){
                Node randomNode = listOfNodes.get(randomGen.nextInt(listOfNodes.size()));
                if(!fourRandomNodes.contains(randomNode)) {
                    fourRandomNodes.add(randomNode);
                    count++;
                }
            }
        }
    }

    /**
     * Method - Updates all the nodes
     */
    public void updateNodes(){
        if(timeStep==400)
            System.out.println();
        for(Node node : listOfNodes)
            node.update();
    }

    /**
     * Method  - Returns true if an Event is happening
     * @return boolean
     */
    private boolean detectEvent(){
        double a =randomGen.nextDouble();
        boolean b =a <= PROBABILITYEVENT;
        return b;
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
     */
    private void fixNeighbours() {
        for (int i = 0; i < listOfNodes.size(); i++) {
            Node node = listOfNodes.get(i);
            for (int j = 0; j < listOfNodes.size(); j++) {
                Node compareNode = listOfNodes.get(j);
                if (i != j) {
                    int xlength = node.getPos().getX() - compareNode.getPos().getX();
                    int ylength = node.getPos().getY() - compareNode.getPos().getY();
                    if (Math.sqrt(Math.pow(xlength, 2) + Math.pow(ylength, 2)) <= 15) {
                        node.addNeighbour(compareNode);
                    }
                }
            }
        }
    }

    /**
     * Method - Increments the timeStep
     */
    public void timeStepIncrement(){ timeStep++; }
}
