import java.util.ArrayList;

/**
 * Created by Max Holmberg on 2017-05-12.
 */
public class Configuration {

    private ArrayList<Node> nodes;
    private double agentProbability;
    private double eventProbability;
    private int comlength;
    private int maxJumpsAgent;
    private int maxJumpsRequest;

    public Configuration(){}

    public void setNodes(ArrayList<Node> nodes) {
        if(nodes.size() < 4)
            throw new IllegalStateException("Grid less than four nodes");
        this.nodes = nodes;
    }

    public void setMaxJumpsRequest(int maxJumpsRequest) { this.maxJumpsRequest = maxJumpsRequest;}

    public void setMaxJumpsAgent(int maxJumpsAgent) { this.maxJumpsAgent = maxJumpsAgent; }

    public void setComlength(int comlength) { this.comlength = comlength; }

    public void setEventProbability(double eventProbability) { this.eventProbability = eventProbability; }

    public void setAgentProbability(double agentProbability) { this.agentProbability = agentProbability; }

    public int getMaxJumpsRequest() { return maxJumpsRequest; }

    public int getMaxJumpsAgent() { return maxJumpsAgent; }

    public int getComlength() { return comlength; }

    public double getEventProbability() { return eventProbability; }

    public double getAgentProbability() { return agentProbability; }

    public ArrayList<Node> getNodes() { return nodes; }
}
