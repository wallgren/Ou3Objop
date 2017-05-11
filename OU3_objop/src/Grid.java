import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid extends JFrame{
    private int timeStep;
    private ArrayList<Node> listOfNodes;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Agent> listOfAgents;
    private ArrayList<Node> fourRandomNodes;
    private double PROBABILITYAGENT;
    private double PROBABILITYEVENT;
    private int COMLENGTH;
    private int MAXJUMPSAGENT;
    private int MAXJUMPSREQUEST;
    private Random randomGen = new Random();
    private int nextIdGenertor;
    private Graphics graphics;
    private BufferedImage img;

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
                double PROBABILITYEVENT, int COMLENGTH, int MAXJUMPSAGENT, int MAXJUMPSREQUEST) throws IOException {
        this.listOfNodes = listOfNodes;
        this.listOfEvents = new ArrayList<>();
        this.fourRandomNodes = new ArrayList<>();
        this.listOfAgents = new ArrayList<>();
        this.PROBABILITYAGENT = PROBABILITYAGENT;
        this.PROBABILITYEVENT = PROBABILITYEVENT;
        this.COMLENGTH = COMLENGTH;
        this.MAXJUMPSAGENT = MAXJUMPSAGENT;
        this.MAXJUMPSREQUEST = MAXJUMPSREQUEST;
        fixNeighbours();
        int count = 0;
        while(count < 4){
            Node randomNode = listOfNodes.get(randomGen.nextInt(listOfNodes.size()));
            if(!fourRandomNodes.contains(randomNode)) {
                fourRandomNodes.add(randomNode);
                count++;
            }
        }
        img = ImageIO.read(new File("/home/c16/c16gwn/Documents/background.png"));
        graphics = img.getGraphics();
        setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    @Override
    public void paint(Graphics g){
        g.drawImage(img, 50, 50, 800, 800, null);
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
                    System.out.println("Agent created at:"+node.getPos().getX()+";"+node.getPos().getY());
                    Agent agent = new Agent(node, MAXJUMPSAGENT);
                    listOfAgents.add(agent);
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
                for (int i = 0; i < 4; i++) {
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
        for(Node node : listOfNodes) {
            node.update();
            drawRektangle(node.getPos(), Color.BLACK);
            if(!node.eventsHereIsEmpty())
                drawRektangle(node.getPos(), Color.green);
            for(Agent a : listOfAgents) {
                if(a.canMove())
                    drawRektangle(a.getCurrNodePos(), Color.RED);
            }
            if(node.getMessageQueue().size() > 0) {
                if (node.getMessageQueue().get(0) instanceof Request) {
                    drawRektangle(node.getPos(), Color.BLUE);
                }
            }
            this.repaint();
        }
    }

    public void drawRektangle(Position p, Color c){
        graphics.setColor(c);
        graphics.drawRect(p.getX()+10, p.getY()+10, 5, 5);
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