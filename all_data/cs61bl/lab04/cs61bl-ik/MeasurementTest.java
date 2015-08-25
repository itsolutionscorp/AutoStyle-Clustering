import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInit() {
		/* check that the measurement's feet and inches accurately
		 * reflect the arguments given into the constructor. Takes
		 * care of situations in which inches >= 12
		 */
		Measurement m1 = new Measurement();
		assertTrue (m1.getFeet() == 0);
		assertTrue (m1.getInches() == 0);
		
		Measurement m2 = new Measurement(80);
		assertTrue (m2.getFeet() == 80);
		assertTrue (m2.getInches() == 0);
		
		Measurement m3 = new Measurement (5, 6);
		assertTrue (m3.getFeet() == 5);
		assertTrue (m3.getInches() == 6);
		
		Measurement m4 = new Measurement(3, 18);
		assertTrue (m4.getFeet() == 4);
		assertTrue (m4.getInches() == 6);
		
		Measurement m5 = new Measurement(6, 12);
		assertTrue (m5.getFeet() == 7);
		assertTrue (m5.getInches() == 0);
		
		Measurement m6 = new Measurement(4, 37);
		assertTrue (m6.getFeet() == 7);
		assertTrue (m6.getInches() == 1);
	}
	
	public void testGetFeet() {
		/* check that the getFeet() method gives the correct
		 * output, even in cases in which the inches provided
		 * are >= 12 (wherein the extra is/are added into feet).
		 */
		Measurement m1 = new Measurement();
		assertTrue (m1.getFeet() == 0);
		
		Measurement m2 = new Measurement(3);
		assertTrue (m2.getFeet() == 3);
		
		Measurement m3 = new Measurement(5, 4);
		assertTrue (m3.getFeet() == 5);
		
		Measurement m4 = new Measurement (5, 12);
		assertTrue (m4.getFeet() == 6);
		
		Measurement m5 = new Measurement (8, 27);
		assertTrue (m5.getFeet() == 10);
		
		Measurement m6 = new Measurement(1412, 60);
		assertTrue (m6.getFeet() == 1417);
	}
	
	public void testGetInches() {
		/* check that the getInches() method gives the correct
		 * output, even in cases in which the inches provided
		 * are >= 12 (extra added into feet).
		 */
		Measurement m1 = new Measurement();
		assertTrue (m1.getInches() == 0);
		
		Measurement m2 = new Measurement(80000);
		assertTrue (m2.getInches() == 0);
		
		Measurement m3 = new Measurement(6, 1);
		assertTrue (m3.getInches() == 1);
		
		Measurement m4 = new Measurement(3, 12);
		assertTrue (m4.getInches() == 0);
		
		Measurement m5 = new Measurement(4, 18);
		assertTrue (m5.getInches() == 6);
		
		Measurement m6 = new Measurement(17, 35);
		assertTrue (m6.getInches() == 11);
	}
	
	public void testPlus() {
		/* tests the plus() method. Makes sure that the resulting
		 * measurement has the correct feet & inches (no inches >= 12)
		 */
		Measurement m1 = new Measurement(3, 5);
		
		Measurement test1 = m1.plus(new Measurement());
		assertTrue (test1.getFeet() == 3);
		assertTrue (test1.getInches() == 5);
		
		Measurement test2 = m1.plus(new Measurement(79));
		assertTrue (test2.getFeet() == 82);
		assertTrue (test2.getInches() == 5);
		
		Measurement test3 = m1.plus(new Measurement(1, 4));
		assertTrue (test3.getFeet() == 4);
		assertTrue (test3.getInches() == 9);
		
		Measurement test4 = m1.plus(new Measurement(5, 8));
		assertTrue (test4.getFeet() == 9);
		assertTrue (test4.getInches() == 1);
		
		Measurement test5 = m1.plus(new Measurement(6, 12));
		assertTrue (test5.getFeet() == 10);
		assertTrue (test5.getInches() == 5);
	
		Measurement test6 = m1.plus(new Measurement(3, 20));
		assertTrue (test6.getFeet() == 8);
		assertTrue (test6.getInches() == 1);
	}
	
	public void testMinus() {
		/* tests the minus() method. Makes sure that the resulting
		 * measurement has the correct feet & inches (no inches >= 12)
		 */
		Measurement m1 = new Measurement(5, 8);
		Measurement m2 = new Measurement(5, 68);
		
		Measurement test1 = m1.minus(new Measurement());
		assertTrue (test1.getFeet() == 5);
		assertTrue (test1.getInches() == 8);
		
		Measurement test2 = m1.minus(new Measurement(4));
		assertTrue (test2.getFeet() == 1);
		assertTrue (test2.getInches() == 8);
		
		Measurement test3 = m1.minus(new Measurement(1, 4));
		assertTrue (test3.getFeet() == 4);
		assertTrue (test3.getInches() == 4);
		
		Measurement test4 = m1.minus(new Measurement(2, 9));
		assertTrue (test4.getFeet() == 2);
		assertTrue (test4.getInches() == 11);
		
		Measurement test5 = m2.minus(new Measurement(1, 34));
		assertTrue (test5.getFeet() == 6);
		assertTrue (test5.getInches() == 10);
		
		Measurement test6 = m2.minus(new Measurement(3, 12));
		assertTrue (test6.getFeet() == 6);
		assertTrue (test6.getInches() == 8);
	}
	
	public void testMultiple() {
		/* tests the multiple() method. Makes sure that the resulting
		 * measurement has the correct feet & inches (no inches >= 12)
		 */
		Measurement m1 = new Measurement(0, 7);
		Measurement test1 = m1.multiple(3);
		assertTrue (test1.getFeet() == 1);
		assertTrue (test1.getInches() == 9);
		
		Measurement m2 = new Measurement(5, 8);
		Measurement test2 = m2.multiple(2);
		assertTrue (test2.getFeet() == 11);
		assertTrue (test2.getInches() == 4);
		
		Measurement m3 = new Measurement(6);
		Measurement test3 = m3.multiple(18);
		assertTrue (test3.getFeet() == 108);
		assertTrue (test3.getInches() == 0);
		
		Measurement m4 = new Measurement(2, 14);
		Measurement test4 = m4.multiple(4);
		assertTrue (test4.getFeet() == 12);
		assertTrue (test4.getInches() == 8);
		
		Measurement m5 = new Measurement(3, 4);
		Measurement test5 = m5.multiple(7);
		assertTrue (test5.getFeet() == 23);
		assertTrue (test5.getInches() == 4);
		
		Measurement m6 = new Measurement();
		Measurement test6 = m6.multiple(1000000);
		assertTrue (test6.getFeet() == 0);
		assertTrue (test6.getInches() == 0);
	}
	
	public void testToString() {
		/* tests the toString() method. Makes sure that the resulting
		 * measurement is accurately represented in (feet'inches'')
		 * form. Also checking to see that the inches >= 12 cases 
		 * are taken care of. 
		 */
		Measurement m1 = new Measurement();
		String test1 = m1.toString();
		assertTrue (test1.equals("0'0''"));
		
		Measurement m2 = new Measurement(8);
		String test2 = m2.toString();
		assertTrue (test2.equals("8'0''"));
		
		Measurement m3 = new Measurement(5, 4);
		String test3 = m3.toString();
		assertTrue (test3.equals("5'4''"));
		
		Measurement m4 = new Measurement(4, 16);
		String test4 = m4.toString();
		assertTrue (test4.equals("5'4''"));
		
		Measurement m5 = new Measurement(8, 12);
		String test5 = m5.toString();
		assertTrue (test5.equals("9'0''"));
		
		Measurement m6 = new Measurement(3, 27);
		String test6 = m6.toString();
		assertTrue (test6.equals("5'3''"));
	}
}
