/**
 * This class represents a message (Request or Agent) that is created on the grid.
 * Created by oi12mnd on 2017-05-09.
 */
import java.util.Stack;
public abstract class Message {
    protected int MAXJUMPS, jumps = 0;
    protected Stack<Node> path = new Stack<>();
    protected Node currNode, nextNode;

    /** Creates a new message.
     * @param MAXJUMPS The maximum number of jumps that a message can make.
     * @param startNode The node where the message is created.
     */
    public Message(Node startNode, int MAXJUMPS){
        this.MAXJUMPS = MAXJUMPS;
        currNode = startNode;
        path.push(currNode);
    }

    /** Abstract method that updates the updates the status of the message. */
    public abstract void update();

    /** Abstract method that finds the next node to visit. */
    public abstract Node findNextNode();

    /** Function that moves a message one step and adds the next ode to its path. */
    public void move(){
        currNode.removeFirstElement();
        currNode = nextNode;
        currNode.addMessageToQueue(this);
        jumps++;
        path.push(currNode);
    }
}

