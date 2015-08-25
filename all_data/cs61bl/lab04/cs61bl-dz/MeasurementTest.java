import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor1() {
		Measurement test = new Measurement();
		assertTrue (test.getFeet() == 0);
		assertTrue (test.getInches() == 0);
	}
	
	public void testConstructor2() {
		Measurement test = new Measurement(10);
		assertTrue (test.getFeet() == 10);
		assertTrue (test.getInches() == 0);
	}
	
	public void testConstructor3() {
		Measurement test = new Measurement(10,15);
		assertTrue (test.getFeet() == 11);
		assertTrue (test.getInches() == 3); //also test get feet and get inches
	}
	
	public void testplus() {
		Measurement test = new Measurement(10,5);
		Measurement test2 = new Measurement(5, 9);
		Measurement test3 = test.plus(test2);
		assertTrue (test3.getFeet() == 16);
		assertTrue (test3.getInches() == 2);
	}
	
	public void testminus() {
		Measurement test = new Measurement(10,5);
		Measurement test2 = new Measurement(5, 9);
		Measurement test3 = test.minus(test2);
		assertTrue (test3.getFeet() == 4);
		assertTrue (test3.getInches() == 8);	
	}
	
	public void testtostring() {
		Measurement test = new Measurement(10,5);
		assertTrue (test.toString().equals("10\'5\""));
		System.out.println(test.toString());
	}
}
