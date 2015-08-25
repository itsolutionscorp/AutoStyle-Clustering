import static org.junit.Assert.*;

import org.junit.Test;


public class CounterTest {

	@Test
	public void test() {
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		assert(modCounter.value()==1); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
	}

}
