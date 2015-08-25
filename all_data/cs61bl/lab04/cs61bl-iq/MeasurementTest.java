import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testConstructor1() {
		Measurement newMeasurement = new Measurement();
		assertTrue (newMeasurement.getFeet() == 0 && newMeasurement.getInches() == 0);
	}
	
	public void testConstructor2() {
		Measurement newMeasurement = new Measurement(3);
		assertTrue (newMeasurement.getFeet() == 3 && newMeasurement.getInches() == 0);
	}
	
	public void testConstructor3() {
		Measurement newMeasurement = new Measurement(3, 4);
		assertTrue (newMeasurement.getFeet() == 3 && newMeasurement.getInches() == 4);
	}
	
	public void testGetFeet() {
		Measurement newMeasurement = new Measurement(2, 3);
		assertTrue (newMeasurement.getFeet() == 2);
	}
	
	public void testGetInches() {
		Measurement newMeasurement = new Measurement(2, 3);
		assertTrue (newMeasurement.getInches() == 3);
	}
	
	public void testPlus() {
		Measurement newMeasurement = new Measurement(2, 3);
		Measurement secondMeasurement = new Measurement(4, 5);
		newMeasurement.plus(secondMeasurement);
		assertTrue (newMeasurement.getFeet() == 6 && newMeasurement.getInches() == 8);
	
		Measurement newMeasurement2 = new Measurement(2, 3);
		Measurement secondMeasurement2 = new Measurement(4, 10);
		newMeasurement2.plus(secondMeasurement2);
		assertTrue (newMeasurement2.getFeet() == 7 && newMeasurement2.getInches() == 1);
	}
	
	public void testMinus() {
		Measurement newMeasurement = new Measurement(4, 5);
		Measurement secondMeasurement = new Measurement(2, 3);
		newMeasurement.minus(secondMeasurement);
		assertTrue (newMeasurement.getFeet() == 2);
		assertTrue (newMeasurement.getInches() == 2);
	
		Measurement newMeasurement2 = new Measurement(4, 3);
		Measurement secondMeasurement2 = new Measurement(2, 10);
		newMeasurement2.minus(secondMeasurement2);
		assertTrue (newMeasurement2.getFeet() == 1 && newMeasurement2.getInches() == 5);
		
	}
	
	public void testMultiple() {
		Measurement newMeasurement = new Measurement(2, 3);
		Measurement multipliedMeasurement = newMeasurement.multiple(3);
		assertNotNull (multipliedMeasurement);
		assertTrue (multipliedMeasurement.getFeet() == 6  && multipliedMeasurement.getInches() == 9);
		
		Measurement multipliedMeasurement2 = newMeasurement.multiple(5);
		assertNotNull (multipliedMeasurement2);
		assertTrue (multipliedMeasurement2.getFeet() == 11  && multipliedMeasurement2.getInches() == 3);
	}
	
	public void testToString() {
		Measurement newMeasurement = new Measurement(2, 3);
		assertEquals ("2'3\"" , newMeasurement.toString());
	}
}
