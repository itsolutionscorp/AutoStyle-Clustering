import static org.junit.Assert.*;

import org.junit.Test;

public class MeasurementTest {

	@Test
	public void test() {
		Measurement m = new Measurement();
		assertTrue(m.getFeet()==0);
		assertTrue(m.getInches()==0);
	}

	public void testFeet() {
		Measurement m = new Measurement(10);
		assertTrue(m.getFeet()==10);
		assertTrue(m.getInches()==0);
	}

	public void testFI() {
		Measurement m = new Measurement(1, 2);
		assertTrue(m.getFeet()==1);
		assertTrue(m.getInches()==2);
	}

	public void testAdd() {
		Measurement m = new Measurement();
		Measurement m2 = new Measurement(3, 4);
		m.plus(m2);
		assertTrue(m.getFeet()==3);
		assertTrue(m.getInches()==4);
	}
	public void testSub() {
		Measurement m = new Measurement(5, 5);
		Measurement m2 = new Measurement(3, 4);
		m.minus(m2);
		assertTrue(m.getFeet()==2);
		assertTrue(m.getInches()==1);
	}
	public void testMul() {
		Measurement m = new Measurement(3, 4);
		m.multiple(2);
		assertTrue(m.getFeet()==6);
		assertTrue(m.getInches()==8);
	}

}
