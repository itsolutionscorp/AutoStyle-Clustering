// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(3);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(3);
        for (int a=0; a<3; a++) {
        c.increment();
        }
        assertTrue (c.value() == 0);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter(3);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}