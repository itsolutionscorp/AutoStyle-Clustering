import junit.framework.TestCase;




public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter();
        assertTrue (c.value() == 0);
        assertTrue(c.max == 0);
    }
    public void testConstrctorOneArg(){
    	ModNCounter c = new ModNCounter(4);
    	assertTrue(c.value() == 0);
    	assertTrue(c.max == 4);
    	c.increment();
    	c.increment();
    	c.increment();
    	c.increment();
    	c.increment();
    	assertTrue(c.value() == 1);
    	c.increment();
    	assertTrue(c.value()==2);
    	c.increment();
    	assertTrue(c.value() == 3);
    	
    	}

    public void testIncrement() {
        ModNCounter c = new ModNCounter();
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 2);
        ModNCounter WithMax = new ModNCounter(5);
        assertTrue(WithMax.value() == 0);
        WithMax.increment();
        assertTrue(WithMax.value() == 1);
        WithMax.increment();
        WithMax.increment();
        WithMax.increment();
        WithMax.increment();
        assertTrue(WithMax.value() == 0);
        WithMax.increment();
        assertTrue(WithMax.value() == 1);
        WithMax.increment();
        assertTrue(WithMax.value() == 2);
        WithMax.increment();
        assertTrue(WithMax.value() == 3);
        WithMax.increment();
        assertTrue(WithMax.value() == 4);
        WithMax.increment();
        assertTrue(WithMax.value() == 0);
        
        
    }

    public void testReset() {
        ModNCounter c = new ModNCounter();
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}