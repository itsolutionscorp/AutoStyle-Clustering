import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
		public void testConstructor1(){
			Measurement c = new Measurement();
			assertTrue (c.getFeet() == 0);
			assertTrue (c.getInches() == 0);
		}
		public void testConstructor2(){
			Measurement c1 = new Measurement(7);
			assertTrue (c1.getFeet() == 7);
			assertTrue (c1.getInches() == 0);
		}
		public void testConstructor3(){
			Measurement c2 = new Measurement(7, 6);
			assertTrue (c2.getFeet() == 7);
			assertTrue(c2.getInches() == 6);
		}
		
/**		public void testInstanceVars1(){
			Measurement c = new Measurement();
			assertFalse (c.myFeet == 0); 
			assertFalse (c.myInches == 0);
			
		}
		
		public void testInstanceVars2(){
			Measurement c2 = new Measurement(7);
			assertTrue (c2.myFeet == 7);
			assertTrue (c2.myInches == 0);
		}
		
		public void testInstanceVars3(){
			Measurement c3 = new Measurement(7, 6);
			assertTrue (c3.myFeet == 7);
			assertTrue (c3.myInches == 6);
		}
		
**/	
		public void testPlus(){
			Measurement m1 = new Measurement(7, 6);
			Measurement m2 = new Measurement(5, 6);
			assertTrue (m1.plus(m2).getFeet() == 13);
			assertTrue (m1.plus(m2).getInches() == 0);
		}
		
		public void testMinus(){
			Measurement m1 = new Measurement(7, 6);
			Measurement m2 = new Measurement(5, 6);
			assertTrue(m1.minus(m2).getFeet() == 2);
			assertTrue(m1.minus(m2).getInches() == 0);
		}
		
		public void testMultiple(){
			Measurement m1 = new Measurement(7, 6);
			assertTrue(m1.multiple(2).getFeet() == 15);
			assertTrue(m1.multiple(2).getInches() == 0);
			Measurement m2 = new Measurement(7, 3);
			assertTrue(m2.multiple(2).getFeet() == 14);
			assertTrue(m2.multiple(2).getInches() == 6);
		}
		
		public void testtoString(){
			Measurement m1 = new Measurement(7, 6);
			assertEquals("7\'6\"",m1.toString());
			
		}
}
