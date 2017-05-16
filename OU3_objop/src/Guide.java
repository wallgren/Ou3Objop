/**
 * Created by Max Holmberg on 2017-05-16.
 * Class - A class to represent steps and direction in the RoutingTable
 */
public class Guide {
    private int steps;
    private int direction;

    /**
     * Constructor - Creates a Guide class with given steps and direction
     * @param steps
     * @param direction
     */
    public Guide(int steps, int direction){
        this.steps = steps;
        this.direction = direction;
    }

    /**
     * Method - Getter to get the steps
     * @return int
     */
    public int getSteps(){return steps;}

    /**
     * Method - Getter to get the direction
     * @return int
     */
    public int getDirection(){return direction;}

    /**
     * Method - Sets the steps and direction
     * @param steps
     * @param direction
     */
    public void setStepsAndDirection(int steps, int direction){
        this.steps = steps;
        this.direction = direction;
    }
}

