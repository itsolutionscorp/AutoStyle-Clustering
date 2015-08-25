package lab04;

import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
	
	public void testConstructor1() {
		Measurement m = new Measurement();
		assertTrue( m.getFeet() == 0 );
		assertTrue( m.getInches() == 0 );
	}
	
	public void testConstructor2() {
		Measurement m = new Measurement(5);
		assertTrue( m.getFeet() == 5 );
		assertTrue( m.getInches() == 0 );
	}
	
	public void testConstructor3() {
		Measurement m = new Measurement(5, 2);
		assertTrue( m.getFeet() == 5 );
		assertTrue( m.getInches() == 2 );
		
		Measurement n = new Measurement(3, 15);
		assertTrue( n.getFeet() == 4 );
		assertTrue( n.getInches() == 3 );
	}
	
	public void testGetFeet() {
		Measurement m = new Measurement(3, 4);
		assertTrue( m.getFeet() == 3 );
	}
	
	public void testGetInches() {
		Measurement m = new Measurement(3, 4);
		assertTrue( m.getInches() == 4 );
	}
	
	public void testPlus() {
		Measurement a = new Measurement(3, 4);
		Measurement b = new Measurement(5, 9);
		Measurement c = a.plus(b);
		assertTrue( c.getFeet() == 9 );
		assertTrue( c.getInches() == 1 );
	}
	
	public void testMinus() {
		Measurement c = new Measurement(9, 1);
		Measurement a = new Measurement(3, 4);
		Measurement b = c.minus(a);
		assertTrue( b.getFeet() == 5 );
		assertTrue( b.getInches() == 9 );
	}
	
	public void testMultiple() {
		Measurement a = new Measurement(3, 7);
		Measurement b = a.multiple(2);
		assertTrue( b.getFeet() == 7 );
		assertTrue( b.getInches() == 2 );
	}
	
	public void testToString() {
		Measurement a = new Measurement(3, 7);
		assertTrue( a.toString().equals("3'7\""));
	}
}
