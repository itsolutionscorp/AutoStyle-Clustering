import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
	public void testInit() {
		Measurement ruler = new Measurement();
		assertEquals(0, ruler.getFeet());
		assertEquals(0, ruler.getInches());
		Measurement feetRuler = new Measurement(5);
		assertEquals(5, feetRuler.getFeet());
		assertEquals(0, feetRuler.getInches());
		Measurement multiRuler = new Measurement(5, 10);
		assertEquals(5, multiRuler.getFeet());
		assertEquals(10, multiRuler.getInches());
	}

	public void testGetFeet() {
		Measurement ruler = new Measurement(5, 10);
		assertEquals(5, ruler.getFeet());
	}

	public void testGetInches() {
		Measurement ruler = new Measurement(5, 10);
		assertEquals(10, ruler.getInches());
	}

	public void testPlus() {
		Measurement smallRuler = new Measurement(5, 10);
		Measurement largerRuler = new Measurement(2, 8);
		assertEquals(8, smallRuler.plus(largerRuler).getFeet());
		assertEquals(6, smallRuler.plus(largerRuler).getInches());
		assertEquals(2, largerRuler.getFeet());
		assertEquals(8, largerRuler.getInches());
		assertEquals(5, smallRuler.getFeet());
		assertEquals(10, smallRuler.getInches());
	}

	public void testMinus() {
		Measurement m1 = new Measurement(10, 2);
		Measurement m2 = new Measurement(3, 8);
		assertEquals(6, m1.minus(m2).getFeet());
		assertEquals(6, m1.minus(m2).getInches());
		assertEquals(10, m1.getFeet());
		assertEquals(2, m1.getInches());
		assertEquals(3, m2.getFeet());
		assertEquals(8, m2.getInches());
	}

	public void testMultiple() {
		Measurement m1 = new Measurement(1, 7);
		assertEquals(4, m1.multiple(3).getFeet());
		assertEquals(9, m1.multiple(3).getInches());
		assertEquals(1, m1.getFeet());
		assertEquals(7, m1.getInches());
		Measurement m2 = new Measurement(0, 7);
		assertEquals(1, m2.multiple(3).getFeet());
		assertEquals(9, m2.multiple(3).getInches());
		assertEquals(0, m2.getFeet());
		assertEquals(7, m2.getInches());
		Measurement m3 = new Measurement(5, 0);
		assertEquals(40, m3.multiple(8).getFeet());
		assertEquals(0, m3.multiple(8).getInches());
		assertEquals(5, m3.getFeet());
		assertEquals(0, m3.getInches());
	}

	public void testToString(){
		Measurement m1 = new Measurement(9, 10);
		assertTrue(m1.toString().equals("9'10\""));
		Measurement m2 = new Measurement(8, 0);
		assertTrue(m2.toString().equals("8'0\""));
		Measurement m3 = new Measurement(0, 7);
		assertTrue(m3.toString().equals("0'7\""));
	}
	
}
