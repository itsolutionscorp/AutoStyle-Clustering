// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter();
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter();
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 2);
        c.increment();
        assertTrue (c.value() == 3);
        ModNCounter c1 = new ModNCounter(3);
        assertTrue (c1.value()  == 0);
        c1.increment();
        assertTrue (c1.value()  == 1);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter();
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}
