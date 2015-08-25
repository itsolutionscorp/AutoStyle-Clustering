import junit.framework.TestCase;


public class MeasurementXTest extends TestCase {
	
	public void testToString() {
		MeasurementX m1 = new MeasurementX(1, 3);
		int expectedFeet = 1;
		int expectedInches = 3;
		int actualFeet = m1.myFeet;
		int actualInches = m1.myInches;
		assertEquals(expectedFeet, actualFeet);
		assertEquals(expectedInches, actualInches);
		String expectedToString = ("1" + "\' " + "3" + "\"");
		String actualToString = m1.toString();
		assertEquals(expectedToString, actualToString);
	}
	
	public void testEqualsMeasurement() {
		MeasurementX m2 = new MeasurementX(2, 4);
		MeasurementX m3 = new MeasurementX(2, 4);
		boolean myExpected = true;
		boolean myActual = m2.equals(m3);
		assertEquals(myExpected, myActual);
	}
	
	public void testEqualsObject() {
		MeasurementX m4 = new MeasurementX(6, 8);
		MeasurementX m5 = new MeasurementX(6, 8);
		boolean myExpected = true;
		boolean myActual = m4.equals(m5);
		assertEquals(myExpected, myActual);
	}

}
