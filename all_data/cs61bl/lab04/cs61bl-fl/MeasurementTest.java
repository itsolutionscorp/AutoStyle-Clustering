import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	//constructor test
	public void testConstructor1(){
		Measurement m = new Measurement();
		assertTrue (m.getFeet() == 0);
		assertTrue (m.getInches() == 0);
	}
	
	public void testConstructor2(){
		Measurement m = new Measurement(2);
		assertTrue (m.getFeet() == 2);
		assertTrue (m.getInches() == 0);
	}
	
	public void testConstructor3(){
		Measurement m = new Measurement(2,6);
		assertTrue (m.getFeet() == 2);
		assertTrue (m.getInches() == 6);
	}
	
	public void testPlus1(){
		Measurement m = new Measurement(2,6);
		Measurement n = new Measurement(1,3);
		m.plus(n);
		assertTrue (m.getFeet( ) == 3);
		assertTrue (m.getInches( ) == 9);
	}
	
	public void testPlus2(){
		Measurement m = new Measurement(2,6);
		Measurement n = new Measurement(1,10);
		m.plus(n);
		assertTrue (m.getFeet( ) == 4);
		assertTrue (m.getInches( ) == 4);
	}
	
	public void testPlus3(){
		Measurement m = new Measurement(2,6);
		Measurement n = new Measurement(0,20);
		m.plus(n);
		assertTrue (m.getFeet( ) == 4);
		assertTrue (m.getInches( ) == 2);
	}
	
	public void testMinus1(){
		Measurement m = new Measurement(2,6);
		Measurement n = new Measurement(1,3);
		m.minus(n);
		assertTrue (m.getFeet( ) == 1);
		assertTrue (m.getInches( ) == 3);
	}
	
	public void testMinus2(){
		Measurement m = new Measurement(4,6);
		Measurement n = new Measurement(1,9);
		m.minus(n);
		assertTrue (m.getFeet( ) == 2);
		assertTrue (m.getInches( ) == 9);
	}
	
	public void testMinus3(){
		Measurement m = new Measurement(4,6);
		Measurement n = new Measurement(0,20);
		m.minus(n);
		assertTrue (m.getFeet( ) == 2);
		assertTrue (m.getInches( ) == 10);
	}
	
	public void testMultiple1(){
		Measurement m = new Measurement(2,4);
		m.multiple(2);
		assertTrue (m.getFeet( ) == 4);
		assertTrue (m.getInches( ) == 8);
	}
	
	public void testMultiple2(){
		Measurement m = new Measurement(2,7);
		m.multiple(3);
		assertTrue (m.getFeet( ) == 7);
		assertTrue (m.getInches( ) == 9);
	}
	
	public void testToString(){
		Measurement m = new Measurement(2,10);
		assertEquals("2'10\"",m.toString());
	}
}
