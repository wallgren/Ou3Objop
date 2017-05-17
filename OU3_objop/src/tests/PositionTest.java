package tests;
import simulation.Position;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test - Junit test that makes sure that the Agent class works as intended
 * Created by grupp 8 on 2017-05-15.
 */
public class PositionTest {
    /**
     * Makes sure a Position cna be created
     */
    @Test
    public void canCreatePosition(){
        new Position(0,0);
    }

    /**
     * Tests that position can handle negative coordinates, as this is a
     * very important feature in our system.
     */
    @Test
    public void shouldBeAbleToCreatePositionWithNegativeXY(){
        new Position(-1,-2);
        new Position(-5,0);
        new Position(5,-3);
    }

    /**
     * Makes sure getX and getY works
     */
    @Test
    public void getXYReturnsCorrect(){
        Position a = new Position(13,57);
        assertEquals("simulation.Position.getX returns incorrect value",
                a.getX(), 13);
        assertEquals("simulation.Position.getY returns incorrect value",
                a.getY(), 57);
    }

    /**
     * makes sure setting a position sets the position correctly
     */
    @Test
    public void setPosSetsPosition(){
        Position p=new Position(0,0);
        p.setPos(15,20);
        assertEquals("simulation.Position.setPos sets incorrect X",
                p.getX(), 15);
        assertEquals("simulation.Position.setPos sets incorrect Y",
                p.getY(), 20);
    }

    /**
     * Makes sure equal works
     */
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

    /**
     * makes sure equal works
     */
    @Test
    public void unequalPositionsShouldMarkAsUnequal(){
        Position p1=new Position(5,13);
        Position p2=new Position(7,10);
        assertFalse(p1.equals(p2));
        assertFalse(p2.equals(p1));
    }
}
