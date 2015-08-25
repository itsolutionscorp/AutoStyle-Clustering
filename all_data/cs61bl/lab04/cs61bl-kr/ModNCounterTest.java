import junit.framework.TestCase;


public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(3);
        assertTrue (c.mod() == 3);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(3);
        c.increment();
        assertTrue (c.value() == 1);
        c.increment();
        assertTrue (c.value() == 2);
        c.increment();
        assertTrue (c.value() == 0);
        c.increment();
        assertTrue (c.value() == 1);
        c.increment();
        assertTrue (c.value() == 2);
        ModNCounter d = new ModNCounter(4);
        d.increment();
        assertTrue (d.value() == 1);
        d.increment();
        assertTrue (d.value() == 2);
        d.increment();
        assertTrue (d.value() == 3);
        d.increment();
        assertTrue (d.value() == 0);
        d.increment();
        assertTrue (d.value() == 1);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}
