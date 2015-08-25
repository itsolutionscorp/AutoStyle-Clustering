import junit.framework.TestCase;

//JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
	public void testConstructor() {
		ModNCounter c = new ModNCounter(5);
		assertTrue(c.value() == 0);
	}

	public void testIncrement() {
		ModNCounter c = new ModNCounter(5);
		c.increment();
		assertTrue(c.value() == 1);
		c.increment();
		assertTrue(c.value() == 2);
		c.increment();
		c.increment();
		c.increment();
		assertTrue(c.value() == 0);
		
	}

	public void testReset() {
		ModNCounter c = new ModNCounter(5);
		c.increment();
		c.reset();
		assertTrue(c.value() == 0);
	}

}