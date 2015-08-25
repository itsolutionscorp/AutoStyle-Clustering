import junit.framework.TestCase;


public class ModNCounterTest extends TestCase {
	
	public void testIncrement() {
		ModNCounter c = new ModNCounter(2);
		assertEquals(0, c.value());
		c.increment();
		assertEquals(1, c.value());
		c.increment();
		assertEquals(0, c.value());
		
		
	}
	
	
}
