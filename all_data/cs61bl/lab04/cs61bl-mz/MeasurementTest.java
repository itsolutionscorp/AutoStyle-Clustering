import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit() {
		Measurement a = new Measurement();
		assertTrue(a.getFeet() == 0 && a.getInches() == 0);
	}
	public void testConstructors() {
		//Feet only
		Measurement a = new Measurement(10);
		assertTrue(a.getFeet() == 10 && a.getInches() == 0);
		//Under 12 inches
		Measurement b = new Measurement(10, 11);
		assertTrue(b.getFeet() == 10 && b.getInches() == 11);
		//Over 12 inches
		Measurement c = new Measurement(10, 13);
		assertTrue(c.getFeet() == 11 && c.getInches() == 1);
		//Equal 12 inches
		Measurement d = new Measurement(1, 12);
		assertTrue(d.getFeet() == 2 && d.getInches() == 0);
	}

	public void testPlus() {
		Measurement a = new Measurement(5, 5);
		Measurement b = new Measurement(1, 2);
		Measurement c = a.plus(b);
		assertTrue(c.getFeet() == 6 && c.getInches() == 7);
		
		// Tests when inches convert to feet
		Measurement d = new Measurement(5, 12);
		Measurement e = new Measurement(1, 13);
		Measurement f = d.plus(e);
		assertTrue(f.getFeet() == 8 && f.getInches() == 1);
	}
	public void testMinus() {
		Measurement a = new Measurement(5, 5);
		Measurement b = new Measurement(1, 2);
		Measurement c = a.minus(b);
		assertTrue(c.getFeet() == 4 && c.getInches() == 3);
		
		// Tests when inches convert to feet
		Measurement d = new Measurement(6, 0);
		Measurement e = new Measurement(1, 13);
		Measurement f = d.minus(e);
		assertTrue(f.getFeet() == 3 && f.getInches() == 11);
	}
	public void testMultiple() {
		Measurement a = new Measurement(2, 5);
		Measurement b = a.multiple(2);
		assertTrue(b.getFeet() == 4 && b.getInches() == 10);
	}
	public void testtoString() {
		Measurement a = new Measurement(5, 0);
		assertTrue(a.toString().equals("5'0\""));
	}
}
