// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(1);
        //assertTrue (c.value() == 0);
        assertNotNull(c);
        assertEquals (c, c);
        //assertNull(c);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 0);
        c.increment();
        assertTrue(c.value() == 1);

    }

    public void testReset() {
        ModNCounter c = new ModNCounter(1);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}