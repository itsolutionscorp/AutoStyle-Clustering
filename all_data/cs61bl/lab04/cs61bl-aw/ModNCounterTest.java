import junit.framework.TestCase;

public class CounterTest extends TestCase {
    public void testConstructor() {
    	int n=5;
        ModNCounter c = new ModNCounter(n);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() {
    	int n=5;
        ModNCounter c = new ModNCounter(n);
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 2);
        c.increment();
        assertTrue (c.value() == 3);
        c.increment();
        assertTrue (c.value() == 4);
        c.increment();
        assertTrue (c.value() == 0);
        c.increment();
        assertTrue (c.value()  == 1);
    }

    public void testReset() {
    	int n=5;
        ModNCounter c = new ModNCounter(n);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}
