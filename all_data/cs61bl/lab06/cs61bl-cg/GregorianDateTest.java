import junit.framework.TestCase;


public class GregorianDateTest extends TestCase {
	
	public void testNextDate(){
		GregorianDate c = new GregorianDate(2000, 1, 31);
		assertTrue(c.month() == 1);
		assertTrue(c.year() == 2000);
		assertTrue(c.dayOfMonth() == 31);
		Date d = c.nextDate();
		System.out.println(d.toString());
		System.out.println(d.month());
		assertTrue(d.month() == 2);
		assertTrue(d.year() == 2000);
		assertTrue(d.dayOfMonth() == 1);
	}
}
