import static org.junit.Assert.*;

import org.junit.Test;


public class ModNCounterTest {

	@Test
	public void testConstructor() {
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		// Second Test
		ModNCounter modCounterb = new ModNCounter(3);
		modCounterb.increment();
		modCounterb.increment();
		modCounterb.increment();
		modCounterb.increment();
		System.out.println(modCounterb.value()); // prints 1
	}

	
}
