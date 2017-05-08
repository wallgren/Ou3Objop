import java.util.Random;

/**
 * Abstract Class - Abstract superclass to Request and Agent
 */
public abstract class Message {
    protected Random rand = new Random();

    public abstract void move();

}
