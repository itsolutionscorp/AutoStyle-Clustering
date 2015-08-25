import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit() {
		Measurement test;
		test = new Measurement();
		assertTrue(test.myFeet == 0);
		assertTrue(test.myInches == 0);
		test = new Measurement(5);
		assertTrue(test.myFeet == 5);
		assertTrue(test.myInches == 0);
		test = new Measurement(5, 9);
		assertTrue(test.myFeet == 5);
		assertTrue(test.myInches == 9);
	}

		public void testSelectors() {
			Measurement test = new Measurement();
			assertTrue(test.myInches == test.getInches());
			assertTrue(test.myFeet == test.getFeet());
		}
		
		public void testAlgebra() {
			Measurement test1 = new Measurement(5, 9);
			Measurement test2 = new Measurement(6, 10);
			Measurement test3 = test1.plus(test2);
			assertTrue(test3.getInches() == 7);
			assertTrue(test3.getFeet() == 12);
			assertTrue(test2.getInches() == 10);
			assertTrue(test2.getFeet() == 6);
			
			Measurement test4 = test2.minus(test1);
			assertTrue(test4.getInches() == 1);
			assertTrue(test4.getFeet() == 1);
		
			Measurement test5 = test1.multiple(2);
			assertTrue(test5.getInches() == 6);
			assertTrue(test5.getFeet() == 11);
		}
		
		public void testStr() {
			Measurement test = new Measurement(4, 10);
			assertTrue(test.toString().equals("4'10"+ '"'));
		}
}
