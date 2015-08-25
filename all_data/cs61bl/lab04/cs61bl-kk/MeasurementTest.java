import static org.junit.Assert.*;

import org.junit.Test;


public class MeasurementTest {

	@Test
	public void testInit() {
		Measurement myFirst = new Measurement();
		assertTrue(myFirst.getFeet() == 0);
		assertTrue(myFirst.getInches() == 0);
	}
	
	@Test
	public void testInitFeet() {
		Measurement myFirst = new Measurement(5);
		assertTrue(myFirst.getFeet() == 5);
		assertTrue(myFirst.getInches() == 0);
	}
	
	@Test
	public void testInitInches() {
		Measurement myFirst = new Measurement(5,7);
//		assertTrue(myFirst.getFeet() == 5);
		assertTrue(myFirst.getInches() == 7);
		
		//test to see if inches goes over 12
		Measurement my2= new Measurement(5,13);
		assertTrue(my2.getFeet() == 6);
		assertTrue(my2.getInches() == 1);
		
		//test to see if inches is 12
		Measurement my3= new Measurement(5,12);
		assertTrue(my3.getFeet() == 6);
		assertTrue(my3.getInches() == 0);
	}
	
	@Test
	public void testPlus() {
		Measurement m1 = new Measurement(5,7);

		Measurement m2 = new Measurement(1,1);
		Measurement test = new Measurement();
		test = m1.plus(m2);
		assertTrue(test.getFeet() == 6);
		assertTrue(test.getInches() == 8);
		
		// test to see if inches goes over 12 when added so it converts to feet
		Measurement m3 = new Measurement(5,11);
		Measurement test2 = new Measurement();
		test2 = m1.plus(m3);
		assertTrue(test2.getFeet() == 11);
		assertTrue(test2.getInches() == 6);
	}
		
	@Test
	public void testMinus() {
			Measurement m1 = new Measurement(5,1);

			Measurement m2 = new Measurement(4,7);
			Measurement test = new Measurement();
			test = m1.minus(m2);
			assertTrue(test.getFeet() == 0);
			assertTrue(test.getInches() == 6);
			
			Measurement m3 = new Measurement(5,1);

			Measurement m4 = new Measurement(4,1);
			Measurement test2 = new Measurement();
			test2 = m3.minus(m4);
			assertTrue(test2.getFeet() == 1);
			assertTrue(test2.getInches() == 0);
	}
	
	@Test
	public void testMultiple() {
		Measurement m1 = new Measurement(5,1);
		Measurement test = new Measurement();
		test = m1.multiple(0);
		assertTrue(test.getFeet() == 0);
		assertTrue(test.getInches() == 0);
		
		Measurement m2 = new Measurement(4,7);
		Measurement test2 = m2.multiple(3);
		assertTrue(test2.getFeet() == 13);
		assertTrue(test2.getInches() == 9);
		
		Measurement m3 = new Measurement(5,1);
		Measurement test3 = m3.multiple(1);
		assertTrue(test3.getFeet() == 5);
		assertTrue(test3.getInches() == 1);

	}
}
