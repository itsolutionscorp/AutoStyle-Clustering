import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testGetFeet(){
		Measurement testM = new Measurement(3);
		assertEquals(3,testM.getFeet());
		
	}
	public void testPlus(){
		Measurement testM = new Measurement(3, 11);
		Measurement m2 = new Measurement(2, 11);
		assertEquals(6, testM.plus(m2).getFeet());
		assertEquals(10, testM.plus(m2).getInches());	
	}
	public void testMinus(){
		Measurement testM = new Measurement(3, 8);
		Measurement m2 = new Measurement(2, 11);
		assertEquals(0, testM.minus(m2).getFeet());
		assertEquals(9, testM.minus(m2).getInches());
		
		Measurement testM2 = new Measurement(3, 8);
		Measurement m23 = new Measurement(2, 6);
		assertEquals(1, testM2.minus(m23).getFeet());
		assertEquals(2, testM2.minus(m23).getInches());
	}
	public void testMultiple(){
		Measurement testM = new Measurement(3, 9);
		assertTrue((testM.multiple(3).getInches() == 3 && testM.multiple(3).getFeet() == 11 ));
			
	}
	public void testString(){
		Measurement testM = new Measurement(2, 9);
		assertTrue( testM.toString().equals("2\' 9\"") );
	}
}
