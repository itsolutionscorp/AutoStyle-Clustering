import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testMeasurement() {
		Measurement hi = new Measurement();
		assertTrue(hi.getFeet() == 0);
		assertEquals(0, hi.getInches());
	}
	
	public void testMeasurementFeet() {
		Measurement bye = new Measurement(2);
		assertTrue(bye.getFeet() == 2);
		assertTrue(bye.getInches() == 0);
	}
	
	public void testMeasurementBoth() {
		Measurement hiAgain = new Measurement(1, 5);
		assertEquals(1, hiAgain.getFeet());
		assertEquals(5, hiAgain.getInches());
	}
	
	public void testMeasurementOverflow() {
		Measurement bigInches = new Measurement(2, 24);
		assertTrue(bigInches.getFeet() == 4);
		assertTrue(bigInches.getInches() == 0);
	}
	
	public void testMeasurementOverflow2() {
		Measurement bigInches = new Measurement(2, 23);
		assertTrue(bigInches.getFeet() == 3);
		assertTrue(bigInches.getInches() == 11);
	}
	
	public void testGetFeet() {
		Measurement byeAgain = new Measurement(1, 2);
		assertEquals(1, byeAgain.getFeet());
	}
	
	public void testGetInches() {
		Measurement one = new Measurement(3, 4);
		assertEquals(4, one.getInches());
	}
	
	public void testPlus() {
		Measurement hi = new Measurement(1, 2);
		Measurement bye = new Measurement(3, 4);
		Measurement both = bye.plus(hi);
		assertEquals(4, both.getFeet());
		assertEquals(6, both.getInches());
	}
	
	public void testMinus() {
		Measurement hi = new Measurement(1, 2);
		Measurement bye = new Measurement(3, 4);
		Measurement both = bye.minus(hi);
		assertEquals(2, both.getFeet());
		assertEquals(2, both.getInches());
	}
	
	public void testMultiple() {
		Measurement one = new Measurement(2, 4);
		Measurement result = one.multiple(4);
		assertEquals(9, result.getFeet());
		assertEquals(4, result.getInches());
	}
	
	public void testToString() {
		Measurement stringman = new Measurement(2, 1);
		assertEquals("2\'1\"", stringman.toString());
	}
}
