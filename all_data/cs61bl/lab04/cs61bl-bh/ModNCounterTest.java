import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;


public class ModNCounterTest extends TestCase {
    public void testConstructor() {
        ModNCounter c = new ModNCounter(2);
        assertTrue (c.value() == 0);

    }

    public void testIncrement() {
    	ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue (c.value()  == 1);
        c.increment();
        assertTrue (c.value() == 0);
    }

    public void testReset() {
    	ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}
