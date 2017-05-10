/**
 * Created by c16gwn on 2017-05-05
 */
public class Position {
    private int x;
    private int y;

    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Description: returns the x-value of the position
     * @return attribute x
     */
    public int getX(){
        return x;
    }
    /**
     * Description: returns the y-value of the position
     * @return attribute y
     */
    public int getY(){
        return y;
    }

    /**
     * Description: sets the x and y of the position to the requested values
     * @param x : the x-value requested
     * @param y : the y-value requested
     */
    public void setPos(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Method   - Compares two positions if they're equal
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    /**
     * Method  - returns the hashCode for a position
     * @return
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}