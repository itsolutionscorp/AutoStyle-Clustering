import junit.framework.TestCase;


public class MeasurementTest extends TestCase {


	public void testInit() {
		Measurement m = new Measurement(1, 6);
		assertTrue (m.getFeet() == 1);
		assertTrue (m.getInches() == 6);
	}
	
	public void testInvalidArgs() {
		Measurement m = new Measurement(1, 20);
		assertTrue (m.getFeet() == 2);
		assertTrue (m.getInches() == 8);
		
		Measurement n = new Measurement(0, 12);
		assertTrue (n.getFeet() == 1);
		assertTrue (n.getInches() == 0);
	}
	
	public void testPlus() {
		Measurement m = new Measurement(10, 20);
		Measurement m2 = new Measurement(5, 7);
		assertTrue (m.plus(m2).getFeet() == 17);
		assertTrue (m.plus(m2).getInches() == 3);
	}
	
	public void testMinus() {
		Measurement m = new Measurement(10, 20);
		Measurement m2 = new Measurement(5, 7);
		assertTrue (m.minus(m2).getFeet() == 6);
		assertTrue (m.minus(m2).getInches() == 1);
	}
	
	public void testMultiple() {
		Measurement m = new Measurement(0, 7);
		Measurement w = m.multiple(3);
		assertTrue (w.getFeet() == 1);
		assertTrue (w.getInches() == 9);
		
		Measurement x = new Measurement(1, 2);
		Measurement y = x.multiple(2);
		assertTrue (y.getFeet() == 2);
		assertTrue (y.getInches() == 2);
	}

}
