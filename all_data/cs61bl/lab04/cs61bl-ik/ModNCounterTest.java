// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(5);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(4);
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 2);
        c.increment();
        assertTrue (c.value() == 3);
        c.increment();
        assertTrue (c.value() == 0);
        c.increment();
        assertTrue (c.value() == 1);
        
        ModNCounter p = new ModNCounter(2);
        p.increment();
        assertTrue (p.value() == 1);
        p.increment();
        assertTrue (p.value() == 0);
        p.increment();
        assertTrue (p.value() == 1);
        p.increment();
        assertTrue (p.value() == 0);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}