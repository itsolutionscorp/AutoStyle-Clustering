import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testConstructor() {
		Measurement testMeasure = new Measurement();
		assertTrue(testMeasure.getFeet() == 0 && testMeasure.getInches() == 0);
		testMeasure = new Measurement(5);
		assertTrue(testMeasure.getFeet() == 5 && testMeasure.getInches() == 0);
		testMeasure = new Measurement(5, 11);
		assertTrue(testMeasure.getFeet() == 5 && testMeasure.getInches() == 11);
		testMeasure = new Measurement(2, 24);
		assertTrue(testMeasure.getFeet() == 4 && testMeasure.getInches() == 0);
	}
	
	public void testgetFeet() {
		Measurement testMeasure = new Measurement(5, 11);
		assertTrue(testMeasure.getFeet() == 5);
	}
	
	public void testgetInches() {
		Measurement testMeasure = new Measurement(5, 11);
		assertTrue(testMeasure.getInches() == 11);
	}
	
	public void testplus() {
		Measurement testMeasure = new Measurement(5, 7);
		Measurement test2Measure = new Measurement(10, 5);
		testMeasure = testMeasure.plus(test2Measure);
		assertTrue(testMeasure.getFeet() == 16 && testMeasure.getInches() == 0);
	}
	
	public void testminus() {
		Measurement testMeasure = new Measurement(5, 11);
		Measurement test2Measure = new Measurement(10, 11);
		testMeasure = test2Measure.minus(testMeasure);
		assertTrue(testMeasure.getFeet() == 5 && testMeasure.getInches() == 0);
		
	}
	
	public void testmultiple() {
		Measurement testMeasure = new Measurement(0, 4);
		Measurement test2Measure = testMeasure.multiple(3);
		assertTrue(test2Measure.getFeet() == 1 && test2Measure.getInches() == 0);
	}
	
	public void testtoString() {
		Measurement testMeasure = new Measurement(0, 1);
		assertTrue(testMeasure.toString().equals("0'1\""));
		testMeasure = new Measurement(5);
		assertTrue(testMeasure.toString().equals("5'0\""));
		testMeasure = new Measurement(5, 11);
		assertTrue(testMeasure.toString().equals("5'11\""));
		
		
	}
}
