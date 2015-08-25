//JUnit test case
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
     if (c.value() == c.myN()) {
    	 c.increment();
    	 assertTrue (c.value() == 0); 
     	}
     }

 public void testReset() {
     ModNCounter c = new ModNCounter(4);
     c.increment();
     c.reset();
     assertTrue (c.value() == 0);
 }

}