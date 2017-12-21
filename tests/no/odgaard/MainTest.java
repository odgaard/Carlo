package no.odgaard;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void helloWorld() throws Exception {
        Main myClass = new Main();
        assertEquals("Hello world!", myClass.helloWorld());
    }
}