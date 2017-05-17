package simulation;

/**
 * Class - A class to represent steps and direction in the RoutingTable.
 * Created by grupp 8 on 2017-05-16.
 */
public class Guide {
    private int steps;
    private int direction;

    /**
     * Constructor - Creates a Guide class with given steps and direction.
     * @param steps: Number of steps to an event.
     * @param direction: The direction to an event.
     */
    public Guide(int steps, int direction){
        this.steps = steps;
        this.direction = direction;
    }

    /**
     * Method - Getter to get the steps.
     * @return int: The amount of steps.
     */
    public int getSteps(){return steps;}

    /**
     * Method - Getter to get the direction.
     * @return int: The direction represented as an integer.
     */
    public int getDirection(){return direction;}

    /**
     * Method - Sets the steps and direction.
     * @param steps: Number of steps to an event.
     * @param direction: The direction to an event.
     */
    public void setStepsAndDirection(int steps, int direction){
        this.steps = steps;
        this.direction = direction;
    }

    /**
     * Method - Checks if two Guides are equal to each other.
     * @param o: Object to compare.
     * @return boolean: True if objects are equivalent, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guide guide = (Guide) o;

        if (steps != guide.steps) return false;
        return direction == guide.direction;
    }

    /**
     * Method - Returns a hashCode for a Guide.
     * @return int: The hashcode for a guide.
     */
    @Override
    public int hashCode() {
        int result = steps;
        result = 31 * result + direction;
        return result;
    }
}

