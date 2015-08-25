import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(2);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue (c.value() == 1);
        c.increment();
        assertTrue (c.value() == 0);
        c.increment();
        assertTrue (c.value() == 1);
        
        ModNCounter b = new ModNCounter(4);
        b.increment();
        assertTrue(b.value() == 1);
        b.increment();
        b.increment();
        assertTrue(b.value() == 3);
        b.increment();
        assertTrue(b.value() == 0);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}