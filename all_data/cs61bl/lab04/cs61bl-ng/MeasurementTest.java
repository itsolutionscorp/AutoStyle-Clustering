import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor1() {
		Measurement c = new Measurement();
		assertTrue (c.getFeet() == 0);
		assertTrue (c.getInches() == 0);
	}
	public void testConstructor2() {
		Measurement c = new Measurement(5);
		assertTrue (c.getFeet() == 5);
		assertTrue (c.getInches() == 0);
	}
	public void testConstructor3() {
		Measurement c = new Measurement(5, 6);
		assertTrue (c.getFeet() == 5);
		assertTrue (c.getInches() == 6);
	}
	public void testplus() {
		Measurement a = new Measurement(5, 6);
		Measurement b = new Measurement(5, 6);
		Measurement c = a.plus(b);
		assertTrue (c.getFeet() == 11);
		assertTrue (c.getInches() == 0);
	}	
	public void testminus() {
		Measurement a = new Measurement(5, 6);
		Measurement b = new Measurement(1, 7);
		Measurement c = a.minus(b);
		assertTrue (c.getFeet() == 3);
		assertTrue (c.getInches() == 11);
	}
	public void testmult() {
		Measurement d = new Measurement(0, 7);
		Measurement h = d.multiple(3);
		assertTrue (h.getFeet() == 1);
		assertTrue (h.getInches() == 9);
		Measurement f = new Measurement(5, 6);
		Measurement i = f.multiple(2);
		assertTrue (i.getFeet() == 11);
		assertTrue (i.getInches() == 0);
	}
	public void testtostring() {
		Measurement d = new Measurement(0, 7);
		assertTrue (d.toString().equals("0\'7\""));
		Measurement e = new Measurement(10, 3);
		assertTrue (e.toString().equals("10\'3\""));
	}

}
