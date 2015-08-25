import static org.junit.Assert.*;

import org.junit.Test;

public class ModNCounterTest {

	@Test
	public void testConstructor() {
	     Counter c = new Counter();
	     assertTrue (c.value() == 0);
	 }

	 public void testIncrement() {
	     Counter c = new Counter();
	     c.increment();
	     assertTrue (c.value()  == 1);
	     c.increment();
	     assertTrue (c.value() == 2);
	     c.increment();
	     assertTrue (c.value() == 3);
	     c.increment();
	     assertTrue (c.value() == 4);
	     c.increment();
	     assertTrue (c.value() == 5);
	 }

	 public void testReset() {
	     Counter c = new Counter();
	     c.increment();
	     c.reset();
	     assertTrue (c.value() == 0);
	 }
}
