import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testConstructor(){
		Measurement myMeasurement = new Measurement();
		assertNotNull(myMeasurement);
		assertEquals(myMeasurement.getFeet(), 0);
		assertEquals(myMeasurement.getInches(), 0);
		myMeasurement = new Measurement(1);
		assertNotNull(myMeasurement);
		assertEquals(myMeasurement.getFeet(), 1);
		assertEquals(myMeasurement.getInches(), 0);
		myMeasurement = new Measurement(1,12);
		assertNotNull(myMeasurement);
		assertEquals(myMeasurement.getFeet(), 2);
		assertEquals(myMeasurement.getInches(), 0);
	}
	public void testGetFeet(){
		Measurement myMeasurement = new Measurement();
		assertEquals(myMeasurement.getFeet(), 0);
		myMeasurement = new Measurement(1);
		assertEquals(myMeasurement.getFeet(), 1);
		myMeasurement = new Measurement(0, 24);
		assertEquals(myMeasurement.getFeet(), 2);
		
	}
	public void testGetInches(){
		Measurement myMeasurement = new Measurement();
		assertEquals(myMeasurement.getInches(), 0);
		myMeasurement = new Measurement(1);
		assertEquals(myMeasurement.getInches(), 0);
		myMeasurement = new Measurement(0, 24);
		assertEquals(myMeasurement.getInches(), 0);
		
	}
	public void testPlus(){
		Measurement myMeasurement = new Measurement();
		Measurement anotherMeasurement = new Measurement(1,1);
		assertEquals(myMeasurement.plus(anotherMeasurement).getFeet(), 1);
		assertEquals(myMeasurement.plus(anotherMeasurement).getInches(), 1);
		anotherMeasurement = new Measurement(0, 13);
		assertEquals(myMeasurement.plus(anotherMeasurement).getFeet(), 1);
		assertEquals(myMeasurement.plus(anotherMeasurement).getInches(), 1);	
	}
	
	
	public void testMinus(){
		Measurement myMeasurement = new Measurement(10, 10);
		Measurement anotherMeasurement = new Measurement(5, 5);
		assertEquals(myMeasurement.minus(anotherMeasurement).getFeet(), 5);
		assertEquals(myMeasurement.minus(anotherMeasurement).getInches(), 5);
		anotherMeasurement = new Measurement(0, 12);
		assertEquals(myMeasurement.minus(anotherMeasurement).getFeet(), 9);
		assertEquals(myMeasurement.minus(anotherMeasurement).getInches(), 10);	
	}
	public void testMultiple(){
		Measurement myMeasurement = new Measurement(5, 5);
		assertEquals(myMeasurement.multiple(2).getFeet(), 10);
		assertEquals(myMeasurement.multiple(2).getInches(), 10);
		assertEquals(myMeasurement.multiple(5).getFeet(), 27);
		assertEquals(myMeasurement.multiple(5).getInches(), 1);

	}
	public void testToString(){
		Measurement myMeasurement = new Measurement();
		assertNotNull(myMeasurement.toString());
		assertEquals(myMeasurement.toString(), "0'0\"");
		myMeasurement = new Measurement(2, 12);
		assertEquals(myMeasurement.toString(), "3'0\"");
	}
}
