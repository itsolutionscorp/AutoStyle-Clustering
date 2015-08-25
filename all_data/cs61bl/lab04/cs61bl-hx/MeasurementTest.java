import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInint() {
		Measurement measure1 = new Measurement();
		assertTrue(measure1.getFeet() == 0);
		assertTrue(measure1.getInches() == 0);
		Measurement measure2 = new Measurement(3);
		assertTrue(measure2.getFeet() == 3);
		assertTrue(measure2.getInches() == 0);
		Measurement measure3 = new Measurement(3, 6);
		assertTrue(measure3.getFeet() == 3);
		assertTrue(measure3.getInches() == 6);
		Measurement measure4 = new Measurement(3, 14);
		assertTrue(measure4.getFeet() == 4);
		assertTrue(measure4.getInches() == 2);
		Measurement measure5 = new Measurement(0, 14);
		assertTrue(measure5.getFeet() == 1);
		assertTrue(measure5.getInches() == 2);
		Measurement measure6 = new Measurement(2, 28);
		assertTrue(measure6.getFeet() == 4);
		assertTrue(measure6.getInches() == 4);
		Measurement measure7 = new Measurement(2, 24);
		assertTrue(measure7.getFeet() == 4);
		assertTrue(measure7.getInches() == 0);
		Measurement measure8 = new Measurement(0, 12);
		assertTrue(measure8.getFeet() == 1);
		assertTrue(measure8.getInches() == 0);
	}


	public void testPlus() {
		Measurement measure1 = new Measurement();
		Measurement measure2 = new Measurement();
		Measurement result = measure1.plus(measure2);
		assertTrue(result.getFeet() == 0);
		assertTrue(result.getInches() == 0);
		assertTrue(result!=measure1);
		assertTrue(result!=measure2);
		Measurement measure3 = new Measurement(2, 3);
		Measurement measure4 = new Measurement(1, 14);
		Measurement result1 = measure3.plus(measure4);
		Measurement result2 = measure4.plus(measure3);
		assertTrue(result1.getFeet() == 4);
		assertTrue(result1.getInches() == 5);
		assertTrue(result1.getFeet() == result2.getFeet());
		assertTrue(result1.getInches() == result2.getInches());
		Measurement measure5 = new Measurement(1);
		Measurement measure6 = new Measurement(1, 4);
		Measurement result3 = measure5.plus(measure6);
		assertTrue(result3.getFeet() == 2);
		assertTrue(result3.getInches() == 4);
		Measurement measure7 = new Measurement(0, 0);
		Measurement result4 = measure6.plus(measure7);
		assertTrue(result4.getFeet() == 1);
		assertTrue(result4.getInches() == 4);
	}

	public void testMinus() {
		Measurement measure1 = new Measurement();
		Measurement measure2 = new Measurement();
		Measurement result = measure1.minus(measure2);
		assertTrue(result.getFeet() == 0);
		assertTrue(result.getInches() == 0);
		assertTrue(result!=measure1);
		assertTrue(result!=measure2);
		Measurement measure3 = new Measurement(2, 3);
		Measurement measure4 = new Measurement(1, 14);
		Measurement result1 = measure3.minus(measure4);
		assertTrue(result1.getFeet() == 0);
		assertTrue(result1.getInches() == 1);
		Measurement measure5 = new Measurement(1, 4);
		Measurement measure6 = new Measurement(1, 4);
		Measurement result3 = measure5.minus(measure6);
		assertTrue(result3.getFeet() == 0);
		assertTrue(result3.getInches() == 0);
		Measurement measure7 = new Measurement(0, 0);
		Measurement result4 = measure6.minus(measure7);
		assertTrue(result4.getFeet() == 1);
		assertTrue(result4.getInches() == 4);
		Measurement measure8 = new Measurement(2,0);
		Measurement measure9 = new Measurement(0, 6);
		Measurement result5 = measure8.minus(measure9);
		assertTrue(result5.getFeet() == 1);
		assertTrue(result5.getInches() == 6);
	}
	
	public void testMultiple() {
		Measurement measure1 = new Measurement();
		Measurement result1 = measure1.multiple(4);
		assertTrue(result1.getFeet() == 0);
		assertTrue(result1.getInches() == 0);
		Measurement measure2 = new Measurement(1, 4);
		Measurement result2 = measure2.multiple(1);
		assertTrue(result2.getFeet() == 1);
		assertTrue(result2.getInches() == 4);
		Measurement result3 = measure2.multiple(0);
		assertTrue(result3.getFeet() == 0);
		assertTrue(result3.getInches() == 0);
		Measurement measure3 = new Measurement(0, 4);
		Measurement result4 = measure3.multiple(4);
		assertTrue(result4.getFeet() == 1);
		assertTrue(result4.getInches() == 4);
		Measurement measure4 = new Measurement(2, 3);
		Measurement result5 = measure4.multiple(2);
		assertTrue(result5.getFeet() == 4);
		assertTrue(result5.getInches() == 6);
		assertTrue(result5 != measure4);
		assertTrue(result2 != measure2);
	}
	
	public void testToString() {
		Measurement measure1 = new Measurement();
//		System.out.print(measure1.toString());
		assertTrue(measure1.toString().equals("0\'0\""));
		Measurement measure2 = new Measurement(1, 4);
		assertTrue(measure2.toString().equals("1\'4\""));
		Measurement measure3 = new Measurement(1);
		assertTrue(measure3.toString().equals("1\'0\""));
		Measurement measure4 = new Measurement(2, 14);
		assertTrue(measure4.toString().equals("3\'2\""));
		
	}
}
