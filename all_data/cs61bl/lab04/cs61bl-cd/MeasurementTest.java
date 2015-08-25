import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testConstructor() {
		Measurement m = new Measurement();
		assertTrue(m.getFeet() == 0);
		assertTrue(m.getInches() == 0);
	}
	public void testFeet() {
		Measurement m = new Measurement(10);
		assertTrue(m.getFeet() == 10);
		assertTrue(m.getInches() == 0);
	}
	public void testInches() {
		Measurement m = new Measurement(10, 10);
		assertTrue(m.getFeet() == 10);
		assertTrue(m.getInches() == 10);
	}
	public void testAdd() {
		Measurement m = new Measurement(10, 10);
		Measurement m2 = new Measurement(10, 10);
		Measurement m3;
		m3 = m.plus(m2);
		assertTrue(m3.getFeet() == 21);
		assertTrue(m3.getInches() == 8);
		Measurement k = new Measurement(5, 3);
		Measurement k2 = new Measurement(1, 11);
		Measurement k3;
		k3 = k.plus(k2);
		assertTrue(k3.getFeet() == 7);
		assertTrue(k3.getInches() == 2);
	}
	public void testMinus() {
		Measurement m = new Measurement(10, 10);
		Measurement m2 = new Measurement(8, 12);
		Measurement m3;
		m3 = m.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 10);
		Measurement k = new Measurement(5, 3);
		Measurement k2 = new Measurement(1, 12);
		Measurement k3;
		k3 = k.minus(k2);
		assertTrue(k3.getFeet() == 3);
		assertTrue(k3.getInches() == 3);
	}
	public void testMultiple() {
		Measurement m = new Measurement(5, 5);
		Measurement k; 
		k = m.multiple(3);
		assertTrue(k.getFeet() == 16);
		assertTrue(k.getInches() == 3);
	}
	public void testString() {
		Measurement m = new Measurement(5, 5);
		m.toString();
		assertTrue(m.toString().equals("5'5"+'"'));	
	}
	}

