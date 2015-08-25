import junit.framework.TestCase;


public class DateTest extends TestCase {
	
	public void testFrenchRevolutionaryDate() {
		FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(1799, 5, 21);
		assertTrue(d.dayOfYear() == 141);
		assertTrue(d.dayOfMonth() == 21);
		assertTrue(d.nextDate().dayOfMonth() == 22);
		d = new FrenchRevolutionaryDate(1799, 13, 5);
		assertTrue(d.nextDate().year() == 1800);
		d = new FrenchRevolutionaryDate(1799, 12, 30);
		assertTrue(d.nextDate().month() == 13);
	}
	
	public void testGregorianDate() {
		GregorianDate d = new GregorianDate(1799, 5, 21);
		assertTrue(d.dayOfYear() == 141);
		assertTrue(d.dayOfMonth() == 21);
		assertTrue(d.nextDate().dayOfMonth() == 22);
		d = new GregorianDate(1799, 12, 31);
		assertTrue(d.nextDate().year() == 1800);
		d = new GregorianDate(1799, 2, 28);
		assertTrue(d.nextDate().month() == 3);
	}
}
