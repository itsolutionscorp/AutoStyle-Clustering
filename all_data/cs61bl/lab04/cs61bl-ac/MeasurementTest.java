import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInitialMeas() {
		Measurement myMeasurement = new Measurement();
		assertTrue(myMeasurement.getFeet() == 0);
		assertTrue(myMeasurement.getInches() == 0);
	}
	
	public void testJustFeet() {
		Measurement myMeasurement = new Measurement(2);
		assertTrue(myMeasurement.getFeet() == 2);
		assertTrue(myMeasurement.getInches() == 0);
	}

	public void testFeetandInches() {
		Measurement myMeasurement = new Measurement(2,6);
		assertTrue(myMeasurement.getFeet() == 2);
		assertTrue(myMeasurement.getInches() == 6);
	}
		
	public void testPlusboundary() {
		Measurement myMeasurement = new Measurement(1,6);
		Measurement otherMeas = new Measurement(1,7);
		myMeasurement.plus(otherMeas);
		assertTrue(myMeasurement.getFeet() == 3);
		assertTrue(myMeasurement.getInches() == 1);
	}
	
	public void testPlusregular() {
		Measurement myMeasurement = new Measurement(1,4);
		Measurement otherMeas = new Measurement(1,7);
		myMeasurement.plus(otherMeas);
		assertTrue(myMeasurement.getFeet() == 2);
		assertTrue(myMeasurement.getInches() == 11);
	}
	
	public void testMinusboundary() {
		Measurement myMeasurement = new Measurement(2,0);
		Measurement otherMeas = new Measurement(1,6);
		myMeasurement.minus(otherMeas);
		assertTrue(myMeasurement.getFeet() == 0);
		assertTrue(myMeasurement.getInches() == 6);
	}
	
	public void testMinusregular() {
		Measurement myMeasurement = new Measurement(2,7);
		Measurement otherMeas = new Measurement(1,6);
		myMeasurement.minus(otherMeas);
		assertTrue(myMeasurement.getFeet() == 1);
		assertTrue(myMeasurement.getInches() == 1);
	}
	
	public void testMultiple() {
		Measurement myMeasurement = new Measurement(2,4);
		myMeasurement.multiple(2);
		assertTrue(myMeasurement.getFeet() == 4);
		assertTrue(myMeasurement.getInches() == 8);
	}
	
	public void testMultipleboundary() {
		Measurement myMeasurement = new Measurement(2,7);
		myMeasurement.multiple(2);
		assertTrue(myMeasurement.getFeet() == 5);
		assertTrue(myMeasurement.getInches() == 2);
	}
	
	public void testtoStringregular() {
		Measurement myMeasurement = new Measurement(2,3);
		assertEquals("2'3\"", myMeasurement.toString());
	}
	
	public void testtoStringboundary() {
		Measurement myMeasurement = new Measurement(2,0);
		assertEquals("2'0\"", myMeasurement.toString());
	}
		
}