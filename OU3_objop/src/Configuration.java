import java.util.ArrayList;

/**
 * Created by Max Holmberg on 2017-05-12.
 * Class - Configuration class to set the parameters to grid
 */
public class Configuration {

    private ArrayList<Node> nodes;
    private double agentProbability;
    private double eventProbability;
    private int comlength;
    private int maxJumpsAgent;
    private int maxJumpsRequest;

    /**
     * Constructor - Creates a Configuration class
     */
    public Configuration() {}

    /**
     * Method - Sets the Nodes to the given node list
     * @param nodes
     */
    public void setNodes(ArrayList<Node> nodes) {
        if (nodes.size() < 4)
            throw new IllegalStateException("Grid less than four nodes");
        this.nodes = nodes;
    }

    /**
     * Method - Sets the maxJumpsRequest to the given parameter
     * @param maxJumpsRequest
     */
    public void setMaxJumpsRequest(int maxJumpsRequest) {this.maxJumpsRequest = maxJumpsRequest;}

    /**
     * Method - Sets the maxJumpsAgents to the given parameter
     * @param maxJumpsAgent
     */
    public void setMaxJumpsAgent(int maxJumpsAgent) {this.maxJumpsAgent = maxJumpsAgent;}

    /**
     * Method - Sets the comLength to the given parameter
     * @param comlength
     */
    public void setComlength(int comlength) {this.comlength = comlength;}

    /**
     * Method - Sets the eventProbability to the given parameter
     * @param eventProbability
     */
    public void setEventProbability(double eventProbability) {this.eventProbability = eventProbability;}

    /**
     * Method - Sets the agentProbability to the given paramter
     * @param agentProbability
     */
    public void setAgentProbability(double agentProbability) {this.agentProbability = agentProbability;}

    /**
     * Method - Gets the maxJumpsRequest
     * @return int
     */
    public int getMaxJumpsRequest() {return maxJumpsRequest;}

    /**
     * Method - Gets the maxJumpsAgent
     * @return int
     */
    public int getMaxJumpsAgent() {return maxJumpsAgent;}

    /**
     * Method - Gets the comLength
     * @return int
     */
    public int getComlength() {return comlength;}

    /**
     * Method - Gets the eventProbability
     * @return double
     */
    public double getEventProbability() {return eventProbability;}

    /**
     * Method - Gets the agentProbability
     * @return double
     */
    public double getAgentProbability() {return agentProbability;}
}
