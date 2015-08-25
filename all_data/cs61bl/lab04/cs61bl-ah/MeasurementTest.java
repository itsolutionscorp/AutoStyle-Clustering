import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testInit(){
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet()==0);
		assertTrue(m1.getInches()==0);
		m1 = new Measurement(5);
		assertTrue(m1.getFeet()==5);
		assertTrue(m1.getInches()==0);
		m1 = new Measurement(1,2);
		assertTrue(m1.getFeet()==1);
		assertTrue(m1.getInches()==2);
	}
	

	public void testPlus(){
		Measurement m1 = new Measurement(1,6);
		Measurement m2 = new Measurement(2,7);
		Measurement m3 = m2.plus(m1);
	
		assertTrue(m3.getFeet() == 4);
		assertTrue(m3.getInches() == 1);	
		
		m1 = new Measurement(2,8);
		m2 = new Measurement(1,8);
		m3 = m1.plus(m2);
		System.out.println(m3);
		assertTrue(m3.getFeet() == 4);
		assertTrue(m3.getInches() == 4);
	}
	
	
	
	public void testMinus(){
		Measurement m1 = new Measurement(2,7);
		Measurement m2 = new Measurement(1,8);
		Measurement m3 = m1.minus(m2);
		
		assertTrue(m3.getFeet() == 0);
		assertTrue(m3.getInches() == 11);
		
		m1 = new Measurement(2,8);
		m2 = new Measurement(1,8);
		m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 0);
	
	}
	
	public void testMultiple(){
		Measurement m1 = new Measurement(2,7);
		Measurement m2 = m1.multiple(5);
		assertTrue(m2.getFeet()==12);
		assertTrue(m2.getInches()==11);
	}
	
	public void testToString(){
		Measurement m1 = new Measurement(2,7);
		assertEquals("2\'7\"",m1.toString());
	}
	
	
	
	
	
}
