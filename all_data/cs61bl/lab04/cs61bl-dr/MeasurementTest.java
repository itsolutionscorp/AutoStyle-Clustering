import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testDefaultConstructor(){
		Measurement m = new Measurement();
		assertTrue (m.getFeet() == 0);
		assertTrue (m.getInches() == 0);
	}
	public void testFeetConstructor(){
		Measurement m = new Measurement(5);
		assertTrue (m.getFeet() == 5);
		assertTrue (m.getInches() == 0);
	}
	public void testFeetInchesConstructor(){
		Measurement m = new Measurement(6, 3);
		assertTrue (m.getFeet() == 6);
		assertTrue (m.getInches() == 3);
		Measurement n = new Measurement(5, 13); // test wrap around for extra inches 
		assertTrue (n.getFeet() == 6);
		assertTrue(n.getInches() == 1);
	}
	public void testPlus(){
		Measurement m = new Measurement(3, 5);
		m = m.plus(new Measurement(4, 3));
		assertTrue (m.getFeet() == 7);
		assertTrue (m.getInches() == 8);
		m = m.plus(new Measurement(1, 7)); // testing wrap around for extra inches
		assertTrue (m.getFeet() == 9); 
		assertTrue (m.getInches() == 3);
	}
	public void testMinus(){
		Measurement m = new Measurement(2, 5);
		m = m.minus(new Measurement(1, 3));
		assertTrue (m.getFeet() == 1);
		assertTrue (m.getInches() == 2);
		m = m.minus(new Measurement(0, 4)); // testing wrap around for negative inches
		assertTrue(m.getFeet() == 0);
		assertTrue (m.getInches() == 10);
	}
	public void testMultiple(){
		Measurement m = new Measurement(2, 3);
		m = m.multiple(3); 
		assertTrue(m.getFeet() == 6);
		assertTrue(m.getInches() == 9);
		m = m.multiple(2); // testing wrap around for extra inches
		assertTrue(m.getFeet() == 13); 
		assertTrue(m.getInches() == 6);
	}
	public void testToString(){
		Measurement m = new Measurement(3, 4);
		assertTrue(m.toString().equals("3\'4\""));
		Measurement n = new Measurement(3, 14); // testing wrap around for extra inches
		assertTrue(n.toString().equals("4\'2\""));
	}

}
