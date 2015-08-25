import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit(){
		Measurement Test_Measurement = new Measurement();
		assertTrue(Test_Measurement.getFeet() == 0);
		assertTrue(Test_Measurement.getInches() == 0);
		
		Test_Measurement = new Measurement(10);
		assertTrue(Test_Measurement.getFeet() == 10);
		assertTrue(Test_Measurement.getInches() == 0);
		
		Test_Measurement = new Measurement(10,2);
		assertTrue(Test_Measurement.getFeet() == 10);
		assertTrue(Test_Measurement.getInches() == 2);
		
		Test_Measurement = new Measurement(0,2);
		assertTrue(Test_Measurement.getFeet() == 0);
		assertTrue(Test_Measurement.getInches() == 2);
		
	}
	
	public void testplus(){
		Measurement Test_Measurement1 = new Measurement(10,2);
		Measurement Test_Measurement2 = new Measurement(5,2);
		Measurement result = Test_Measurement1.plus(Test_Measurement2);
		assertTrue(result.getFeet() == 15);
		assertTrue(result.getInches() == 4);
		
		Test_Measurement1 = new Measurement(10,8);
		Test_Measurement2 = new Measurement(5,5);
		result = Test_Measurement1.plus(Test_Measurement2);
		assertTrue(result.getFeet() == 16);
		assertTrue(result.getInches() == 1);
		
		Test_Measurement1 = new Measurement(0,8);
		Test_Measurement2 = new Measurement(0,5);
		result = Test_Measurement1.plus(Test_Measurement2);
		assertTrue(result.getFeet() == 1);
		assertTrue(result.getInches() == 1);
		
		
	}
	public void testminus(){
		Measurement Test_Measurement1 = new Measurement(10,2);
		Measurement Test_Measurement2 = new Measurement(5,1);
		Measurement result = Test_Measurement1.minus(Test_Measurement2);
		assertTrue(result.getFeet() == 5);
		assertTrue(result.getInches() == 1);
		
		Test_Measurement1 = new Measurement(10,8);
		Test_Measurement2 = new Measurement(5,11);
		result = Test_Measurement1.minus(Test_Measurement2);
		assertTrue(result.getFeet() == 4);
		assertTrue(result.getInches() == 9);
	}
		
   public void testmultiple(){
	   Measurement Test_Measurement1 = new Measurement(10,5);
	   Measurement result = Test_Measurement1.multiple(3);
	   assertTrue(result.getFeet() == 31);
	   assertTrue(result.getInches() == 3);
	   Test_Measurement1 = new Measurement(10,2);
	   result = Test_Measurement1.multiple(3);
	   assertTrue(result.getFeet() == 30);
	   assertTrue(result.getInches() == 6);
     
   }
   public void testtoString(){
	   Measurement Test_Measurement1 = new Measurement(10,5);  
	   assertEquals("10\'5\"",Test_Measurement1.toString());
	   Test_Measurement1 = new Measurement(5,11);  
	   assertEquals("5\'11\"",Test_Measurement1.toString());
	   Test_Measurement1 = new Measurement(5);  
	   assertEquals("5\'0\"",Test_Measurement1.toString());
	   Test_Measurement1 = new Measurement();  
	   assertEquals("0\'0\"",Test_Measurement1.toString());
	   
	}

}
