import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement testM = new Measurement();
		assertTrue (testM.getFeet() == 0);
		assertTrue (testM.getInches() == 0);
	}
	
	public void testConstructorFeet() {
		Measurement testM = new Measurement(5);
		assertTrue (testM.getFeet() == 5);
		assertTrue (testM.getInches() == 0);
	}
	
	public void testConstructorFeetInches() {
		Measurement testM = new Measurement(5, 10);
		assertTrue (testM.getFeet() == 5);
		assertTrue (testM.getInches() == 10);
		Measurement testM1 = new Measurement(5,25);
		assertTrue (testM1.toString().equals("7'1\""));
	}
	
	public void testPlus() {
		Measurement testM = new Measurement(0, 11);
		Measurement testM1 = new Measurement(0, 2);
		Measurement testM2 = testM.plus(testM1);
		assertTrue (testM2.toString().equals("1'1\""));
	}
	
	public void testMinus() {
		Measurement testM = new Measurement(1, 1);
		Measurement testM1 = new Measurement(0, 2);
		Measurement testM2 = testM.minus(testM1);
		assertTrue (testM2.toString().equals("0'11\""));
	}
	
	public void testMultiple() {
		Measurement testM = new Measurement(1, 4);
		Measurement testM1 = testM.multiple(3);
		assertTrue (testM1.toString().equals("4'0\""));
	}
}
