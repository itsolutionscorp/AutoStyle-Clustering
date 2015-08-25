import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void getTest() {
		Measurement c = new Measurement(1, 1);
		assertTrue (c.getFeet() == 1);
		assertTrue (c.getInches() == 1);
	}
	
	public void testConstructor1() {
        Measurement c = new Measurement();
        assertTrue (c.getFeet() == 0);
        assertTrue (c.getInches() == 0);
	}
	
	public void testConstructor2() {
		Measurement c = new Measurement(1);
        assertTrue (c.getFeet() == 1);
        assertTrue (c.getInches() == 0);
	}
	
	public void testConstructor3() {
		Measurement c = new Measurement(1, 1);
        assertTrue (c.getInches() == 1);
        assertTrue (c.getFeet() == 1);
        
        Measurement b = new Measurement(1, 13);
        assertTrue (b.getInches() == 1);
        assertTrue (b.getFeet() == 2);
	}
	
	public void plusTest() {
		Measurement join;
		Measurement c = new Measurement(5, 1);
		Measurement b = new Measurement(2, 10);
		join = c.plus(b);
		assertTrue(join.getFeet() == 7);
		assertTrue(join.getInches() == 11);
	}
	
	public void minusTest() {
		Measurement result;
		Measurement c = new Measurement(5, 1);
		Measurement b = new Measurement(2, 10);
		result = c.minus(b);
		assertTrue(result.getFeet()==2);
		assertTrue(result.getInches()==3);
	}
	
	public void multipleTest() {
		Measurement multiplied;
		Measurement b = new Measurement(2, 10);
		multiplied = b.multiple(2);
		assertTrue(multiplied.getFeet()==5);
		assertTrue(multiplied.getInches()==8);
	}
	
	public void toStringTest() {
		Measurement c = new Measurement(5, 1);
		assertEquals(c.toString(), "5\'1\"");
		Measurement b = new Measurement(2, 10);
		assertEquals(b.toString(), "2\'10\"");
	}
	
}







