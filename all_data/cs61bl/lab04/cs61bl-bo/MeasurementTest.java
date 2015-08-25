import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructors() {
		Measurement test = new Measurement();
		assertEquals(0, test.getInches());
		assertEquals(0, test.getFeet());
		
		int feet = 5;
		Measurement test2 = new Measurement(feet);
		assertEquals(feet, test2.getFeet());
		assertEquals(0, test2.getInches());
		
		int inch = 10;
		Measurement test3 = new Measurement(feet, inch);
		assertEquals(feet, test3.getFeet());
		assertEquals(inch, test3.getInches());
		
		//test when inches > 12
		Measurement test4 = new Measurement(5, 13);
		assertEquals(6, test4.getFeet());
		assertEquals(1, test4.getInches());
				
	}
	
	public void testPlus() {
		Measurement test = new Measurement(5,7);
		//test adding 0
		test=test.plus(new Measurement());
		assertEquals(5, test.getFeet());
		assertEquals(7, test.getInches());
		//test adding without carry over
		test=test.plus(new Measurement(1,3));
		assertEquals(6,test.getFeet());
		assertEquals(10, test.getInches());
		//test adding with carry over
		test=test.plus(new Measurement(1,4));
		assertEquals(8, test.getFeet());
		assertEquals(2, test.getInches());
		//test adding with double carry over
		test=test.plus(new Measurement(0,22));
		assertEquals(10, test.getFeet());
		assertEquals(0, test.getInches());
		
	}
	
	public void testMinus() {
		Measurement test = new Measurement(6,10);
		//test subtracting 0
		test=test.minus(new Measurement());
		assertEquals(6, test.getFeet());
		assertEquals(10, test.getInches());
		//test normal subtraction
		test=test.minus(new Measurement(1,3));
		assertEquals(5, test.getFeet());
		assertEquals(7, test.getInches());
		//test subtraction with carry over
		test=test.minus(new Measurement(1,8));
		assertEquals(3, test.getFeet());
		assertEquals(11, test.getInches());
	}
	
	public void testMultiple() {
		Measurement test = new Measurement(1,2);
		//test multi without conversion
		test=test.multiple(2);
		assertEquals(2, test.getFeet());
		assertEquals(4, test.getInches());
		//test multi with conversion
		test=test.multiple(3);
		assertEquals(7, test.getFeet());
		assertEquals(0, test.getInches());
		//test multiply by 0
		test=test.multiple(0);
		assertEquals(0, test.getFeet());
		assertEquals(0, test.getInches());
	}
	
	public void testToString() {
		Measurement test = new Measurement();
		//test 0'0"
		assertEquals("0\'0\"",test.toString());
		//test 0 value for feet
		test=test.plus(new Measurement(0,10));
		assertEquals("0\'10\"",test.toString());
		//test inches and feet
		test=test.plus(new Measurement(0,4));
		assertEquals("1\'2\"",test.toString());
	}

}
