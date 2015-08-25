import junit.framework.TestCase;


public class FrenchRevolutionaryDateTest extends TestCase {
	public void testOne() {
		Date d = new FrenchRevolutionaryDate(2000, 1, 1);
		assertTrue (d.year() == 2000);
		assertTrue (d.month() == 1);
		assertTrue (d.dayOfMonth() == 1);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2000);
		assertTrue (dn.month() == 1);
		assertTrue (dn.dayOfMonth() == 2);
	}
	

	public void testTwo() {
		Date d = new FrenchRevolutionaryDate(2000, 1, 30);
		assertTrue (d.year() == 2000);
		assertTrue (d.month() == 1);
		assertTrue (d.dayOfMonth() == 30);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2000);
		assertTrue (dn.month() == 2);
		assertTrue (dn.dayOfMonth() == 1);
	}

	public void testThree() {
		Date d = new FrenchRevolutionaryDate(2000, 13, 5);
		assertTrue (d.year() == 2000);
		assertTrue (d.month() == 13);
		assertTrue (d.dayOfMonth() == 5);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2001);
		assertTrue (dn.month() == 1);
		assertTrue (dn.dayOfMonth() == 1);
	}
}
