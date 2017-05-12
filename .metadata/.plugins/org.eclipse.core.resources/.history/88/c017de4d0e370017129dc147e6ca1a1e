import javafx.geometry.Pos;
import sun.security.krb5.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class - Grid represents the network with all the nodes in it
 */
public class Grid extends JFrame{
    private int timeStep;
    private ArrayList<Node> listOfNodes;
    private ArrayList<Node> randomNodes;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Node> fourRandomNodes;
    private ArrayList<Agent> listOfAgents;
    private double PROBABILITYAGENT;
    private double PROBABILITYEVENT;
    private int COMLENGTH;
    private int MAXJUMPSAGENT;
    private int MAXJUMPSREQUEST;
    private Random randomGen = new Random();
    private int nextIdGenertor;
    private BufferedImage image;
    private Graphics graphics;

    /**
     *  JFRAME CONFIGURATIONS
     *
     */

    @Override
    public void paint(Graphics g){
        g.drawImage(image, 50, 50, 800, 800, null);
    }

    public void drawRectangle(Position p, Color c){
        graphics.setColor(c);
        graphics.fillRect(p.getX()+1, p.getY()+1, 8, 8);
    }
    /**
     * *****************************************************************************
     */


    /**
     * Constructor        - Creates the grid with a configuration class
     * @Param Configuration config
     */
    public Grid(Configuration config) throws IOException {
        this.listOfNodes = config.getNodes();
        this.listOfEvents = new ArrayList<>();
        this.fourRandomNodes = new ArrayList<>();
        this.listOfAgents = new ArrayList<>();
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

        //Read image and pack/set visibleFrame
        image = ImageIO.read(new File("C:\\Users\\Max Holmberg\\Pictures\\grid.png"));
        graphics = image.getGraphics();
        setPreferredSize(new Dimension(1000, 900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

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
                    listOfAgents.add(agent);
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
        for(Node node : listOfNodes) {
            node.update();
            drawRectangle(node.getPos(), Color.black);
            if(!node.eventsHereIsEmpty())
                drawRectangle(node.getPos(), Color.green);
            if(node.getMessageOnTop() != null) {
                if(node.getMessageOnTop() instanceof Request)
                    drawRectangle(node.getPos(), Color.BLUE);
            }
            for(Agent agent : listOfAgents){
                if(agent.canMove())
                    drawRectangle(agent.currNode.getPos(), Color.RED);
            }
        }
        repaint();
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