import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit() {
		//tests all three constructor classes as well as getInches and getFeet
		Measurement l1 = new Measurement();
		Measurement l2 = new Measurement(2);
		Measurement l3 = new Measurement(3, 4);
		assertTrue (l1.getFeet() == 0);
		assertTrue (l1.getInches() == 0);
		assertTrue (l2.getFeet() == 2);
		assertTrue (l2.getInches() == 0);
		assertTrue (l3.getFeet() == 3);
		assertTrue (l3.getInches() == 4);
	}
	
	public void testPlus() {
		//tests addition method of two measurements
		Measurement l2 = new Measurement(2);
		Measurement l3 = new Measurement(3, 4);
		l2.plus(l3);
		assertTrue (l2.getFeet() == 5);
		assertTrue (l2.getInches() == 4);
	}
	
	public void testMinus() {
		//tests subtraction method of two measurements
		Measurement l2 = new Measurement(2);
		Measurement l3 = new Measurement(3, 4);
		l3.minus(l2);
		assertTrue (l3.getFeet() == 1);
		assertTrue (l3.getInches() == 4);
	}
	
	public void testMultiple() {
		//tests multiplication method of a measurement
		Measurement l1 = new Measurement();
		Measurement l2 = new Measurement(2);
		Measurement l3 = new Measurement(3, 4);
		l1.multiple(2);
		assertTrue (l1.getFeet() == 0);
		assertTrue (l1.getInches() == 0);
		l2.multiple(3);
		assertTrue (l2.getFeet() == 6);
		assertTrue (l2.getInches() == 0);
		l3.multiple(4);
		assertTrue (l3.getFeet() == 13);
		assertTrue (l3.getInches() == 4);
	}
	
	public void testToString() {
		//tests printing of feet with inches in a string
		Measurement l1 = new Measurement();
		Measurement l2 = new Measurement(2);
		Measurement l3 = new Measurement(3, 4);
		assertEquals("0\'0\"", l1.toString());
		assertEquals("2\'0\"", l2.toString());
		assertEquals("3\'4\"", l3.toString());
	}

}
