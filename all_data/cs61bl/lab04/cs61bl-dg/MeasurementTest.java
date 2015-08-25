import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testInit() {
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
		
		Measurement m2 = new Measurement(6);
		assertTrue(m2.getFeet() == 6);
		assertTrue(m2.getInches() == 0);
		
		Measurement m3 = new Measurement(6, 1);
		assertTrue(m3.getFeet() == 6);
		assertTrue(m3.getInches() == 1);
		
		Measurement m4 = new Measurement(5, 50);
		assertTrue(m4.getFeet() == 9);
		assertTrue(m4.getInches() == 2);
	}
	
	public void testPlus(){
		Measurement m1 = new Measurement(5, 11);
		Measurement m2 = new Measurement(6, 10);
		Measurement m3 = m1.plus(m2);
		assertTrue(m3.getFeet() == 12);
		assertTrue(m3.getInches() == 9);
	}
	
	public void testMinus(){
		Measurement m1 = new Measurement(5, 4);
		Measurement m2 = new Measurement(2, 20);
		Measurement m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 8);
	}
	
	public void testMultiple(){
		Measurement m1 = new Measurement(6, 11);
		Measurement m2 = m1.multiple(5);
		assertTrue(m2.getFeet() == 34);
		assertTrue(m2.getInches() == 7);
	}
	
	public void testToString(){
		Measurement m1 = new Measurement(5, 30);
		assertTrue(m1.toString().equals("7'6'"));
	}
}
