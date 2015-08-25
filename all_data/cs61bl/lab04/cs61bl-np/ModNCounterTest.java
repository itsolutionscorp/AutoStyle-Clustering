// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
    	int n1 = 0;
        ModNCounter c = new ModNCounter(n1);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
    	int n = 0;
        ModNCounter c = new ModNCounter(n);
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 2);
    }

    public void testReset() {
    	int n3 = 0;
        ModNCounter c = new ModNCounter(n3);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}