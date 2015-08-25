import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testDefault() {
		Measurement m = new Measurement();
		assertTrue (m.feet == 0 & m.inches == 0);
	}
	
	public void testDefault1() {
		Measurement m = new Measurement(1);
		assertTrue (m.feet == 1 & m.inches == 0);
	}
	
	public void testDefault2() {
		Measurement m = new Measurement(1, 2);
		assertTrue (m.feet == 1 & m.inches == 2);
	}
	
	public void testget() {
		Measurement m = new Measurement(1, 2);
		assertTrue (m.getFeet() == 1);
		assertTrue (m.getInches() == 2);
	}
	
	public void testPlus() {
		Measurement m = new Measurement(1, 11);
		Measurement m1 = new Measurement(2, 3);
		assertTrue (m.plus(m1).getFeet() == 4 & m.plus(m1).getInches() == 2); 
	}
	
	public void testMinus() {
		Measurement m = new Measurement(3, 1);
		Measurement m1 = new Measurement(1, 2);
		assertTrue ((m.minus(m1).getFeet() == 1) & (m.minus(m1).getInches() == 11));
	}
	
	public void testMultiple() {
		Measurement m = new Measurement(3, 9);
		assertTrue (m.multiple(3).getFeet() == 11 & m.multiple(3).getInches() == 3);
	}
	
	public void testToString() {
		Measurement m = new Measurement(7, 9);
		assertTrue (m.toString().equals("7'9\""));
	}

}
