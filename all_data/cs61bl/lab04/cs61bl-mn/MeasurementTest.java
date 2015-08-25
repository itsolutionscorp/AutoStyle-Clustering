import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testInit() {
		Measurement test1 = new Measurement();
		assertTrue(test1.getFeet()==0);
		assertTrue(test1.getInches()==0);
		Measurement test2 = new Measurement(2);
		assertTrue(test2.getFeet()==2);
		assertTrue(test2.getInches()==0);
		Measurement test3 = new Measurement(1, 2);
		assertTrue(test3.getFeet()==1);
		assertTrue(test3.getInches()==2);
		Measurement test4 = new Measurement(1, 37);
		assertTrue(test4.getFeet()==4);
		assertTrue(test4.getInches()==1);
	}
	public void testPlus() {
		Measurement test3 = new Measurement(1, 2);
		Measurement test4 = new Measurement(1, 22);
		Measurement test5 = test3.plus(test4);
		assertTrue(test5.getFeet()== 4);
		assertTrue(test5.getInches()==0);
		Measurement test6 = test3.plus(test3);
		assertTrue(test6.getFeet()== 2);
		assertTrue(test6.getInches()==4);
	}
	public void testMinus() {
		Measurement test3 = new Measurement(1, 2);
		Measurement test4 = new Measurement(1, 22);
		Measurement test5 = test4.minus(test3);
		assertTrue(test5.getFeet()== 1);
		assertTrue(test5.getInches()== 8);
		Measurement test6 = new Measurement(0, 5);
		Measurement test7 = test3.minus(test6);
		assertTrue(test7.getFeet()== 0);
		assertTrue(test7.getInches()== 9);
	}
	public void testMultiple() {
		Measurement test3 = new Measurement(1, 2);
		Measurement test5 = test3.multiple(1);
		assertTrue(test5.getFeet()== 1);
		assertTrue(test5.getInches()==2);
		Measurement test6 = test3.multiple(20);
		assertTrue(test6.getFeet()== 23);
		assertTrue(test6.getInches()==4);
	}
	public void testToString() {
		Measurement test3 = new Measurement(1, 2);
		assertTrue(test3.toString().equals(new String(1 + "'" + 2 +'"')));
	}
}
