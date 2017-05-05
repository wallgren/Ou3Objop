import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by c16gwn on 2017-05-05.
 */
public class Node {
    private ArrayList<Node> neighbours;
    private HashMap<Integer, ArrayList<Integer>> routingTable;
    private Position pos;
    private ArrayList<Agent> agentQueue;
    private HashMap<Integer, Event> eventsHere;
    private int timeSinceRequest;
    private Request currentRequest;

    public Node(Position p){
        routingTable=new HashMap<>();
        neighbours=new ArrayList<>();
        pos=p;
        timeSinceRequest=0;
        eventsHere=new HashMap<>();
    }

    /**
     * Description: Compare two routingTables with eachother, update with the shortest path to the event
     * @param agentRT : the routingTable to compare.
     */
    public void compareTable(HashMap<Integer,ArrayList<Integer>> agentRT){
        for(int key: routingTable.keySet()){
            if(agentRT.containsKey(key)){
                if(agentRT.get(key).get(0)<routingTable.get(key).get(0)){
                    //If agent has a shorter path, update the routingTable in the node.
                    routingTable.get(key).set(0,agentRT.get(key).get(0));
                    routingTable.get(key).set(1,agentRT.get(key).get(1));
                }
                else if(agentRT.get(key).get(0)>routingTable.get(key).get(0)){
                    //If node has a shorter path, update the routingTable in the agent.
                    agentRT.get(key).set(0,routingTable.get(key).get(0));
                    agentRT.get(key).set(1,routingTable.get(key).get(1));
                }
            }
            else{
                //If the node has an event the agent doesn't, add it.
                agentRT.put(key, routingTable.get(key));
            }
        }
        for(int key: agentRT.keySet()){
            if(routingTable.containsKey(key)){
                if(agentRT.get(key).get(0)<routingTable.get(key).get(0)){
                    //If agent has a shorter path, update the routingTable in the node.
                    routingTable.get(key).set(0,agentRT.get(key).get(0));
                    routingTable.get(key).set(1,agentRT.get(key).get(1));
                }
                else if(agentRT.get(key).get(0)>routingTable.get(key).get(0)){
                    //If node has a shorter path, update the routingTable in the agent.
                    agentRT.get(key).set(0,routingTable.get(key).get(0));
                    agentRT.get(key).set(1,routingTable.get(key).get(1));
                }
            }
            else{
                //If the agent has an event the node doesn't, add it.
                routingTable.put(key, agentRT.get(key));
            }
        }
    }

    public void addEvent(){


    }

    public void addNeighbour(Node neigh){

    }





}
