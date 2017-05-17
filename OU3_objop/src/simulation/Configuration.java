package simulation;

import java.util.ArrayList;

/**
 * Class - Configuration class to set the parameters to grid.
 * Created by grupp 8 on 2017-05-12.
 */
public class Configuration {

    private ArrayList<Node> nodes;
    private double agentProbability;
    private double eventProbability;
    private int comlength;
    private int maxJumpsAgent;
    private int maxJumpsRequest;
    private int timeEachRequestsIsSent;

    /**
     * Constructor - Creates a Configuration class.
     */
    public Configuration() {}

    /**
     * Method - Sets the Nodes to the given node list.
     * @param nodes: The nodes to add to the system.
     */
    public void setNodes(ArrayList<Node> nodes) {
        if (nodes.size() < 4)
            throw new IllegalStateException("Grid less than four nodes");
        this.nodes = nodes;
    }

    /**
     * Method - Sets the maxJumpsRequest to the given parameter.
     * @param maxJumpsRequest: The maximum number of jumps that a request can perform.
     */
    public void setMaxJumpsRequest(int maxJumpsRequest) {this.maxJumpsRequest = maxJumpsRequest;}

    /**
     * Method - Sets the maxJumpsAgents to the given parameter.
     * @param maxJumpsAgent: The maximum number of jumps that an agent can perform.
     */
    public void setMaxJumpsAgent(int maxJumpsAgent) {this.maxJumpsAgent = maxJumpsAgent;}

    /**
     * Method - Sets the comLength to the given parameter.
     * @param comlength: The radius within which a node can detect other nodes.
     */
    public void setComlength(int comlength) {this.comlength = comlength;}

    /**
     * Method - Sets the eventProbability to the given parameter.
     * @param eventProbability: is what the probability will be for an event to be created.
     */
    public void setEventProbability(double eventProbability) {this.eventProbability = eventProbability;}

    /**
     * Method - Sets the agentProbability to the given paramter.
     * @param agentProbability: is what the probability will be for an agent to be created.
     */
    public void setAgentProbability(double agentProbability) {this.agentProbability = agentProbability;}

    /**
     * Method - Sets the timeEachRequestisSent.
     * @param timeEachRequestsIsSent: is how often the program will send requests.
     */
    public void setTimeEachRequestsIsSent(int timeEachRequestsIsSent){
        this.timeEachRequestsIsSent = timeEachRequestsIsSent;
    }

    /**
     * Method - Gets the list of nodes.
     * @return ArrayList<Node>: List of the nodes in the system.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Method - Gets the maxJumpsRequest.
     * @return int: The maximum number of jumps that a request can perform.
     */
    public int getMaxJumpsRequest() {return maxJumpsRequest;}

    /**
     * Method - Gets the maxJumpsAgent.
     * @return int: The maximum number of jumps that a agent can perform.
     */
    public int getMaxJumpsAgent() {return maxJumpsAgent;}

    /**
     * Method - Gets the comLength.
     * @return int: The radius within which a node can detect other nodes.
     */
    public int getComlength() {return comlength;}

    /**
     * Method - Gets the eventProbability.
     * @return double: The probability that a event is created.
     */
    public double getEventProbability() {return eventProbability;}

    /**
     * Method - Gets the agentProbability.
     * @return double: The probability that a agent is created.
     */
    public double getAgentProbability() {return agentProbability;}

    /**
     * Method - Gets the timeEachRequestIsSent.
     * @return int: How often a request is created.
     */
    public int getTimeEachRequestsIsSent() {return timeEachRequestsIsSent; }
}
