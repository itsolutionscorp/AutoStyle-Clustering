import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
	
	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertTrue (m1.ft == 0);
		assertTrue (m1.inch == 0);
		
	}
	
	public void testft() {
		Measurement m1 = new Measurement(3);
		assertTrue (m1.ft == 3);
		assertTrue (m1.inch == 0);
		
	}
	
	public void testftinch() {
		Measurement m1 = new Measurement(3, 5);
		assertTrue (m1.ft == 3);
		assertTrue (m1.inch == 5);
	}
	
	public void testftinch2() {
		Measurement m1 = new Measurement (3, 15);
		assertTrue (m1.ft == 4);
		assertTrue (m1.inch == 3);
	}
	
	public void testnumfeet() {
		Measurement m1 = new Measurement (5, 2);
		assertTrue (m1.getFeet() == 5);
	}
	
	public void testnuminch() {
		Measurement m1 = new Measurement (6, 5);
		assertTrue (m1.getInches() == 5);
	}
	
	public void testplusMeasurement() {
		Measurement m1 = new Measurement (6, 2);
		Measurement m2 = new Measurement (4, 5);
		Measurement m3 = m1.plus(m2);
		assertTrue(m3.ft == 10);
		assertTrue(m3.inch == 7);
	}
	
	public void testminusMeasurement() {
		Measurement m1 = new Measurement (5, 5);
		Measurement m2 = new Measurement (2, 4);
		Measurement m3 = m1.minus(m2);
		assertTrue(m3.ft == 3);
		assertTrue(m3.inch == 1);
		
	}
	
	public void testmulMeasurement() {
		Measurement m1 = new Measurement (4, 2);
		Measurement m2 = m1.multiple(10);
		assertTrue(m2.ft == 41);
		assertTrue(m2.inch == 8);
	}
	
	public string getstring() {
			Measurement m1 = new Measurement (6, 2);
			assertTrue(m1 == "6" + "'" + "2");
	
	
	
}
