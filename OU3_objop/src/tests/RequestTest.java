package tests;

import simulation.*;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

import java.util.ArrayList;

/**
 * Created by oi12mnd on 2017-05-16.
 */
public class RequestTest {
    @Test
    public void justCreatedRequestShouldNotBeMarkedAsReturned(){
        Request r=new Request(new Node(new Position(0,0)), 1, 10);
        assertFalse(r.hasReturned());
    }

}

