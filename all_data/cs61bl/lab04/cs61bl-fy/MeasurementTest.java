import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstruct_1() {
		Measurement m = new Measurement();
		assertTrue (m.getFeet() == 0);
		assertTrue (m.getInches() == 0);
	}
	public void testConstruct_2() {
		Measurement m = new Measurement(5);
		assertTrue (m.getFeet() == 5);
		assertTrue (m.getInches() == 0);
	}
	public void testConstruct_3() {
		Measurement m = new Measurement(5, 3);
		assertTrue (m.getFeet() == 5);
		assertTrue (m.getInches() == 3);
		Measurement n = new Measurement(1, 15);
		assertTrue(n.getFeet() == 2);
		assertTrue(n.getInches() == 3);
	}
	public void testGetFeetandInches(){
		Measurement m = new Measurement(5, 3);
		assertTrue (m.getFeet() == 5);
		assertTrue (m.getInches() == 3);
	}
	public void testPlus() {
		Measurement m = new Measurement(5, 3);
		Measurement n = new Measurement(5, 4);
		Measurement result = m.plus(n);
		assertTrue (result.getInches() == 7);
		assertTrue (result.getFeet() == 10);
	}
	public void testMinus() {
		Measurement m = new Measurement(5, 4);
		Measurement n = new Measurement(5, 3);
		Measurement result = m.minus(n);
		assertTrue (result.getInches() == 1);
		assertTrue (result.getFeet() == 0);
	}
	public void testMultiple() {
		Measurement m = new Measurement(1, 1);
		Measurement result = m.multiple(6);
		assertTrue (result.getInches() == 6);
		assertTrue (result.getFeet() == 6);
	}
	public void testToString() {
		Measurement m = new Measurement(5, 3);
		assertTrue (m.toString().equals("5\'3\""));
		Measurement n = new Measurement(0, 1);
		assertTrue (n.toString().equals("0\'1\""));
	}
	
}

