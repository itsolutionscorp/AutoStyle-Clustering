// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(2);
        assertTrue (c.value() == 0);
        assertTrue (c.myN == 2);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 0);
        c.increment();
        assertTrue (c.value() == 1);
        ModNCounter c2 = new ModNCounter(4);
        assertTrue (c2.value()  == 0);
        c2.increment();
        c2.increment();
        c2.increment();
        c2.increment();
        assertTrue (c2.value()  == 0);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}