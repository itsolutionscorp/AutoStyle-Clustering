import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertTrue (m1.getFeet() == 0);
		assertTrue (m1.getInches() == 0);
	}
	public void testMakeFeet() {
		Measurement m2 = new Measurement(2);
		assertTrue (m2.getFeet() == 2);
		assertTrue (m2.getInches() == 0);
	}
	public void testMakeFeetInches() {
		Measurement m3 = new Measurement(1 , 3);
		assertTrue (m3.getFeet() == 1);
		assertTrue (m3.getInches() == 3);
	}
	
	public void testGetFeet() {
		Measurement m1 = new Measurement(1);
		assertTrue(m1.getFeet() == 1);
	}
	
	public void testGetInches() {
		Measurement m1 = new Measurement(1, 11);
		assertTrue(m1.getInches() == 11);
	}
	
	public void testPlusMeasurement() {
		Measurement m1 = new Measurement(1, 5);
		Measurement m2 = new Measurement(2, 8);
		Measurement m3 =  m1.plus(m2);
		assertTrue (m3.toString().equals("(4, 1)")); 
	}
	
	public void testMinusMeasurement() {
		Measurement m1 = new Measurement(3, 4);
		Measurement m2 = new Measurement(1, 5);
		Measurement m3 = m1.minus(m2);
		assertTrue(m3.toString().equals("(1, 11)"));
	}
	
	public void testMultipleMeasurement() {
		Measurement m1 = new Measurement(0, 7);
		Measurement m3 = m1.multiple(3);
		assertTrue(m3.toString().equals("(1, 9)"));
	}
}
