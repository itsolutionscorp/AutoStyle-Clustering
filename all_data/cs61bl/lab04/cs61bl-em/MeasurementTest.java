import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testConstructors () {
		Measurement m1 = new Measurement();
		assertTrue (m1.getFeet() == 0);
		assertTrue (m1.getInches() == 0);
		Measurement m2 = new Measurement(5);
		assertTrue (m2.getFeet() == 5);
		assertTrue (m2.getInches() == 0);
		Measurement m3 = new Measurement(5, 7);
		assertTrue (m3.getFeet() == 5);
		assertTrue (m3.getInches() == 7);
	}
	
	public void testPlus () {
		Measurement m1 = new Measurement(5, 7);
		Measurement m2 = new Measurement(1, 8);
		Measurement m = m1.plus(m2);
		assertTrue (m.getFeet() == 7);
		assertTrue (m.getInches() == 3);
	}
	
	public void testMinus () {
		Measurement m1 = new Measurement(5, 7);
		Measurement m2 = new Measurement(1, 8);
		Measurement m = m1.minus(m2);
		assertTrue (m.getFeet() == 3);
		assertTrue (m.getInches() == 11);
	}
	
	public void testMultiple () {
		Measurement m = new Measurement (0, 7);
		Measurement m2 = m.multiple(3);
		assertTrue (m2.getFeet() ==1);
		assertTrue (m2.getInches() == 9);
	}
	
	public void testToString () {
		Measurement m = new Measurement (5, 7);
		assertTrue (m.toString().equals("5'7''"));
	}
}
