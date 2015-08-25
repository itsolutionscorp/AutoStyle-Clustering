import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit(){
		Measurement test = new Measurement();
		assertEquals (test.getFeet(), 0);
		assertEquals (test.getInches(), 0);
		
		Measurement test2 = new Measurement(20);
		assertEquals (test2.getFeet(), 20);
		assertEquals (test2.getInches(), 0);
		
		Measurement test3 = new Measurement(1, 2);
		assertEquals (test3.getFeet(), 1);
		assertEquals (test3.getInches(), 2);
		
		Measurement test4 = new Measurement(3, 26);
		assertEquals (test4.getFeet(), 5);
		assertEquals (test4.getInches(), 2);
	}
	
	public void testPlus(){
		Measurement test = new Measurement(1, 2);
		Measurement test2 = new Measurement(3, 26);
		
		Measurement sum = test.plus(test2);
		assertEquals(6, sum.getFeet());
		assertEquals(4, sum.getInches());

		assertTrue(sum != test);
		assertTrue(sum != test2);
		
		Measurement test3 = new Measurement(1, 2);
		Measurement test4 = new Measurement(0, 0);
		
		Measurement sum2 = test3.plus(test4);
		assertEquals(1, sum2.getFeet());
		assertEquals(2, sum2.getInches());
	}
	
	public void testMinus(){
		Measurement test = new Measurement(3, 26);
		Measurement test2 = new Measurement(1, 30); 
		
		Measurement subtracted = test.minus(test2);
		assertEquals(1, subtracted.getFeet());
		assertEquals(8, subtracted.getInches());
		
		assertTrue(subtracted != test);
		assertTrue(subtracted != test2);
	}
	
	public void testMultiple(){
		Measurement test = new Measurement(3, 26);
		Measurement multiplied = test.multiple(3);
		
		assertEquals(15, multiplied.getFeet());
		assertEquals(6, multiplied.getInches());
		
		assertTrue(multiplied != test);
		
	}
	
	public void testToString(){
		Measurement test = new Measurement(3, 26);
		assertEquals("5\'2\"", test.toString());
	}
	
	
}
