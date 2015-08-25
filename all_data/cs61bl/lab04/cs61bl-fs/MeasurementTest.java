import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testConstructorNoFeetNoInches() {
		Measurement testMeasurement = new Measurement();
		assertNotNull(testMeasurement);
		assertEquals(testMeasurement.getFeet(), 0);
		assertEquals(testMeasurement.getInches(), 0);
	}
	public void testConstructorFeetNoInches() {
		int testFeet = 9;
		Measurement testMeasurement = new Measurement(testFeet);
		assertNotNull(testMeasurement);
		assertEquals(testMeasurement.getFeet(), testFeet);
		assertEquals(testMeasurement.getInches(), 0);
	}
	public void testConstructorFeetInches() {
		int testFeet = 9;
		int testInches = 8;
		Measurement testMeasurement = new Measurement(testFeet, testInches);
		assertNotNull(testMeasurement);
		assertEquals(testMeasurement.getFeet(), testFeet);
		assertEquals(testMeasurement.getInches(), testInches);
	}
	public void testgetFeet() {
		int testFeet = 9;
		Measurement testMeasurement = new Measurement(testFeet);
		assertNotNull(testMeasurement);
		assertEquals(testMeasurement.getFeet(), testFeet);
	}
	public void testgetInches() {
		int testInches = 8;
		Measurement testMeasurement = new Measurement(9, testInches);
		assertNotNull(testMeasurement);
		assertEquals(testMeasurement.getInches(), testInches);
	}
	public void testPlus() {
		Measurement m1 = new Measurement(1, 1);
		Measurement m2 = new Measurement(1, 2);
		Measurement m3 = new Measurement(2, 3);
		assertEquals(m1.plus(m2).getFeet(), m3.getFeet());
		assertEquals(m1.plus(m2).getInches(), m3.getInches());
		
		m1 = new Measurement(1, 7);
		m2 = new Measurement(2, 10);
		m3 = new Measurement(4, 5);
		assertEquals(m1.plus(m2).getFeet(), m3.getFeet());
		assertEquals(m1.plus(m2).getInches(), m3.getInches());
		
		m1 = new Measurement(3, 120);
		m2 = new Measurement(4, 100);
		m3 = new Measurement(25, 4);
		assertEquals(m1.plus(m2).getFeet(), m3.getFeet());
		assertEquals(m1.plus(m2).getInches(), m3.getInches());
	}
	public void testMinus() {
		Measurement m1 = new Measurement(2, 3);
		Measurement m2 = new Measurement(1, 2);
		Measurement m3 = new Measurement(1, 1);
		assertEquals(m1.minus(m2).getFeet(), m3.getFeet());
		assertEquals(m1.minus(m2).getInches(), m3.getInches());
		
		m1 = new Measurement(2, 1);
		m2 = new Measurement(1, 10);
		m3 = new Measurement(0, 3);
		assertEquals(m1.minus(m2).getFeet(), m3.getFeet());
		assertEquals(m1.minus(m2).getInches(), m3.getInches());
		
	}
	public void testMultiple() {
		Measurement m1 = new Measurement(2, 3);
		Measurement m2 = new Measurement(0, 0);
		assertEquals(m1.multiple(0).getFeet(), m2.getFeet());
		assertEquals(m1.multiple(0).getInches(), m2.getInches());
		
		m2 = new Measurement(2, 3);
		assertEquals(m1.multiple(1).getFeet(), m2.getFeet());
		assertEquals(m1.multiple(1).getInches(), m2.getInches());
		
		m2 = new Measurement(6, 9);
		assertEquals(m1.multiple(3).getFeet(), m2.getFeet());
		assertEquals(m1.multiple(3).getInches(), m2.getInches());
		
		m2 = new Measurement(11, 3);
		assertEquals(m1.multiple(5).getFeet(), m2.getFeet());
		assertEquals(m1.multiple(5).getInches(), m2.getInches());
		
		m2 = new Measurement(22, 6);
		assertEquals(m1.multiple(10).getFeet(), m2.getFeet());
		assertEquals(m1.multiple(10).getInches(), m2.getInches());
		
	}
	public void testToString() {
		Measurement m1 = new Measurement(2, 3);
		assertEquals(m1.toString(), "2'3\"");
	}

}
