import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() throws Exception {
        ModNCounter c = new ModNCounter(3);
        assertTrue(c.value() == 0);
        assertTrue(c.n() == 3);
    }

    public void testIncrement() throws Exception {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue(c.value() == 1);
        c.increment();
        assertTrue(c.value() == 0);
    }

    public void testReset() throws Exception {
        ModNCounter c = new ModNCounter(3);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }
}