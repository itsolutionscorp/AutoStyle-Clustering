import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructors() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(1);
		Measurement m3 = new Measurement(2,2);
		assertEquals (m1.getFeet(),0);
		assertEquals (m1.getInches(),0);
		assertEquals (m2.getFeet() , 1);
		assertEquals (m2.getInches() , 0);
		assertEquals (m3.getFeet() , 2);
		assertEquals (m3.getInches() , 2);
	}
	public void testPlus() {
		Measurement m1 = new Measurement(1,9);
		Measurement m2 = new Measurement(1,4);
		assertTrue(m1.plus(m2).getFeet()==3);
		assertTrue(m1.plus(m2).getInches()==1);
	}
	public void testMinus() {
		Measurement m1 = new Measurement(1,2);
		Measurement m2 = new Measurement(0,9);
		assertTrue(m1.minus(m2).getFeet()==0);
		assertTrue(m1.minus(m2).getInches()==5);
	}
	public void testMultiple() {
		Measurement m1 = new Measurement(9,9);
		int i1 = 2;
		assertTrue(m1.multiple(i1).getFeet()==19);
		assertTrue(m1.multiple(i1).getInches()==6);
	}
	public void testToString() {
		Measurement m1 = new Measurement();
		assertEquals(m1.toString(),m1.getFeet()+"' "+m1.getInches()+"''");
	}
}
	
