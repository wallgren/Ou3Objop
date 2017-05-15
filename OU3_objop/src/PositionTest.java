import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by c16gwn on 2017-05-15.
 */
public class PositionTest {
    @Test
    public void canCreatePosition(){
        new Position(0,0);
    }
    @Test
    public void shouldBeAbleToCreatePositionWithNegativeXY(){
        new Position(-1,-2);
        new Position(-5,0);
        new Position(5,-3);
    }

    @Test
    public void getXYReturnsCorrect(){
        Position a = new Position(13,57);
        assertEquals("Position.getX returns incorrect value", a.getX(), 13);
        assertEquals("Position.getY returns incorrect value",a.getY(), 57);
    }

    @Test
    public void setPosSetsPosition(){
        Position p=new Position(0,0);
        p.setPos(15,20);
        assertEquals("Position.setPos sets incorrect X", p.getX(), 15);
        assertEquals("Position.setPos sets incorrect Y", p.getY(), 20);
    }

    @Test
    public void equalPositionsShouldMarkAsEqual(){
        Position p1=new Position(5,13);
        Position p2=new Position(5,13);
        Position p3=new Position(0,0);

        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));

        assertFalse(p1.equals(p3));
        p3.setPos(5,13);
        assertTrue(p1.equals(p3));
    }

    @Test
    public void unequalPositionsShouldMarkAsUnequal(){
        Position p1=new Position(5,13);
        Position p2=new Position(7,10);
        assertFalse(p1.equals(p2));
        assertFalse(p2.equals(p1));
    }
}
