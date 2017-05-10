/**
 * Class that handles the information about an event.
 * Created by oi12mnd on 2017-05-05.
 */

public class Event {
    private int id, time;
    private Position position;

    /** Stores the information about an event.
     * @param eventPosition The position of the event.
     * @param eventId The id of the event.
     * @param eventTime The time of the event.
     */
    public Event(Position eventPosition, int eventId, int eventTime){
        position = eventPosition;
        id = eventId;
        time = eventTime;
    }

    /** Get the id of the event.
     * @return The id of the event.
     * */
    public int getId(){
        return id;
    }

    /** Get the time of the event.
     * @return The time of the event.
     * */
    public int getTime(){
        return time;
    }

    /** Get the event position.
     * @return The position of the event.
     * */
    public Position getPosition(){
        return position;
    }
}