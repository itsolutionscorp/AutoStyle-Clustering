import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testConstrucor() {
		Measurement m = new Measurement();
		assertTrue( m.getFeet()== 0 && m.getInches() == 0 );
	}
	
	public void testConstrutorWithOneArg(){
		Measurement m = new Measurement(50);
		assertTrue( m.getFeet() == 50 && m.getInches() == 0 );
		
		m = new Measurement(-10);   // check negative value
		assertTrue(m.getFeet() == 0 && m.getInches() == 0 );
	}
	
	public void testConstructorWithTwoArgs() {
		Measurement m = new Measurement(1, 13);
		assertTrue( m.getFeet() == 2 && m.getInches() == 1 );
		
		// check negative values
		m = new Measurement(5, -10);
		assertTrue( m.getFeet() == 0 && m.getInches() == 0 );
		m = new Measurement(-5, 10);
		assertTrue( m.getFeet() == 0 && m.getInches() == 0 );
		m = new Measurement(-5, -10);
		assertTrue( m.getFeet() == 0 && m.getInches() == 0 );
	}
	
	public void testGetFeet() {
		Measurement m = new Measurement(5,6);
		assertTrue( m.getFeet() == 5 );
	}
	
	public void testGetInches() {
		Measurement m = new Measurement(5,6);
		assertTrue( m.getInches() == 6 );
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(1,9);
		Measurement m2 = new Measurement(5,3);
		
		Measurement m3 = m1.plus(m2);
		assertTrue( m3.getFeet() == 7 && m3.getInches() == 0 );
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(10);
		Measurement m2 = new Measurement(5,8);
		
		Measurement m3 = m1.minus(m2);
		assertTrue( m3.getFeet() == 4 && m3.getInches() == 4 );
		
		// check that subtract a larger argument from the current measurement
		m3 = m2.minus(m1);
		assertTrue( m3.getFeet() == 0 && m3.getInches() == 0 );
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement(2,6);
		Measurement m2 = m1.multiple(9);
		assertTrue( m2.getFeet() == 6 && m2.getInches() == 6 );
		m2 = m1.multiple(0);
		assertTrue( m2.getFeet() == 2 && m2.getInches() == 0 );
		
		// check a negative integer argument
		m2 = m1.multiple(-1);
		assertTrue( m2.getFeet() == 0 && m2.getInches() == 0 );
		
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(1,6);
		assertEquals("1f'6i\"", m1.toString());
	}

}
