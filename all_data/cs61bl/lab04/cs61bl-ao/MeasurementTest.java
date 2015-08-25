import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testMeasurement() {
		Measurement obj1 = new Measurement();
		assertTrue (obj1.getFeet() == 0);
		assertTrue (obj1.getInches() == 0);
	}
	public void testAddMeasurement() {
		Measurement obj2 = new Measurement(4);
		assertTrue (obj2.getFeet() == 4);
		assertTrue (obj2.getInches() == 0);
		Measurement obj3 = new Measurement(3, 2);
		assertTrue (obj3.getFeet() == 3);
		assertTrue (obj3.getInches() == 2);
	}
	public void testPlus() {
		Measurement obj4 = new Measurement(6, 7);
		Measurement obj5 = new Measurement(3, 7);
		Measurement obj6 = obj4.plus(obj5);
		assertTrue (obj6.getFeet() == 10);
		assertTrue (obj6.getInches() == 2);
	}
	public void testMinus() {
		Measurement obj7 = new Measurement(6, 2);
		Measurement obj8 = new Measurement(2, 4);
		Measurement obj9 = obj7.minus(obj8);
		assertTrue (obj9.getFeet() == 3);
		assertTrue (obj9.getInches() == 10);
	}
	public void testMultiple() {
		Measurement obj10 = new Measurement(3, 2);
		Measurement obj11 = obj10.multiple(2);
		assertTrue (obj11.getFeet() == 6);
		assertTrue (obj11.getInches() == 4);
	}
}
