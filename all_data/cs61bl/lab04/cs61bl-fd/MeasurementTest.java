import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement test = new Measurement();
		assertTrue (test.getFeet() == 0);
		assertTrue (test.getInches() == 0);
	}
	
	public void testGetFeet(){
		Measurement test = new Measurement(3, 6);
		assertTrue (test.getFeet() == 3);
		
	}
	
	public void testGetInches(){
		Measurement test = new Measurement(3, 6);
		assertTrue (test.getInches() == 6);
		
	}
	
	public void testAddmeasurement(){
		Measurement test = new Measurement(3, 6);
		Measurement test2 = new Measurement(2, 5);
		Measurement test3 = new Measurement();
		test3 = test.plus(test2);
		assertTrue (test3.getFeet() == 5);
		
	
	}
	
	public void testMinusmeasurement() {
		Measurement test = new Measurement(3, 6);
		Measurement test2 = new Measurement(1, 1);
		Measurement test3 = new Measurement();
		test3 = test.minus(test2);
		assertTrue (test3.getFeet() == 2);
	}
    public void testMultiplemeasurement(){
	   Measurement test = new Measurement(1, 2);
	   Measurement test3 = new Measurement();
	   test3 = test.multiple(2);
	   assertTrue (test3.getFeet() == 2);
	   assertTrue (test3.getInches() == 4);
   }
   
   public void testString() {
	   Measurement test = new Measurement(3, 6);
	   assertEquals ("3'6\"", test.toString());
	   
   }
}
