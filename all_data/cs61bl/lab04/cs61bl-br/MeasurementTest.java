import junit.framework.TestCase;


public class MeasurementTest extends TestCase {


		
		public void testConstructor() {
			
			Measurement m1 = new Measurement();
			
			assertTrue (m1.getFeet() == 0);
			
			Measurement m2 = new Measurement(5);
			
			assertTrue (m2.getFeet() == 5);
			
			Measurement m3 = new Measurement(5,3);
			
			assertTrue (m3.getFeet() == 5);
			
			assertTrue (m3.getInches() == 3);
			
					
		}
		
		public void testGetfeet(){
			
			
			Measurement m1 = new Measurement();
			
			assertTrue (m1.getFeet() == 0);
			
			Measurement m2 = new Measurement(5);
			
			assertTrue (m2.getFeet() == 5);	
			
			
		}
		
		public void testGetinch(){
			
			Measurement m1 = new Measurement(5,3);
			
			assertTrue (m1.getInches() == 3);
			
			Measurement m2 = new Measurement(6,1);
			
			assertTrue (m2.getInches() == 1);
			
			Measurement m3 = new Measurement(6,14);
			
			assertTrue(m3.getInches() == 0);
			
			assertTrue(m3.getFeet() == 0);
										
		}
		
		public void testPlus(){
			
			Measurement m1 = new Measurement(5,3);			
			
			
			Measurement m2 = new Measurement(6,1);
			
			m1.plus(m2);
			
			assertTrue(m1.getFeet() == 11);
			assertTrue(m1.getInches() == 4);
			
			
			Measurement m3 = new Measurement(5,9);			
			
			
			Measurement m4 = new Measurement(6,6);
			
			m3.plus(m4);
			
					
			assertTrue(m3.getFeet() == 12);
			assertTrue(m3.getInches() == 3);	
			
			
		}
		
		public void testMinus(){
				
			
			
			Measurement m2 = new Measurement(6,3);
			
			Measurement m1 = new Measurement(5,1);	
			
			
			m2.minus(m1);
			
			
			
			assertTrue(m2.getFeet() == 1);
			assertTrue(m2.getInches() == 2);
			
			
			
			Measurement m3 = new Measurement(6,6);
			
			Measurement m4 = new Measurement(5,9);		
			
			m3.minus(m4);
			
		
			
			assertTrue(m3.getFeet() == 0);
			assertTrue(m3.getInches() == 9);
			
				
			
		}
		
		public void testMultiple(){
			Measurement m1 = new Measurement(5);
			m1.multiple(5);
			assertTrue(m1.getFeet() == 25);		
			
			
			Measurement m2 = new Measurement(5,3);
			m2.multiple(2);
			assertTrue(m2.getFeet() == 10);	
			assertTrue(m2.getInches() == 6);	
			
			Measurement m3 = new Measurement(2,9);
			m3.multiple(3);
			assertTrue(m3.getFeet() == 8);	
			assertTrue(m3.getInches() == 3);
			
			Measurement m4 = new Measurement(0,8);
			m4.multiple(3);
			assertTrue(m4.getFeet() == 2);
			
			
					
		}
		
		public void testToString(){
			
			Measurement m1 = new Measurement(5,3);
			System.out.println("5" + "'3" + '"');
			System.out.println(m1.toString());
			
			
			assertEquals(m1.toString(), "5" + "'" +   "3" + '"');
			
			

			
			
			
			
		}
		
		

	}

