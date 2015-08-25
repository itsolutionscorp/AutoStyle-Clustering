import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testGetFeet(){
		Measurement bob = new Measurement(1,8);
		assertTrue (bob.getFeet() == 1);
	}
	
	public void testGetInches(){
		Measurement bob = new Measurement(1,8);
		assertTrue (bob.getInches() == 8);
	}

	public void testPlusTwoMeasurements(){
		Measurement bob = new Measurement(2,5);
		Measurement john = new Measurement (1,8);
		bob.plus(john);
		assertTrue (bob.getFeet() == 4);
		assertTrue (bob.getInches() == 1);
	}
	
	public void testMinusTwoMeasurements(){
		Measurement bob = new Measurement(2,4);
		Measurement john = new Measurement (1,3);
		bob.minus(john);
		assertTrue (bob.getFeet() == 1);
		assertTrue (bob.getInches() == 1);

	}
	
	public void testMultiple(){
		Measurement bob = new Measurement(1,2);
		bob.multiple(3);
		assertTrue (bob.getFeet() == 3);
		assertTrue (bob.getInches() == 6);

	}
	
	public void testString(){
		Measurement bob = new Measurement(1,8);
		assertTrue (bob.toString().equals("1\'8\""));
	}
}
