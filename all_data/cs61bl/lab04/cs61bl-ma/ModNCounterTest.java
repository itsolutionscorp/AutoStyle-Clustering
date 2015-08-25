import junit.framework.TestCase;


//JUnit test case
import junit.framework.TestCase;

public class ModNCounterTest extends TestCase {
 public void testConstructor(int n) {
     ModNCounter c = new ModNCounter(n);
     assertTrue (c.value() == 0);
 }

 public void testIncrement() {
     ModNCounter c = new ModNCounter(4);
     c.increment();
     assertTrue (c.value()  == 1);
     c.increment();
     assertTrue (c.value() == 2);
     c.increment();
     assertTrue (c.value()==3);
     c.increment();
     assertTrue(c.value()==0);
 }

 public void testReset() {
     ModNCounter c = new ModNCounter(4);
     c.increment();
     c.reset();
     assertTrue (c.value() == 0);
 }

}