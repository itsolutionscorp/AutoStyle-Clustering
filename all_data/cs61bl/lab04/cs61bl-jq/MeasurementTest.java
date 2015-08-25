import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
        Measurement c = new Measurement(5,13);
        assertTrue (c.getFeet()==6);
        assertTrue (c.getInches()==1);
    }

    public void testPlus() {
    	Measurement a = new Measurement(4,11);
    	Measurement d = new Measurement(1,2);
    	Measurement c = a.plus(d);
        assertTrue (c.getFeet()==6);
        assertTrue (c.getInches()==1);
    }
    
    public void testMinus() {
    	Measurement a = new Measurement(7,10);
    	Measurement d = new Measurement(1,9);
    	Measurement c = a.minus(d);
        assertTrue (c.getFeet()==6);
        assertTrue (c.getInches()==1);
    }
    
    public void testMultiple() {
    	Measurement a = new Measurement(3,1);
    	Measurement c = a.multiple(2);
        assertTrue (c.getFeet()==6);
        assertTrue (c.getInches()==2);
    }
}
