import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testMeasurement(){
		Measurement MeasurementOne = new Measurement();
		assertTrue (MeasurementOne.getInches()==0 && MeasurementOne.getFeet() == 0);
		Measurement MeasurementTwo = new Measurement(5);
		assertTrue (MeasurementTwo.getInches()==0 && MeasurementTwo.getFeet() == 5);
		Measurement MeasurementThree = new Measurement(5,10);
		assertTrue (MeasurementThree.getInches()==10 && MeasurementThree.getFeet() == 5);
	}
	
	public void testPlus(){
		Measurement MeasurementOne = new Measurement(4,10);
		Measurement MeasurementTwo = new Measurement(5,6);
		Measurement MeasurementThree = MeasurementOne.plus(MeasurementTwo);
		assertTrue (MeasurementThree.getInches() == 4 && MeasurementThree.getFeet() == 10);
		
		Measurement MeasurementFour = new Measurement(4,11);
		Measurement MeasurementFive = new Measurement(5,15);
		Measurement MeasurementSix = MeasurementFour.plus(MeasurementFive);
		assertTrue (MeasurementSix.getInches() == 2 && MeasurementSix.getFeet() == 11);
	}
	
	public void testMinus(){
		Measurement MeasurementOne = new Measurement(5,6);
		Measurement MeasurementTwo = new Measurement(4,10);
		Measurement MeasurementThree = MeasurementOne.minus(MeasurementTwo);
		assertTrue (MeasurementThree.getInches() == 8 && MeasurementThree.getFeet() == 0);
	}
	
	public void testMultiple(){
		Measurement MeasurementOne = new Measurement(0,7);
		Measurement MeasurementTwo = MeasurementOne.multiple(3);
		assertTrue (MeasurementTwo.getInches()==9 && MeasurementTwo.getFeet()==1);
	}
	
	public void testString(){
		Measurement MeasurementOne = new Measurement(5,6);
		String compare = "5\'6\"";
		assertTrue( MeasurementOne.toString().equals(compare));
	}
	
}
