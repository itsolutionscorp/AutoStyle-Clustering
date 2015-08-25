import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testZeroMeasurements() {
		Measurement m0 = new Measurement();
		assertTrue (m0.getFeet() == 0);
		assertTrue (m0.getInches() == 0);
	}
	public void testOneMeasurements() {
		Measurement m0 = new Measurement(6);
		assertTrue (m0.getFeet() == 6);
		assertTrue (m0.getInches() == 0);
	}
	public void testTwoMeasurements() {
		Measurement m0 = new Measurement(6, 7);
		assertTrue (m0.getFeet() == 6);
		assertTrue (m0.getInches() == 7);
		Measurement m1 = new Measurement(3, 12);
		assertTrue (m1.getFeet() == 4);
		assertTrue (m1.getInches() == 0);
		Measurement m2 = new Measurement(3, 25);
		assertTrue (m2.getFeet() == 5);
		assertTrue (m2.getInches() == 1);
	}
	public void testPlus() {
		Measurement m0 = new Measurement(3, 14);
		Measurement m1 = new Measurement(5);
		Measurement m2 = new Measurement();
		assertTrue (m0.plus(m1).getFeet() == 9);
		assertTrue (m0.plus(m1).getInches() == 2);
		assertTrue (m0.plus(m2).getFeet() == 4);
		assertTrue (m0.plus(m2).getInches() == 2);
	}
	public void testMinus() {
		Measurement m0 = new Measurement(3, 14);
		Measurement m1 = new Measurement(5, 1);
		Measurement m2 = new Measurement(6);
		assertTrue (m1.minus(m0).getFeet() == 0);
		assertTrue (m1.minus(m0).getInches() == 11);
		assertTrue (m2.minus(m0).getFeet() == 1);
		assertTrue (m2.minus(m0).getInches() == 10);
	}
	public void testMultiply() {
		Measurement m0 = new Measurement(3, 14);
		Measurement m1 = new Measurement(5, 5);
		Measurement m2 = new Measurement();
		assertTrue (m0.multiple(2).getFeet() == 8);
		assertTrue (m1.multiple(3).getInches() == 3);
		assertTrue (m0.multiple(1).getFeet() == 4);
		assertTrue (m1.multiple(0).getInches() == 0);
		assertTrue (m2.multiple(99).getInches() == 0);
		assertTrue (m2.multiple(99).getFeet() == 0);
	}
	public void testToString() {
		Measurement m0 = new Measurement(3, 14);
		Measurement m1 = new Measurement(5, 5);
		Measurement m2 = new Measurement();
		String m0String = "4'2\"";
		String m1String = "5'5\"";
		String m2String = "0'0\"";
		assertTrue (m0.toString().equals(m0String));
		assertTrue (m1.toString().equals(m1String));
		assertTrue (m2.toString().equals(m2String));
	}
}
