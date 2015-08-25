import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit() {
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
		Measurement m2 = new Measurement(1);
		assertTrue(m2.getFeet() == 1);
		assertTrue(m2.getInches() == 0);
		Measurement m3 = new Measurement(1, 1);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 1);		
	}
	
	public void testGetFeet() {
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet() == 0);		
		Measurement m2 = new Measurement(1);
		assertTrue(m2.getFeet() == 1);		
		Measurement m3 = new Measurement(1, 1);
		assertTrue(m3.getFeet() == 1);	
	}
	
	public void testGetInches() {
		Measurement m1 = new Measurement();
		assertTrue(m1.getInches() == 0);		
		Measurement m2 = new Measurement(1);
		assertTrue(m2.getInches() == 0);		
		Measurement m3 = new Measurement(1, 1);
		assertTrue(m3.getInches() == 1);			
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(1, 5);
		Measurement m2 = new Measurement(1, 8);
		m1.plus(m2);
		assertTrue(m1.getFeet() == 3);
		assertTrue(m1.getInches() == 1);
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(3, 5);
		Measurement m2 = new Measurement(1, 8);
		m1.minus(m2);
		assertTrue(m1.getFeet() == 1);
		assertTrue(m1.getInches() == 9);
		
		Measurement m3 = new Measurement(1, 5);
		Measurement m4 = new Measurement(1, 8);
		m3.minus(m4);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 5);		
	}
	
	public void testMultiple() {
		Measurement m = new Measurement(1, 6);
		m.multiple(5);
		assertTrue(m.getFeet() == 7);
		assertTrue(m.getInches() == 6);
		Measurement m2 = new Measurement(1, 0);
		m2.multiple(-1);
		assertTrue(m2.getFeet() == 1);
		assertTrue(m2.getInches() == 0);
	}
	
	public void testToString() {
		Measurement m = new Measurement(3, 5);
		assertTrue(m.toString().equals("3\'5\""));
	}
	
}
