/**
 * Created by Max Holmberg on 2017-05-16.
 */
public class Guide {
    private int steps;
    private int direction;
    public Guide(int steps, int direction){
        this.steps = steps;
        this.direction = direction;
    }

    public int getSteps(){return steps;}
    public int getDirection(){return direction;}

    public void setStepsAndDirection(int steps, int direction){
        this.steps = steps;
        this.direction = direction;
    }
}
