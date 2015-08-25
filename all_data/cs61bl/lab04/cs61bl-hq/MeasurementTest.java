import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testNoArgs() {
		Measurement blank = new Measurement();
		assertTrue (blank.getFeet() == 0);
		assertTrue (blank.getInches() == 0);
	}
	
	public void testOneArg() {
		Measurement single = new Measurement(1);
		assertTrue (single.getFeet() == 1);
		assertTrue (single.getInches() == 0);
	}
	
	public void testTwoArgs() {
		Measurement both1 = new Measurement(1, 6);
		assertTrue (both1.getFeet() == 1);
		assertTrue (both1.getInches() == 6);
		Measurement both2 = new Measurement(1, 25);
		assertTrue (both2.getFeet() == 3);
		assertTrue (both2.getInches() == 1);
	}
	
	public void testPlus() {
		Measurement add1 = new Measurement(1, 6);
		Measurement add2 = new Measurement(0, 5);
		Measurement addition1 = add1.plus(add2);
		assertTrue (addition1.getFeet() == 1);
		assertTrue (addition1.getInches() == 11);
		Measurement add3 = new Measurement(1, 6);
		Measurement add4 = new Measurement(1, 7);
		Measurement addition2 = add3.plus(add4);
		assertTrue (addition2.getFeet() == 3);
		assertTrue (addition2.getInches() == 1);
	}
	
	public void testMinus() {
		Measurement sub1 = new Measurement(1, 6);
		Measurement sub2 = new Measurement(0, 5);
		Measurement difference1 = sub1.minus(sub2);
		assertTrue (difference1.getFeet() == 1);
		assertTrue (difference1.getInches() == 1);
		Measurement sub3 = new Measurement(2, 6);
		Measurement sub4 = new Measurement(1, 12);
		Measurement difference2 = sub3.minus(sub4);
		assertTrue (difference2.getFeet() == 0);
		assertTrue (difference2.getInches() == 6);
	}
	
	public void testMultiple() {
		Measurement mul1 = new Measurement(1, 5);
		Measurement product1 = mul1.multiple(2);
		assertTrue (product1.getFeet() == 2);
		assertTrue (product1.getInches() == 10);
		Measurement mul2 = new Measurement(0, 7);
		Measurement product2 = mul2.multiple(3);
		assertTrue (product2.getFeet() == 1);
		assertTrue (product2.getInches() == 9);
	}
	
	public void testString() {
		Measurement string1 = new Measurement(1, 6);
		String conversion1 = string1.getFeet() + "'" + string1.getInches() + "\"";
		assertEquals (conversion1, string1.toString());
		Measurement string2 = new Measurement(1, 12);
		String conversion2 = string2.getFeet() + "'" + string2.getInches() + "\"";
		assertEquals (conversion2, string2.toString());
	}
}

