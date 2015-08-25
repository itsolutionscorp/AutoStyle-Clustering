import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement M1 = new Measurement();
		assertTrue (M1.getFeet() == 0);
		assertTrue (M1.getInches() == 0);
		Measurement M2 = new Measurement(6);
		assertTrue (M2.getFeet() == 6);
		assertTrue (M2.getInches() == 0);
		Measurement M3 = new Measurement(6,10);
		assertTrue (M3.getFeet() == 6);
		assertTrue (M3.getInches() == 10);
		
	}
	public void testPlus() {
		Measurement M1 = new Measurement(6,10);
		Measurement M2 = new Measurement(3,7);
		M1.plus(M2);
		assertTrue (M1.getFeet() == 10);
		assertTrue (M1.getInches() ==5);
	}
	public void testMinus() {
		Measurement M1 = new Measurement(6,10);
		Measurement M2 = new Measurement(3,7);
		M1.minus(M2);
		assertTrue (M1.getFeet() == 3);
		assertTrue (M1.getInches() ==3);
	}
	public void testMultiple() {
		Measurement M1 = new Measurement(6,10);
		M1.multiple(2);
		assertTrue (M1.getFeet() == 13);
		assertTrue (M1.getInches() ==8);
	}
	public void testtoString() {
		Measurement M1 = new Measurement(6,10);
		
		assertTrue (M1.toString().equals('6' + "'" + "10" +'"'));
	}

}
