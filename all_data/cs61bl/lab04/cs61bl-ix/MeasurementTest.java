import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testNoMeasurement() {
		Measurement m = new Measurement();
		assertTrue(m.getFeet() == 0 && m.getInches() == 0);
	}
	
	public void testMeasurementFeet() {
		Measurement m = new Measurement(2);
		assertTrue(m.getFeet()==2 && m.getInches() == 0);
	}
	public void testMeasurementInches() {
		Measurement m = new Measurement(2, 5);
		assertTrue(m.getFeet()==2 && m.getInches() == 5);
		Measurement m1 = new Measurement(2, 24);
		assertTrue(m1.getFeet()== 4 && m1.getInches()==0);
	}
	
	public void testMeasurementPlus() {
		Measurement m1 = new Measurement(2, 6);
		Measurement m2 = new Measurement(3, 6);
		Measurement m3 = m1.plus(m2);
		assertTrue(m3.getFeet() == 6);
		assertTrue(m3.getInches() == 0);
		assertTrue(m1.getFeet() == 2 && m1.getInches() == 6);
		assertTrue(m2.getFeet() == 3 && m2.getInches() == 6);
		
	}
	
	public void testMeasurementMinus() {
		Measurement m1 = new Measurement(2,8);
		Measurement m2 = new Measurement(0, 11);
		Measurement m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 9);
		assertTrue(m1.getFeet() == 2 && m1.getInches() == 8);
		assertTrue(m2.getFeet() == 0 && m2.getInches() == 11);
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement(2, 8);
		Measurement m2 = m1.multiple(3);
		assertTrue(m1.getFeet() == 2 && m1.getInches() == 8);
		assertTrue(m2.getFeet() == 8 & m2.getInches() == 0);
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(2, 8);
		assertTrue(m1.toString().equals("2'8\""));
	}
	
}
