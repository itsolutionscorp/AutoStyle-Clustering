import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
	public void testInit(){
		//type 1 constructor, 0 argument
		Measurement a = new Measurement();
		assertTrue(a.getFeet() == 0);
		assertTrue(a.getInches() == 0);
		
		//type 2 constructor, 1 argument
		Measurement b = new Measurement(5);
		assertTrue(b.getFeet() == 5);
		assertTrue(b.getInches() == 0);
		
		//type 3 constructor, 2 arguments
		Measurement c = new Measurement(0, 28);
		assertTrue(c.getFeet() == 2);
		assertTrue(c.getInches() == 4);
	}
	
	//test getFeet() method and getInches() method
	public void testGet(){
		Measurement a = new Measurement(4, 5);
		assertTrue(a.getFeet() == 4);
		assertTrue(a.getInches() == 5);
	}
	
	//test plus, minus, multiple methods
	public void testOperation(){
		Measurement a = new Measurement(4, 5);
		Measurement b = new Measurement(1, 11);
		
		// test plus
		Measurement c = a.plus(b);
		assertTrue(c.getFeet() == 6);
		assertTrue(c.getInches() == 4);
		
		// test minus
		c = a.minus(b);
		assertTrue(c.getFeet() == 2);
		assertTrue(c.getInches() == 6);
		
		// test multiple
		c = b.multiple(5);
		assertTrue(c.getFeet() == 9);
		assertTrue(c.getInches() == 7);
	}
	
	//test toString() method
	public void testtoString(){
		Measurement a = new Measurement(4, 5);
		assertTrue(a.toString().equals("4'5\""));
	}
}
