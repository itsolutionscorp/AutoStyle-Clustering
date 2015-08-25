import junit.framework.TestCase;


	public class MeasurementTest extends TestCase {
		
		public void testMeasurementOne() {
			Measurement m = new Measurement();
			assertTrue (m.getFeet() == 0);
			assertTrue (m.getInches() == 0);
		}
		
		public void testMeasurementTwo() {
			Measurement m = new Measurement(9);
			assertTrue (m.getFeet() == 9);
			assertTrue (m.getInches() == 0);
		}
		
		public void testMeasurementThree() {
			Measurement m = new Measurement(9, 5);
			assertTrue (m.getFeet() == 9);
			assertTrue (m.getInches() == 5);
		}
		
		public void testGetFeet() {
			Measurement m = new Measurement(9, 5);
			assertTrue (m.getFeet() == 9);
		}
		
		public void testGetInches() {
			Measurement m = new Measurement(9, 5);
			assertTrue (m.getInches() == 5);
			
			// Makes sure that the number of inches is always less than 12
			assertTrue (m.getInches() < 12);
		}
		
		public void testPlus(){
			Measurement m = new Measurement(9, 5);
			Measurement resultM;
			resultM = m.plus(new Measurement(2, 5));
			assertTrue (m.getFeet() == 11);
			assertTrue (m.getInches() == 10);
			
			// Makes sure that inches are not a number over 12
			Measurement n = new Measurement(9, 5);
			Measurement resultN;
			resultN = n.plus(new Measurement(2, 10));
			assertTrue (n.getFeet() == 12);
			assertTrue (n.getInches() == 3);
			
			// Tests to make sure that a new Measurement object is created
			assertFalse (m == resultM);
			assertFalse (n == resultN);
		}
		
		public void testMinus() {
			Measurement m = new Measurement(9, 5);
			Measurement resultM;
			resultM = m.minus(new Measurement(2, 5));
			assertTrue (m.getFeet() == 7);
			assertTrue (m.getInches() == 0);
			
			// Makes sure that inches are not a negative value
			Measurement n = new Measurement(9, 5);
			Measurement resultN;
			resultN = n.minus(new Measurement(2, 10));
			assertTrue (n.getFeet() == 6);
			assertTrue (n.getInches() == 7);
			
			// Tests to make sure that a new Measurement object is created
			assertFalse (m == resultM);
			assertFalse (n == resultN);
		}
		
		public void testMultiple() {
			Measurement m = new Measurement(0, 7);
			Measurement resultM;
			resultM = m.multiple(3);
			assertTrue (m.getFeet() == 1);
			assertTrue (m.getInches() == 9);
			
			// Tests to make sure that excess inches are correctly converted to feet
			
			Measurement n = new Measurement(9, 7);
			Measurement resultN;
			resultN = n.multiple(2);
			assertTrue (n.getFeet() == 19);
			assertTrue (n.getInches() == 2);
			
			// Tests to make sure that a new Measurement object is created
			assertFalse (m == resultM);
			assertFalse (n == resultN);
		}
		
		public void testString() {
			Measurement m = new Measurement(9, 5);
			assertTrue (m.toString().equals("9'5\""));
			Measurement n = new Measurement(0, 5);
			assertTrue (n.toString().equals("0'5\""));
		}
		
	}



