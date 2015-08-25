import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit1() {
		Measurement a;
		a = new Measurement();
		assertTrue(a.getFeet() == 0);
		assertTrue(a.getInches() == 0);
	}
	
	public void testInit2() {
		Measurement a;
		a = new Measurement(3);
		assertTrue(a.getFeet() == 3);
		assertTrue(a.getInches() == 0);
	}
	
	public void testInit3() {
		Measurement a;
		a = new Measurement(3, 5);
		assertTrue(a.getFeet() == 3);
		assertTrue(a.getInches() == 5);
	}
	
	public void testplus1() {
		Measurement a, b, c;
		a = new Measurement(3, 5);
		b = new Measurement(4, 6);
		c = a.plus(b);
		assertTrue(c.getFeet() == 7);
		assertTrue(c.getInches() == 11);
	}
	
	public void testplus2() {
		Measurement a, b, c;
		a = new Measurement(3, 7);
		b = new Measurement(4, 6);
		c = a.plus(b);
		assertTrue(c.getFeet() == 8);
		assertTrue(c.getInches() == 1);
	}
	
	public void testminus1() {
		Measurement a, b, c;
		a = new Measurement(4, 6);
		b = new Measurement(3, 5);
		c = a.minus(b);
		assertTrue(c.getFeet() == 1);
		assertTrue(c.getInches() == 1);
	}
	
	public void testminus2() {
		Measurement a, b, c;
		a = new Measurement(4, 4);
		b = new Measurement(3, 5);
		c = a.minus(b);
		assertTrue(c.getFeet() == 0);
		assertTrue(c.getInches() == 11);
	}
	
	public void testmultiple1() {
		Measurement a, b;
		a = new Measurement(0, 3);
		b = a.multiple(2);
		assertTrue(b.getFeet() == 0);
		assertTrue(b.getInches() == 6);
	}
	
	public void testmultiple2() {
		Measurement a, b;
		a = new Measurement(0, 3);
		b = a.multiple(4);
		assertTrue(b.getFeet() == 1);
		assertTrue(b.getInches() == 0);
	}
	
	public void testtoString() {
		Measurement a;
		a = new Measurement(3, 4);
		assertEquals("3'4\"", a.toString());
	}
}
