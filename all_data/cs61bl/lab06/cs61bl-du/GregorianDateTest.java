import junit.framework.TestCase;

public class GregorianDateTest extends TestCase {

	public void testOne() {
		Date d = new GregorianDate(2000, 1, 1);
		assertTrue (d.year() == 2000);
		assertTrue (d.month() == 1);
		assertTrue (d.dayOfMonth() == 1);
		assertTrue (d.dayOfYear() == 1);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2000);
		assertTrue (dn.month() == 1);
		assertTrue (dn.dayOfMonth() == 2);
	}
	
	public void testTwo() {
		Date d = new GregorianDate(2000, 1, 31);
		assertTrue (d.dayOfYear() == 31);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2000);
		assertTrue (dn.month() == 2);
		assertTrue (dn.dayOfMonth() == 1);
	}
	
	public void testThree() {
		Date d = new GregorianDate(2000, 2, 27);
		assertTrue (d.dayOfYear() == 58);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2000);
		assertTrue (dn.month() == 2);
		assertTrue (dn.dayOfMonth() == 28);
	}
	
	public void testFour() {
		Date d = new GregorianDate(2000, 12, 31);
		assertTrue (d.dayOfYear() == 365);
		Date dn = d.nextDate();
		assertTrue (dn.year() == 2001);
		assertTrue (dn.month() == 1);
		assertTrue (dn.dayOfMonth() == 1);
	}
}