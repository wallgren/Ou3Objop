import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class - JUnit tests that test the functionality of the class Position
 */
public class PositionTest {

    /**
     * Test - Creating 100*100 different positions and tests if
     *        everyone can be created and that they have the
     *        expected position
     */
    @Test
    public void shouldBeExpectedPos(){
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                Position p = new Position(x, y);
                assertEquals(p.getX(), x);
                assertEquals(p.getY(), y);
            }
        }
    }

    /**
     * Test - Two equal Positions should be equal when comparing with the
     *        equal method
     */
    @Test
    public void shouldBeEqual(){
        assertTrue(new Position(1, 1).equals(new Position(1, 1)));
    }

    /**
     * Test - Two equal Positions should have the same hashCode
     */
    @Test
    public void shouldHaveEqualHashCode(){
        assertEquals(new Position(1, 1).hashCode(), new Position(1, 1).hashCode());
    }


}
