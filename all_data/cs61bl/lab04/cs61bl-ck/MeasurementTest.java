import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testMeasurement() {
		Measurement M = new Measurement();
		assertTrue(M.feet == 0);
		assertTrue(M.inches == 0);
		
	}
	public void testFeetMeasurement() {
		Measurement M = new Measurement(5);
		assertTrue(M.feet == 5);
		assertTrue(M.inches == 0);
		
	}
	public void testFeetandInches() {
		Measurement M = new Measurement(5, 10);
		assertTrue(M.feet == 5);
		assertTrue(M.inches == 10);
	}
	public void testGetFeet() {
		Measurement M = new Measurement(5);
		assertTrue(M.getFeet() == 5);
	}
	
	public void testGetInches() {
		Measurement M = new Measurement(5, 10);
		assertTrue(M.getInches() == 10);
	}
	public void testMeasurementPlus() {
		Measurement M = new Measurement(5, 10);
		Measurement P = new Measurement(5, 1);
		Measurement Q = new Measurement();
		Q = M.plus(P);
		assertTrue(Q.getFeet() == 10);
		assertTrue(Q.getInches() == 11);
		Q = Q.plus(M);
		assertTrue(Q.getFeet() == 16);
		assertTrue(Q.getInches() == 9);
	}
	public void testMeasurementMinus() {
		Measurement M = new Measurement(5, 10);
		Measurement P = new Measurement(5, 1);
		Measurement Q = new Measurement();
		Q = M.minus(P);
		assertTrue(Q.getFeet()== 0);
		assertTrue(Q.getInches() == 9);
		Q = P.minus(Q);
		assertTrue(Q.getFeet() == 4);
		assertTrue(Q.getInches() == 4);
	}
	public void testMeasurementMultiple() {
		Measurement M = new Measurement(5, 6);
		M = M.multiple(2);
		assertTrue(M.getFeet() == 11);
		assertTrue(M.getInches() == 0);
		
	}
	public void testToString() {
		Measurement M = new Measurement(5, 6);
		assertEquals(M.toString(), "5" + "\'" + "6" + "\"");
		
	}	

}
