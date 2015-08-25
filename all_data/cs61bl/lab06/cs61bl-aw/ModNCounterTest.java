import static org.junit.Assert.*;

import org.junit.Test;


public class ModNCounterTest {

	@Test
	public void testIncrement() {
		ModNCounter s = new ModNCounter(4);
		s.increment();
		s.increment();
		assertTrue (s.value() == 2);
		s.reset();
		assertTrue (s.value() == 0);

		s.increment();
		s.increment();
		s.increment();
		s.increment();
		assertTrue (s.value() == 0);	
		}
	public void testValue() {
		ModNCounter s = new ModNCounter(6);

		s.increment();
		s.increment();
		s.increment();
		s.increment();
		assertTrue (s.value() == 4);	
	}
	public void testReset() {
		ModNCounter s = new ModNCounter(9);
		s.increment();
		s.increment();
		assertTrue (s.value() == 2);
		s.reset();
		assertTrue (s.value() == 0);
	}

}
