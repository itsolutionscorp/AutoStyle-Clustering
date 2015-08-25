import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement a = new Measurement();
		assertTrue(a.getFeet() == 0);
		assertTrue(a.getInches() == 0);
		Measurement b = new Measurement(2);
		assertTrue(b.getFeet() == 2);
		assertTrue(b.getInches() == 0);
		Measurement c = new Measurement(1, 11);
		assertTrue(c.getFeet() == 1);
		assertTrue(c.getInches() == 11);
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(0, 2);
		Measurement m3 = m1.plus(m2);
		assertTrue(m3.getFeet() == 0);
		assertTrue(m3.getInches() == 2);
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getFeet() == 0);
		assertTrue(m2.getInches() == 2);
		Measurement a = new Measurement(1, 11);
		Measurement b = new Measurement(0, 2);
		Measurement c = a.plus(b);
		assertTrue(c.getFeet() == 2);
		assertTrue(c.getInches() == 1);
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(1, 11);
		Measurement m2 = new Measurement(0, 2);
		Measurement m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 9);
		assertTrue(m1.getFeet() == 1);
		assertTrue(m1.getInches() == 11);
		assertTrue(m2.getFeet() == 0);
		assertTrue(m2.getInches() == 2);
		Measurement a = new Measurement(3, 3);
		Measurement b = new Measurement(0, 5);
		Measurement c = a.minus(b);
		assertTrue(c.getFeet() == 2);
		assertTrue(c.getInches() == 10);
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement(1, 2);
		Measurement m2 = m1.multiple(3);
		assertTrue(m2.getFeet() == 3);
		assertTrue(m2.getInches() == 6);
		Measurement m3 = new Measurement(0, 7);
		Measurement m4 = m3.multiple(4);
		assertTrue(m4.getFeet() == 2);
		assertTrue(m4.getInches() == 4);
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(5, 3);
		assertTrue("5'3\"".equals(m1.toString()));
		Measurement m2 = new Measurement(0, 11);
		assertTrue("0'11\"".equals(m2.toString()));
		Measurement m3 = new Measurement(0, 12);
		assertTrue("1'0\"".equals(m3.toString()));
	}
}
