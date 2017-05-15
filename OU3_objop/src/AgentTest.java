import org.junit.Test;

/**
 * Created by c16gwn on 2017-05-15.
 */
public class AgentTest {

    @Test
    public void canCreateAgent(){
        new Agent(new Node(new Position(0,0)),50);
    }

}
