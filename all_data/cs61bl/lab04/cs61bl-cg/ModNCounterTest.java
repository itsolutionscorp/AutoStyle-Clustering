


//JUnit test case
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
		
		// Check the wraparound, should reset myCounter only when it hits the value N
		c = new ModNCounter(3);
		c.increment();
		c.increment();
		assertTrue (c.value() != 0);
		c.increment();
		assertTrue (c.value() == 0);
	}

	public void testReset() {
		ModNCounter c = new ModNCounter();
		c.increment();
		c.reset();
		assertTrue (c.value() == 0);
	}
	
	public void testConstructorWithArgument() {
		ModNCounter c = new ModNCounter(9);
		assertTrue (c.certainValue() == 9);
	}

}