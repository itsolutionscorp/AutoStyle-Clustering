// JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter();
        assertTrue (c.value() == 0);
    }
    
    public void testConstructorN() {
    	ModNCounter c = new ModNCounter(5);
    	assertTrue (c.N == 5);
    }

    public void testIncrement() {
        ModNCounter c = new ModNCounter();
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 2);
    }

    public void testReset() {
        ModNCounter c = new ModNCounter();
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }
    
    public void testResetN() {
    	ModNCounter c = new ModNCounter(5);
    	c.increment();
    	c.increment();
    	c.increment();
    	c.increment();
    	c.increment();
    	assertTrue (c.value() == 0);
    }

}