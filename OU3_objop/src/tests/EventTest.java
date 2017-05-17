package tests;

import simulation.Event;
import simulation.Position;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test - Junit test that makes sure that the Event class works as intended
 * Created by grupp 8 on 2017-05-05.
 */
public class EventTest {

    /** Test that an event is created */
    @Test
    public void eventIsCreated() {
        new Event(new Position(0, 0), 1, 1);
    }

    /** Test that an event returns the correct position */
    @Test
    public void correctPositionReturned() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Event event = new Event(new Position(x, y), 1, 1);
                assertEquals(new Position(x, y), event.getPosition());
            }
        }
    }

    /** Test that an event returns the correct position */
    @Test
    public void correctIdReturned() {
        for (int id = 0; id < 10; id++) {
            Event event = new Event(new Position(1, 1), id, 3);
            assertEquals(id, event.getId());
        }
    }

    /** Test that an event returns the correct time of event */
    @Test
    public void correctTimeReturned() {
        for (int time = 0; time < 10; time++) {
            Event event = new Event(new Position(1, 1), 2, time);
            assertEquals(time, event.getTime());
        }
    }
}
