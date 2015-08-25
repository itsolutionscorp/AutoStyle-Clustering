import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstruction() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(1, 13);
		
		assertEquals(m1.getFeet(), 0);
		assertEquals(m1.getInches(), 0);
		assertEquals(m2.getFeet(), 1);
		assertEquals(m2.getFeet(), 13);
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(1, 2);
		Measurement m2 = new Measurement(3, 4);
		
		Measurement m3 = new Measurement();
		
		assertEquals(m1.plus(m2).getFeet(), 4);
		assertEquals(m1.plus(m2).getInches(), 6);
		
		assertEquals(m1.plus(m3).getFeet(), 1);
		assertEquals(m1.plus(m3).getInches(), 2);
		
		assertEquals(m3.plus(m3).getFeet(), 0);
		assertEquals(m3.plus(m3).getInches(), 0);
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(1, 2);
		Measurement m2 = new Measurement(3, 4);
		
		Measurement m3 = new Measurement();
		
		assertEquals(m2.minus(m1).getFeet(), 2);
		assertEquals(m2.minus(m1).getInches(), 2);
		
		assertEquals(m1.minus(m3).getFeet(), 1);
		assertEquals(m1.minus(m3).getInches(), 2);
		
		assertEquals(m3.minus(m3).getFeet(), 0);
		assertEquals(m3.minus(m3).getInches(), 0);
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement(1, 2);
		Measurement m2 = new Measurement(3, 4);
		
		Measurement m3 = new Measurement();
		
		assertEquals(m2.multiple(0).getFeet(), 0);
		assertEquals(m2.multiple(0).getInches(), 0);
		
		assertEquals(m1.multiple(2).getFeet(), 2);
		assertEquals(m1.multiple(2).getInches(), 4);
		
		assertEquals(m1.multiple(7).getFeet(), 8);
		assertEquals(m1.multiple(7).getInches(), 2);
		
		assertEquals(m3.multiple(100).getFeet(), 0);
		assertEquals(m3.multiple(100).getInches(), 0);
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(1, 2);
		Measurement m2 = new Measurement(3, 16);
		
		Measurement m3 = new Measurement();
		
		assertEquals("1'2\"", m1.toString());
		assertEquals("4'4\"", m2.toString());
		assertEquals("0'0\"", m3.toString());
	}
}
