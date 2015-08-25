import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(5);
		Measurement m3 = new Measurement(5, 5);
		Measurement m4 = new Measurement(5, 12);
		Measurement m5 = new Measurement(0);
		Measurement m6 = new Measurement(0, 0);
		assertNotNull(m1);
		assertNotNull(m2);
		assertNotNull(m3);
		assertNotNull(m4);
		assertNotNull(m5);
		assertNotNull(m6);
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getFeet() == 5);
		assertTrue(m2.getInches() == 0);
		assertTrue(m3.getFeet() == 5);
		assertTrue(m3.getInches() == 5);
		assertTrue(m4.getFeet() == 6);
		assertTrue(m4.getInches() == 0);
		assertTrue(m5.getFeet() == 0);
		assertTrue(m5.getInches() == 0);
		assertTrue(m6.getFeet() == 0);
		assertTrue(m6.getInches() == 0);
	}
	
	public void testGetFeet() {
		Measurement m1 = new Measurement(5, 13);
		assertTrue(m1.getFeet() == 6);
		Measurement m2 = new Measurement();
		assertTrue(m2.getFeet() == 0);
		Measurement m3 = new Measurement(0);
		assertTrue(m3.getFeet() == 0);
		Measurement m4 = new Measurement(0, 0);
		assertTrue(m4.getFeet() == 0);
	}
		
	public void testGetInches() {
		Measurement m1 = new Measurement(0, 5);
		assertTrue(m1.getInches() == 5);
		Measurement m2 = new Measurement(0, 13);
		assertTrue(m2.getInches() == 1);
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(5, 12);
		Measurement m2 = new Measurement(5, 13);
		Measurement m3 = m1.plus(m2);
		assertTrue(m1.getFeet() == 6);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getFeet() == 6);
		assertTrue(m2.getInches() == 1);
		assertTrue(m3.getFeet() == 12);
		assertTrue(m3.getInches() == 1);
		Measurement m4 = m1.plus(new Measurement());
		assertTrue(m4.getFeet() == 6);
		assertTrue(m4.getInches() == 0);
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(5, 0);
		Measurement m2 = new Measurement(3, 11);
		Measurement m3 = m1.minus(m2);
		assertTrue(m1.getFeet() == 5);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getFeet() == 3);
		assertTrue(m2.getInches() == 11);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 1);
		Measurement m4 = m2.minus(new Measurement());
		assertTrue(m4.getFeet() == 3);
		assertTrue(m4.getInches() == 11);
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement();
		Measurement m2 = m1.multiple(5);
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getFeet() == 0);
		assertTrue(m2.getInches() == 0);
		Measurement m3 = new Measurement(3, 15);
		Measurement m4 = m3.multiple(3);
		assertTrue(m3.getFeet() == 4);
		assertTrue(m3.getInches() == 3);
		assertTrue(m4.getFeet() == 12);
		assertTrue(m4.getInches() == 9);
		Measurement m5 = m3.multiple(1);
		assertTrue(m5.getFeet() == 4);
		assertTrue(m5.getInches() == 3);
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(4, 18);
		assertTrue(m1.toString().equals("5\'6\""));
		Measurement m2 = m1.plus(new Measurement(0, 1));
		assertTrue(m2.toString().equals("5\'7\""));
		Measurement m3 = m2.minus(new Measurement(3, 12));
		assertTrue(m3.toString().equals("1\'7\""));
		Measurement m4 = m3.multiple(2);
		assertTrue(m4.toString().equals("3\'2\""));
		Measurement m5 = new Measurement();
		assertTrue(m5.toString().equals("0\'0\""));
		Measurement m6 = new Measurement(0);
		assertTrue(m6.toString().equals("0\'0\""));
		Measurement m7 = new Measurement(0, 0);
		assertTrue(m7.toString().equals("0\'0\""));
		Measurement m8 = new Measurement(5);
		assertTrue(m8.toString().equals("5\'0\""));
	}
}
