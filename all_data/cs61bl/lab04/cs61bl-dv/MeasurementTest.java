import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testMeasurement0ArgConstructor() {
		Measurement m1 = new Measurement();
		int expectedFeet = 0;
		int actualFeet = m1.myFeet;
		assertEquals(expectedFeet, actualFeet);
		
		int expectedInches = 0;
		int actualInches = m1.myInches;
		assertEquals(expectedInches, actualInches);
	}
	
	public void testMeasurement1ArgConstructor() {
		Measurement m2 = new Measurement(5);
		int expectedFeet = 5;
		int actualFeet = m2.myFeet;
		assertEquals(expectedFeet, actualFeet);
		
		int expectedInches = 0;
		int actualInches = m2.myInches;
		assertEquals(expectedInches, actualInches);
	}
	
	public void testMeasurement2ArgConstructor() {
		Measurement m3 = new Measurement(5, 8);
		int expectedFeet = 5;
		int actualFeet = m3.myFeet;
		assertEquals(expectedFeet, actualFeet);
		
		int expectedInches = 8;
		int actualInches = m3.myInches;
		assertEquals(expectedInches, actualInches);
	}
	
	public void testGetFeet() {
		Measurement m4 = new Measurement(6, 2);
		int expected1 = 6;
		int actual1 = m4.getFeet();
		assertEquals(expected1, actual1);
		
		Measurement m5 = new Measurement(3, 24);
		int expected2 = 5;
		int actual2 = m5.getFeet();
		assertEquals(expected2, actual2);
	}
	
	public void testGetInches() {
		Measurement m6 = new Measurement(1, 6);
		int expected = 6;
		int actual = m6.getInches();
		assertEquals(expected, actual);
	}
	
	public void testPlus() {
		Measurement m7 = new Measurement(2, 3);
		Measurement m8 = new Measurement(3, 2);
		Measurement m9 = m7.plus(m8);
		
		int expectedFeet = 5;
		int actualFeet = m9.getFeet();
		assertEquals(expectedFeet, actualFeet);
		
		int expectedInches = 5;
		int actualInches = m9.getInches();
		assertEquals(expectedInches, actualInches);
	}
	
	public void testMinus() {
		Measurement m10 = new Measurement(4, 3);
		Measurement m11 = new Measurement(3, 2);
		Measurement m12 = m10.minus(m11);
		
		int expectedFeet = 1;
		int actualFeet = m12.getFeet();
		assertEquals(expectedFeet, actualFeet);
		
		int expectedInches = 1;
		int actualInches = m12.getInches();
		assertEquals(expectedInches, actualInches);
	}
	
	public void testMultiple() {
		Measurement m13 = new Measurement(4, 11);
		m13 = m13.multiple(3);
		int expectedFeet = 14;
		int actualFeet = m13.getFeet();
		assertEquals(expectedFeet, actualFeet);
		
		int expectedInches = 9;
		int actualInches = m13.getInches();
		assertEquals(expectedInches, actualInches);
	}
	
	public void testToString() {
		Measurement m14 = new Measurement(5);
		String expected = "5\'0\"";
		String actual = m14.toString();
		assertEquals(expected, actual);
	}

}
