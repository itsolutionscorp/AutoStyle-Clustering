import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInitial(){
		Measurement test = new Measurement();
		assertEquals(test.getFeet(), 0);
		assertEquals(test.getInches(), 0);
		
		
	}
	public void testMeasurementFeet(){
		Measurement test = new Measurement(3);
		assertEquals(test.getFeet(), 3);
		assertEquals(test.getInches(), 0);
	}
	
	public void testMeasurementInches(){
		Measurement test = new Measurement(3, 1);
		assertEquals(test.getFeet(), 3);
		assertEquals(test.getInches(), 1);
		Measurement test2 = new Measurement(3, 13);
		assertEquals(test2.getFeet(), 4);
		assertEquals(test2.getInches(), 1);
		
		Measurement test3 = new Measurement(3, 24);
		assertTrue(test3.toString().equals("5'0\""));
		
	}
	public void testplus(){
		Measurement test = new Measurement(3, 1);
		Measurement test2 = new Measurement(4, 5);
		
		assertTrue(test.plus(test2).toString().equals("7'6\""));
		
		Measurement test3 = new Measurement(4, 11);
		assertTrue(test.plus(test3).toString().equals("8'0\""));	
		
		
	}
	public void testminus(){
		Measurement test = new Measurement(3, 0);
		Measurement test2 = new Measurement(0, 5);
		
		assertTrue(test.minus(test2).toString().equals("2'7\""));
		
		Measurement test3 = new Measurement(0, 4);
		assertTrue(test2.minus(test3).toString().equals("0'1\""));
	}
	
	public void testToString(){
		Measurement test = new Measurement(3,1);
		Measurement test1 = new Measurement(3,1);
		Measurement test2 = new Measurement(2,13);
		Measurement test3 = new Measurement();
		
		assertTrue(test.toString().equals(test1.toString()));
		assertTrue(test.toString().equals(test2.toString()));
		assertTrue(test.toString().equals(test3.toString())==false);		
		
	}
	
	public void testMultiple(){
		Measurement test = new Measurement(3,4);
		Measurement test3 = new Measurement();	
		
		
		assertTrue(test.multiple(0).toString().equals(test3.toString()));
		assertTrue(test.multiple(5).toString().equals("16'8\""));
		
	}
	
	

}
