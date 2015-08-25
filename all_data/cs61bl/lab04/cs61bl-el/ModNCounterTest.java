import static org.junit.Assert.*;

import org.junit.Test;
//JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
	int N = 3;

	public void testConstructor() {
		ModNCounter c = new ModNCounter(N);
		assertTrue(c.value() == 0);
	}

	public void testIncrement() {
		ModNCounter c = new ModNCounter(N);
		c.increment();
		assertTrue(c.value() == 1);
		c.increment();
		assertTrue(c.value() == 2);
		c.increment();
		assertTrue(c.value() == 0);
		c.increment();
		assertTrue(c.value() == 1);

	}
	
	public void testReset() {
		ModNCounter c = new ModNCounter(N);
		c.increment();
		c.reset();
		assertTrue(c.value() == 0);
		
	}
}
