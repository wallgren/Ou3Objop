package tests;
import simulation.*;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

import java.util.ArrayList;

/**
 * Test - Junit test that makes sure that the Agent class works as intended
 * Created by grupp 8 on 2017-05-05.
 */
public class RequestTest {
    /**
     * Tests that a request can be created
     */
    @Test
    public void canCreateRequest(){
        new Request(new Node(new Position(0,0)), 1, 10);
    }

    /**
     * Makes sure a request that has not found its event is not marked as equal
     */
    @Test
    public void justCreatedRequestShouldNotBeMarkedAsReturned(){
        Request r=new Request(new Node(new Position(0,0)), 1, 10);
        assertFalse(r.hasReturned());
    }

}

