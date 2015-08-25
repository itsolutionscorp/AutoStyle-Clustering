import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testConstructor1() {
		Measurement a = new Measurement();
		assertTrue(a.getFeet() == 0);
		assertTrue(a.getInches() == 0);
	}
	
	public void testConstructor2() {
		Measurement a = new Measurement(5);
		assertTrue(a.getFeet() == 5);
		assertTrue(a.getInches() == 0);
	}
	
	public void testConstructor3() {
		Measurement a = new Measurement(5,7);
		assertTrue(a.getFeet() == 5);
		assertTrue(a.getInches() == 7);
		
		Measurement b = new Measurement(5,13);
		assertTrue(b.getFeet() == 6);
		assertTrue(b.getInches() == 1);
	}
	
	public void testGetFeet() {
		Measurement a = new Measurement(3);
		assertTrue(a.getFeet() == 3);
	}
	
	public void testGetInches() {
		Measurement a = new Measurement(7,9);
		assertTrue(a.getInches() == 9);
	}
	
	public void testPlus() {
		Measurement a = new Measurement(7,9);
		Measurement b = new Measurement(7,3);
		Measurement c = a.plus(b);
		assertTrue(c.getFeet() == 15);
		assertTrue(c.getInches() == 0);  
		
		Measurement d = new Measurement(7,9);
		Measurement e = new Measurement(7,4);
		Measurement f = d.plus(e);
		assertTrue(f.getFeet() == 15);
		assertTrue(f.getInches() == 1);  
	}

	public void testMinus() {
		Measurement a = new Measurement(7,9);
		Measurement b = new Measurement(7,1);
		Measurement c = a.minus(b);
		assertTrue(c.getFeet() == 0);
		assertTrue(c.getInches() == 8);  
		
		Measurement d = new Measurement(9,9);
		Measurement e = new Measurement(7,11);
		Measurement f = d.minus(e);
		assertTrue(f.getFeet() == 1);
		assertTrue(f.getInches() == 10);  
	}

	public void testMultiple() {
		Measurement a = new Measurement(1,9);
		Measurement c = a.multiple(5);
		assertTrue(c.getFeet() == 8);
		assertTrue(c.getInches() == 9);  
		
		Measurement d = new Measurement(7,1);
		Measurement f = d.multiple(5);
		assertTrue(f.getFeet() == 35);
		assertTrue(f.getInches() == 5);  
	}
	
	public void testToString() {
		Measurement a = new Measurement(2,5);
		assertEquals(a.toString(),"2\' 5\"");
		Measurement b = new Measurement(2,13);
		assertEquals(b.toString(),"3\' 1\"");
	}

}
