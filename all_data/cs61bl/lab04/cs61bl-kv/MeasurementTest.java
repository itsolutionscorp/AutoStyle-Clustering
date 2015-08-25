import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testInit() {
		Measurement test1 = new Measurement();
		assertTrue(test1.getInches() == 0);
		assertTrue(test1.getFeet() == 0);
		Measurement test2 = new Measurement(6);
		assertTrue(test2.getInches() == 0);
		assertTrue(test2.getFeet() == 6);
		Measurement test3 = new Measurement(6, 5);
		assertTrue(test3.getInches() == 5);
		assertTrue(test3.getFeet() == 6);
	}
	
	public void testgetFeet() {
		Measurement test1 = new Measurement(6);
		assertTrue(test1.getFeet() == 6);
	}
	
	public void testgetInches() {
		Measurement test1 = new Measurement(2, 3);
		assertTrue(test1.getInches() == 3);
	}
	
	public void testplus() {
		Measurement test1 = new Measurement(6, 6);
		Measurement test2 = new Measurement(0, 6);
		Measurement test3 = test1.plus(test2);
		assertTrue(test3.getFeet() == 7);
		assertTrue(test3.getInches() == 0);
		Measurement test4 = new Measurement(6, 6);
		Measurement test5 = new Measurement(1, 7);
		Measurement test6 = test4.plus(test5);
		assertTrue(test6.getFeet() == 8);
		assertTrue(test6.getInches() == 1);
		Measurement test7 = new Measurement(6, 6);
		Measurement test8 = new Measurement(0, 3);
		Measurement test9 = test7.plus(test8);
		assertTrue(test9.getFeet() == 6);
		assertTrue(test9.getInches() == 9);
	}
	
	public void testminus() {
		Measurement test1 = new Measurement(7, 0);
		Measurement test2 = new Measurement(0, 6);
		Measurement test3 = test1.minus(test2);
		assertTrue(test3.getFeet() == 6);
		assertTrue(test3.getInches() == 6);
		Measurement test4 = new Measurement(7, 7);
		Measurement test5 = new Measurement(0, 6);
		Measurement test6 = test4.minus(test5);
		assertTrue(test6.getFeet() == 7);
		assertTrue(test6.getInches() == 1);
	}
	
	public void testmultiple() {
		Measurement test1 = new Measurement(2, 3);
		Measurement test2 = test1.multiple(4);
		assertTrue(test2.getFeet() == 9);
		assertTrue(test2.getInches() == 0);
		Measurement test3 = new Measurement(2, 3);
		Measurement test4 = test3.multiple(0);
		assertTrue(test4.getFeet() == 0);
		assertTrue(test4.getInches() == 0);
		Measurement test5 = new Measurement(0, 3);
		Measurement test6 = test5.multiple(2);
		assertTrue(test6.getFeet() == 0);
		assertTrue(test6.getInches() == 6);
	}
	
	public void testtoString() {
		Measurement test1 = new Measurement(6);
		assertTrue(test1.toString().equals("6\'0\""));
		
	}
}
