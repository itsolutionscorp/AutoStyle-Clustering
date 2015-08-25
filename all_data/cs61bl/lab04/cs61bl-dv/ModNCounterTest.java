import junit.framework.TestCase;


public class ModNCounterTest extends TestCase {

	public void testConstructor() {
		ModNCounter c = new ModNCounter(2);
		assertTrue(c.value() == 0);
	}
	
	public void testIncrement() {
		ModNCounter c1 = new ModNCounter(2);
		assertTrue(c1.value() == 0);
		c1.increment();
		assertTrue(c1.value() == 1);
		c1.increment();
		assertTrue(c1.value() == 0);

		ModNCounter c2 = new ModNCounter(4);
		assertTrue(c2.value() == 0);
		c2.increment();
		assertTrue(c2.value() == 1);
		c2.increment();
		assertTrue(c2.value() == 2);
		c2.increment();
		assertTrue(c2.value() == 3);
		c2.increment();
		assertTrue(c2.value() == 0);
	}
	
	public void testReset() {
		ModNCounter c = new ModNCounter(2);
		c.increment();
		c.reset();
		assertTrue(c.value() == 0);
	}
	
}
