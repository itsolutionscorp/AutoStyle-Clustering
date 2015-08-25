import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertEquals(0, m1.getFeet());
		assertEquals(0, m1.getInches());
		
		Measurement m2 = new Measurement(5);
		assertEquals(5, m2.getFeet());
		assertEquals(0, m2.getInches());
		
		Measurement m3 = new Measurement(5, 10);
		assertEquals(5, m3.getFeet());
		assertEquals(10, m3.getInches());
		
		Measurement m4 = new Measurement(10, 5);
		assertEquals(10, m4.getFeet());
		assertEquals(5, m4.getInches());
		
		Measurement m5 = new Measurement(5, 13);
		assertEquals(6, m5.getFeet());
		assertEquals(1, m5.getInches());
		
		Measurement m6 = new Measurement(5, 12);
		assertEquals(6, m6.getFeet());
		assertEquals(0, m6.getInches());
	}
	
	public void testGetFeetAndInches() {
		Measurement m1 = new Measurement();
		assertEquals(0, m1.getFeet());
		assertEquals(0, m1.getInches());
		
		Measurement m2 = new Measurement(5);
		assertEquals(5, m2.getFeet());
		assertEquals(0, m2.getInches());
		
		Measurement m3 = new Measurement(5, 10);
		assertEquals(5, m3.getFeet());
		assertEquals(10, m3.getInches());
		
		Measurement m4 = new Measurement(10, 5);
		assertEquals(10, m4.getFeet());
		assertEquals(5, m4.getInches());
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(10);
		Measurement result0 = m1.plus(m2);
		assertEquals(10, result0.getFeet());
		assertEquals(0, result0.getInches());
		
		Measurement m3 = new Measurement(6, 15);
		Measurement m4 = new Measurement(10, 5);
		Measurement result = m3.plus(m4);
		assertEquals(17, result.getFeet());
		assertEquals(8, result.getInches());
		
		Measurement m5 = new Measurement(6, 7);
		Measurement m6 = new Measurement(10, 5);
		Measurement result1 = m5.plus(m6);
		assertEquals(17, result1.getFeet());
		assertEquals(0, result1.getInches());
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(10);
		Measurement result0 = m2.minus(m1);
		assertEquals(10, result0.getFeet());
		assertEquals(0, result0.getInches());
		
		Measurement m3 = new Measurement(6, 15);
		Measurement m4 = new Measurement(10, 5);
		Measurement result = m4.minus(m3);
		assertEquals(3, result.getFeet());
		assertEquals(2, result.getInches());
		
		Measurement m5 = new Measurement(6, 7);
		Measurement m6 = new Measurement(10, 8);
		Measurement result1 = m6.minus(m5);
		assertEquals(4, result1.getFeet());
		assertEquals(1, result1.getInches());
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement();
		Measurement result0 = m1.multiple(3);
		assertEquals(0, result0.getFeet());
		assertEquals(0, result0.getInches());
		
		Measurement m2 = new Measurement(10);
		Measurement result1 = m2.multiple(2);
		assertEquals(20, result1.getFeet());
		assertEquals(0, result1.getInches());
		
		Measurement m3 = new Measurement(6, 15);
		Measurement result2 = m3.multiple(3);
		assertEquals(21, result2.getFeet());
		assertEquals(9, result2.getInches());
		
		Measurement m5 = new Measurement(6, 7);
		Measurement result3 = m5.multiple(2);
		assertEquals(13, result3.getFeet());
		assertEquals(2, result3.getInches());
	}
	
	public void testToString() {
		Measurement m1 = new Measurement();
		String result = m1.toString();
		assertEquals("0'0\"", result);
		
		Measurement m2 = new Measurement(10);
		String result1= m2.toString();
		assertEquals("10'0\"", result1);
		
		Measurement m3 = new Measurement(6, 15);
		String result2 = m3.toString();
		assertEquals("7'3\"", result2);
		
		Measurement m4 = new Measurement(10, 5);
		String result3 = m4.toString();
		assertEquals("10'5\"", result3);
	}
}
