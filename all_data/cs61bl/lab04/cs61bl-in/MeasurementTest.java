package lab04;

import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertTrue (m1.getFeet() == 0);
		assertTrue (m1.getInches() == 0);
		
		Measurement m2 = new Measurement(5);
		assertTrue (m2.getFeet() == 5);
		assertTrue (m2.getInches() == 0);

		Measurement m3 = new Measurement(10,6);
		assertTrue (m3.getFeet() == 10);
		assertTrue (m3.getInches() == 6);
		Measurement m4 = new Measurement(2,14);
		assertTrue (m4.getFeet() == 3);
		assertTrue (m4.getInches() == 2);
	}
	
	public void testGet() {
		Measurement m1 = new Measurement(10,6);
		assertTrue (m1.getFeet() == 10);
		assertTrue (m1.getInches() == 6);
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(5,6);
		Measurement m2 = new Measurement(2,3);
		Measurement m3 = m1.plus(m2);
		assertTrue (m3.getFeet() == 7);
		assertTrue (m3.getInches() == 9);
		
		Measurement m4 = m3.plus(m1);
		assertTrue (m4.getInches() == 3);
		assertTrue (m4.getFeet() == 13);
	}
		
	public void testMinus() {
		Measurement m1 = new Measurement(10,4);
		Measurement m2 = new Measurement(1,3);
		Measurement m3 = m1.minus(m2);
		assertTrue (m3.getFeet() == 9);
		assertTrue (m3.getInches() == 1);
		
		Measurement m4 = m3.minus(m2);
		assertTrue (m4.getInches() == 10);
		assertTrue (m4.getFeet() == 7);
	}
	
	public void testMultiple() {
		Measurement m1 = new Measurement(0,7);
		Measurement m2 = m1.multiple(3);
		assertTrue (m2.getInches() == 9);
		assertTrue (m2.getFeet() == 1);
	}
	
	public void testToString() {
		Measurement m1 = new Measurement(5,7);
		assertEquals (m1.toString(),"5'7\"");
	}

}
