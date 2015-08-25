import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement m = new Measurement ();
		assertTrue (m.myFeet == 0 && m.myInches == 0);
	}
	public void testFeetOnly() {
		 Measurement m = new Measurement (1);
		 assertTrue (m.myFeet == 1 && m.myInches == 0);
	}
	public void testInit() {
		Measurement m = new Measurement (2 , 3);
		assertTrue (m.myFeet == 2 && m.myInches == 3);
	}
	public void testgetFeet() {
		Measurement m = new Measurement (4 , 5);
		assertTrue (m.getFeet() == 4);
	}
	public void testgetInches() {
		Measurement m = new Measurement (6 , 7);
		assertTrue (m.getInches() == 7);
	}
	public void testPlus() {
		Measurement m = new Measurement (8 , 9);
		Measurement m2 = new Measurement (1 , 2);
		m.plus(m2);
		assertTrue (m.myFeet == 9 && m.myInches == 11);
	}
	public void testPlusOver() {
		Measurement m = new Measurement (8 , 9);
		Measurement m2 = new Measurement (3 , 4);
		m.plus(m2);
		assertTrue (m.myFeet == 12 && m.myInches == 1);
	}
	public void testMinus() {
		Measurement m = new Measurement (8 , 9);
		Measurement m2 = new Measurement (1 , 7);
		m.minus(m2);
		assertTrue (m.myFeet == 7 && m.myInches == 2);
	}
	public void testMinusUnder() {
		Measurement m = new Measurement (8 , 9);
		Measurement m2 = new Measurement (1 , 10);
		m.minus(m2);
		assertTrue (m.myFeet == 6 && m.myInches == 11);
	}
	public void testMultiple() {
		Measurement m = new Measurement (1 , 4);
		m.multiple(3);
		assertTrue (m.myFeet == 4 && m.myInches == 0);
	}
	public void testToString() {
		Measurement m = new Measurement (2 , 2);
		assertTrue(m.toString().equals("2'2\""));
	}
}
