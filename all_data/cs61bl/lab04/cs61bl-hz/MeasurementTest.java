import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testMeasurement() {
		Measurement c = new Measurement();
		assertTrue(c.feet == 0 && c.inches == 0);
	}
	
	public void testMeasurement(int feet) {
		Measurement c = new Measurement(5);
		assertTrue(c.feet == 5 && c.inches == 0);
	}
	
	public void testMeasurement(int feet, int inches) {
		Measurement c = new Measurement(5, 4);
		assertTrue(c.feet == 5 && c.inches == 4);
		Measurement d = new Measurement(0, 13);
		assertTrue(d.feet == 1 && d.inches == 1);
	}
	
	public void testgetFeet() {
		Measurement c = new Measurement(5);
		assertTrue(c.getFeet() == 5);
	}
	
	public void testgetInches() {
		Measurement c = new Measurement(5, 4);
		assertTrue(c.getInches() == 4);
		Measurement d = new Measurement(0, 13);
		assertTrue(d.getInches() == 1);
	}
	
	public void testAdd() {
		Measurement a = new Measurement(5);
		Measurement b = new Measurement(4);
		Measurement c = a.plus(b);
		assertTrue(c.feet == 9 && c.inches == 0);
		Measurement d = new Measurement(0, 11);
		Measurement e = new Measurement(0, 2);
		Measurement f = d.plus(e);
		assertTrue(f.feet == 1 && f.inches == 1);
		
	}
	
	public void testMinus() {
		Measurement a = new Measurement(5);
		Measurement b = new Measurement(4);
		Measurement c = a.minus(b);
		assertTrue(c.feet == 1 && c.inches == 0);
		Measurement d = new Measurement(1, 1);
		Measurement e = new Measurement(0, 2);
		Measurement f = d.minus(e);
		assertTrue(f.feet == 0 && f.inches == 11);
		
	}
	
	public void testMultiple() {
		Measurement a = new Measurement(5);
		Measurement b = a.multiple(3);
		assertTrue(b.feet == 15 && b.inches == 0);
		Measurement c = new Measurement(0, 4);
		Measurement d = c.multiple(3);
		assertTrue(d.feet == 1 && d.inches == 0);
		Measurement e = new Measurement(1, 5);
		Measurement f = e.multiple(3);
		assertTrue(f.feet == 4 && f.inches == 3);	
	}
	
	public void testtoString() {
		Measurement a = new Measurement(5, 1);
		String b = "5' 1\"";
		assertTrue(b.equals(a.toString()));
	}
}
