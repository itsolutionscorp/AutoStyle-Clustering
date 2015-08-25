import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
    /**
     * Checks that a newly created Measurement has the correct values.
     * Also checks for getFeet() and getInches().
	 */
    public void testConstructor() {
        Measurement a = new Measurement();
        assertTrue(a.getFeet() == 0);
        assertTrue(a.getInches() == 0);
        
        Measurement b = new Measurement(5);
        assertTrue(b.getFeet() == 5);
        assertTrue(b.getInches() == 0);
        
        Measurement c = new Measurement(4, 3);
        assertTrue(c.getFeet() == 4);
        assertTrue(c.getInches() == 3);
    }
    
    /**
     * Checks the plus method adds the values of m2 to the
     * current measurement and does not change the values of m1 or m2.
     */
    public void testPlus() {
    	Measurement a = new Measurement(1,10);
    	Measurement b = new Measurement(1,13);
    	Measurement c = a.plus(b);
    	
        assertTrue(a.getFeet() == 1);
        assertTrue(a.getInches() == 10);
        assertTrue(b.getFeet() == 2);
        assertTrue(b.getInches() == 1);
        
        assertTrue(c.getFeet() == 3);
        assertTrue(c.getInches() == 11);
    }
    
    /**
     * Checks the minus method subtracts the values of m2 to the
     * current measurement and does not change the values of m1 or m2.
     */
    public void testMinus() {
    	Measurement a = new Measurement(2,6);
    	Measurement b = new Measurement(1,5);
    	Measurement c = a.minus(b);
    	
        assertTrue(a.getFeet() == 2);
        assertTrue(a.getInches() == 6);
        assertTrue(b.getFeet() == 1);
        assertTrue(b.getInches() == 5);
        
        assertTrue(c.getFeet() == 1);
        assertTrue(c.getInches() == 1);
    }
    
    /**
     * Checks the multiple method multiplies the values of m1 and
     * m2 and doesn't result in any changed measurements.
     */
    public void testMultiple() {
    	Measurement a = new Measurement(2,6);
    	Measurement b = new Measurement(1,5);
    	
    	Measurement c = a.multiple(2);
    	Measurement d = b.multiple(5);
    	
        assertTrue(a.getFeet() == 2);
        assertTrue(a.getInches() == 6);
        assertTrue(b.getFeet() == 1);
        assertTrue(b.getInches() == 5);
        
        assertTrue(c.getFeet() == 5);
        assertTrue(c.getInches() == 0);
        assertTrue(d.getFeet() == 7);
        assertTrue(d.getInches() == 1);
    }

}
