import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertTrue(m1.feet == 0);
		assertTrue(m1.inches == 0);
		Measurement m2 = new Measurement(1);
		assertTrue(m2.feet == 1);
		assertTrue(m2.inches == 0);
		Measurement m3 = new Measurement(1,2);
		assertTrue(m3.feet == 1);
		assertTrue(m3.inches == 2);
	}
	
	public void testGetfeet() {
		Measurement m1 = new Measurement(1,2);
		assertTrue(m1.getFeet() == 1);
	}
	
	public void testGetinches() {
		Measurement m1 = new Measurement(1,2);
		assertTrue(m1.getInches() == 2);
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(1,2);
		Measurement m2 = new Measurement(5,11);
		Measurement reference;
		reference = m1.plus(m2);
		assertTrue(reference.feet == 7);
		assertTrue(reference.inches == 1);	
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(1,2);
		Measurement m2 = new Measurement(5,1);
		Measurement reference;
		reference = m2.minus(m1);
		assertTrue(reference.feet == 3);
		assertTrue(reference.inches == 11);	
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement(1,2);
		Measurement reference = m1.multiple(8);
		assertTrue(reference.feet == 9);
		assertTrue(reference.inches == 4);
		Measurement m2 = new Measurement(1,1);
		Measurement reference1 = m2.multiple(8);
		assertTrue(reference1.feet == 8);
		assertTrue(reference1.inches == 8);
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(1,2);
		String s = m1.toString();
		assertEquals("1'"+ "2" +'"', s);
	}
  
}
