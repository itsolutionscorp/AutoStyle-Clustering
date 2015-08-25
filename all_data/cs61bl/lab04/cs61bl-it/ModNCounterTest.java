import junit.framework.TestCase;


public class ModNCounterTest extends TestCase {
	public void testConstructor() {
        ModNCounter c = new ModNCounter(3);
        assertTrue (c.value()[0] == 0);
        assertTrue (c.value()[1] == 3);
	}
    public void testIncrement() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue (c.value()[0] == 1);
        assertTrue (c.value()[1] == 2);
        c.increment();
        assertTrue (c.value()[0] == 0);
        assertTrue (c.value()[1] == 2);
    }
    public void testReset() {
        ModNCounter c = new ModNCounter(4);
        c.increment();
        c.reset();
        assertTrue (c.value()[0] == 0);
        assertTrue (c.value()[1] == 4);
    }
}
