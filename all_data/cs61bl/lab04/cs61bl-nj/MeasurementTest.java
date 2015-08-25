import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement test1 = new Measurement();
		assertTrue(test1.getFeet() == 0);
		assertTrue(test1.getInches() == 0);
		
		Measurement test2 = new Measurement(5);
		assertTrue(test2.getFeet() == 5);
		assertTrue(test2.getInches() == 0);
		
		Measurement test3 = new Measurement(4, 6);
		assertTrue(test3.getFeet() == 4);
		assertTrue(test3.getInches() == 6);
		
		Measurement test4 = new Measurement(5, 15);
		assertTrue(test4.getFeet() == 6);
		assertTrue(test4.getInches() == 3);
		
		Measurement test5 = new Measurement(5, 30);
		assertTrue(test5.getFeet() == 7);
		assertTrue(test5.getInches() == 6);
	}
	
	public void testAdd() {
		Measurement a1 = new Measurement(5);
		Measurement b1 = new Measurement(7);
		assertTrue(a1.plus(b1).getFeet() ==  12);
		assertTrue(a1.plus(b1).getInches() == 0);
		
		Measurement a2 = new Measurement(5, 7);
		Measurement b2 = new Measurement(6, 2);
		assertTrue(a2.plus(b2).getFeet() ==  11);
		assertTrue(a2.plus(b2).getInches() == 9);
		
		Measurement a3 = new Measurement(5, 7);
		Measurement b3 = new Measurement(7, 18);
		assertTrue(a3.plus(b3).getFeet() ==  14);
		assertTrue(a3.plus(b3).getInches() == 1);
	}
	
	public void testMinus() {
		Measurement a1 = new Measurement(7);
		Measurement b1 = new Measurement(5);
		assertTrue(a1.minus(b1).getFeet() == 2);
		assertTrue(a1.minus(b1).getInches() == 0);
		
		Measurement a2 = new Measurement(6, 2);
		Measurement b2 = new Measurement(5, 11);
		assertTrue(a2.minus(b2).getFeet() ==  0);
		assertTrue(a2.minus(b2).getInches() == 3);
		
		Measurement a3 = new Measurement(7, 6);
		Measurement b3 = new Measurement(4, 7);
		assertTrue(a3.minus(b3).getFeet() ==  2);
		assertTrue(a3.minus(b3).getInches() == 11);
		
		Measurement a4 = new Measurement(8, 6);
		Measurement b4 = new Measurement(4, 35);
		assertTrue(a4.minus(b4).getFeet() == 1);
		assertTrue(a4.minus(b4).getInches() == 7);
	}
	
	public void testMultiple() {
		Measurement a1 = new Measurement(7);
		assertTrue(a1.multiple(5).getFeet() == 35);
		assertTrue(a1.multiple(5).getInches() == 0);
		
		Measurement a2 = new Measurement(0, 7);
		assertTrue(a2.multiple(3).getFeet() == 1);
		assertTrue(a2.multiple(3).getInches() == 9);
		
		Measurement a3 = new Measurement(3, 7);
		assertTrue(a3.multiple(3).getFeet() == 10);
		assertTrue(a3.multiple(3).getInches() == 9);
	}
	
	public void testString() {
		Measurement test = new Measurement(7, 11);
		String solution = new String(7 + "\'" + 11 + "\"");
		assertTrue(test.toString().equals(solution));
	}
}
