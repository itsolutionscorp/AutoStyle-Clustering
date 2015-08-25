import junit.framework.TestCase;


public class FrenchRevolutionaryDateTest extends TestCase {

	public void testNextDate() {
		FrenchRevolutionaryDate c = new FrenchRevolutionaryDate(2000, 1, 30);
		assertTrue(c.month() == 1);
		assertTrue(c.year() == 2000);
		assertTrue(c.dayOfMonth() == 30);
		Date d = c.nextDate();
		System.out.println(d.toString());
		System.out.println(d.month());
		assertTrue(d.month() == 2);
		assertTrue(d.year() == 2000);
		assertTrue(d.dayOfMonth() == 1);
	}
}
