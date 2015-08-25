import static org.junit.Assert.*;

import org.junit.Test;


public class MeasurementTest {

	@Test
	public void testConstructor1() {
		Measurement measure;
		measure = new Measurement();
		assertTrue (measure.getFeet() == 0);
		assertTrue (measure.getInches() == 0);
	}
	
	@Test
	public void testConstructor2() {
		Measurement measure;
		measure = new Measurement(10);
		assertTrue (measure.getFeet() == 10);
		assertTrue (measure.getInches() == 0);
	}
	
	@Test
	public void testConstructor3() {
		Measurement measure;
		measure = new Measurement(10, 9);
		assertTrue (measure.getFeet() == 10);
		assertTrue (measure.getInches() == 9);
	}

	@Test
	public void testPlus() {
		Measurement measure;
		measure = new Measurement(10);
		Measurement measure2;
		measure2 = new Measurement(3);
		measure.plus(measure2);
		assertTrue (measure.getInches() == 0);
		assertTrue (measure.getFeet() == 13);
		
		Measurement measure3;
		measure3 = new Measurement(1, 10);
		Measurement measure4;
		measure4 = new Measurement(2, 2);
		measure3.plus(measure4);
		assertTrue (measure3.getInches() == 0);
		assertTrue (measure3.getFeet() == 4);
	}
	
	@Test
	public void testMinus() {
		Measurement measure;
		measure = new Measurement(10);
		Measurement measure2;
		measure2 = new Measurement(3);
		measure.minus(measure2);
		assertTrue (measure.getInches() == 0);
		assertTrue (measure.getFeet() == 7);
		
		Measurement measure3;
		measure3 = new Measurement(2, 2);
		Measurement measure4;
		measure4 = new Measurement(1, 1);
		measure3.minus(measure4);
		assertTrue (measure3.getInches() == 1);
		assertTrue (measure3.getFeet() == 1);
	}
	
	@Test
	public void testMultiply() {
		Measurement measure;
		measure = new Measurement(1);
		measure.multiple(2);
		assertTrue (measure.getInches() == 0);
		assertTrue (measure.getFeet() == 2);
		
		Measurement measure1;
		measure1 = new Measurement(1,7);
		measure1.multiple(3);
		assertTrue (measure1.getInches() == 9);
		assertTrue (measure1.getFeet() == 4);
	}
	
	@Test
	public void testToString() {
		Measurement measure;
		measure = new Measurement(56, 4);
		String string;
		string = measure.toString();
		assertTrue (string.equals("56'4\""));
	}
}
