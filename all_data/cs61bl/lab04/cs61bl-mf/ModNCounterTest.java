import junit.framework.TestCase;

	public class ModNCounterTest extends TestCase {
	    /*public void testConstructor() {
	        Counter c = new Counter();
	        assertTrue (c.value() == 0);
	    }

	    public void testIncrement() {
	        Counter c = new Counter();
	        c.increment();
	        assertTrue (c.value()  == 1);
	        c.increment();
	        assertTrue (c.value() == 2);
	    }

	    public void testReset() {
	        Counter c = new Counter();
	        c.increment();
	        c.reset();
	        assertTrue (c.value() == 0); 
	    } */
	    
	    public void testWraparound() {
	    	ModNCounter c = new ModNCounter(2);
	    	assertTrue("One failed", c.value() == 0);
	    	c.increment();
	    	assertTrue("Two failed", c.value() == 1);
	    	c.increment();
	    	assertTrue("three failed", c.value() == 0);
	    }

	}
