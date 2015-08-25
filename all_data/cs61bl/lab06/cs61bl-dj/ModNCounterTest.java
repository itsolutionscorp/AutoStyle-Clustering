import junit.framework.TestCase;


	// JUnit test case
	import junit.framework.TestCase;

	public class ModNCounterTest extends TestCase {
	    public void testConstructor() {
	        ModNCounter c = new ModNCounter(4);
	        assertTrue (c.value() == 0);
	    }

	    public void testIncrement() {
	        ModNCounter c = new ModNCounter(4);
	        c.increment();
	        assertTrue (c.value()  == 1);
	        c.increment();
	        assertTrue (c.value() == 2);
	        c.increment();
	        assertTrue(c.value() == 3);
	        c.increment();
	        assertTrue(c.value() == 4);
	        c.increment();
	        assertTrue(c.value() == 0);
	        c.increment();
	        assertTrue(c.value() == 1);
	        ModNCounter modCounter = new ModNCounter(3);
	        modCounter.increment();
	        modCounter.increment();
	        modCounter.increment();
	        modCounter.increment();
	        System.out.println(modCounter.value()); // prints 1
	    }

	    public void testReset() {
	        ModNCounter c = new ModNCounter(4);
	        c.increment();
	        c.reset();
	        assertTrue (c.value() == 0);
	 
	    }

	}

