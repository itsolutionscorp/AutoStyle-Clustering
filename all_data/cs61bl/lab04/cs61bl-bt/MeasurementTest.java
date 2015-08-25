import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
	private Measurement m = new Measurement(5, 5);
	private Measurement m2 = new Measurement(4, 4);
	private Measurement m3 = new Measurement(3, 6);
	
	
	public void testPlus(){
		Measurement result = m.plus(m2);
		assertTrue(result.getFeet() == m.getFeet() + m2.getFeet());
		assertTrue(result.getInches() == m.getInches() + m2.getInches());
		assertTrue((result != m) && (result != m2));
	}
	
	public void testMinus(){
		Measurement result1 = m.minus(m2);
		assertTrue(result1.getFeet() == m.getFeet() - m2.getFeet());
		assertTrue(result1.getInches() == m.getInches() - m2.getInches());
		assertTrue((result1 != m) && (result1 != m2));
		Measurement result2 = m2.minus(m3);
		assertTrue(result2.getFeet() == m2.getFeet() - m3.getFeet() - 1);
		assertTrue(result2.getInches() == m2.getInches() + 12 - m3.getInches());
		assertTrue((result2 != m2) && (result2 != m3));
	}
	
	public void testMultiple(){
    	Measurement result = m.multiple(10);
    	int tempInches = result.getFeet() * 12 + result.getInches();
    	int tempFeet = tempInches / 12;
    	tempInches = tempInches % 12;
    	assertTrue(result.getFeet() == tempFeet);
    	assertTrue(result.getInches() == tempInches);
    	assertTrue(result != m);
	}
	
	public void testToString(){
		assertTrue(m.toString().equals("5'5\""));
	}
}
