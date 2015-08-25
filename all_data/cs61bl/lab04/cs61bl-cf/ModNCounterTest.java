import junit.framework.TestCase;


public class ModNCounterTest extends TestCase {
	public void testConstructor() {
        ModNCounter c = new ModNCounter(2);
        assertTrue (c.value() == 1);
        assertTrue (c.nValue() == 2);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue ((c.value() % c.nValue()) == 0);
        c.increment();
        assertTrue ((c.value() % c.nValue()) == 1);
        c.increment();
        assertTrue ((c.value() % c.nValue()) == 0);
        c.increment();
        c.increment();
    }

    public void testReset() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertTrue (c.value() == 1);
    }
}
