import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor(){
		Measurement m1, m2, m3;
		m1 = new Measurement();
		m2 = new Measurement(3);
		m3 = new Measurement(3,5);
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getFeet() == 3);
		assertTrue(m2.getInches() == 0);
		assertTrue(m3.getFeet() == 3);
		assertTrue(m3.getInches() == 5);
	}
	
	public void testGet(){
		Measurement m = new Measurement(3,5);
		assertTrue(m.getFeet() == 3);
		assertTrue(m.getInches() == 5);
	}
	
	public void testPlus(){
		Measurement m1 = new Measurement(3,5);
		Measurement m2 = new Measurement(2,2);
		Measurement m3 = m1.plus(m2);
		assertTrue(m3.getFeet() == 5);
		assertTrue(m3.getInches() == 7);
		m2 = new Measurement(10,10);
		m3 = m1.plus(m2);
		assertTrue(m3.getFeet() == 14);
		assertTrue(m3.getInches() == 3);
	}
	
	public void testMinus(){
		Measurement m1 = new Measurement(3,5);
		Measurement m2 = new Measurement(2,2);
		Measurement m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 3);
		
		m2 = new Measurement(1, 10);
		m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 7);
	}
	
	public void testMultiple(){
		Measurement m1 = new Measurement(3,5);
		Measurement m2 = m1.multiple(3);
		
		assertTrue(m2.getFeet() == 10);
		assertTrue(m2.getInches() == 3);
	}
	
	public void testToString(){
		Measurement m = new Measurement(3,5);
		assertTrue(m.toString().equals("3'5\""));
	}

}
