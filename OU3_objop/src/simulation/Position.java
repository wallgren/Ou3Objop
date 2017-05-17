package simulation;

/**
 * Class - Position class to define positions in the grid.
 * Created by grupp 8 on 2017-05-05
 */
public class Position {
    private int x;
    private int y;

    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Description: Returns the x-value of the position.
     * @return x-coordinate.
     */
    public int getX(){
        return x;
    }
    /**
     * Description: Returns the y-value of the position.
     * @return y-coordinate.
     */
    public int getY(){
        return y;
    }

    /**
     * Description: Sets the x and y of the position to the requested values.
     * @param x : The x-value requested.
     * @param y : The y-value requested.
     */
    public void setPos(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Method   - Compares two positions if they're equal.
     * @param o: Is the object to Compare.
     * @return boolean: True if the objects are equal, false otherwise.
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
     * Method  - Returns the hashCode for a position.
     * @return The hascode for a position.
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
