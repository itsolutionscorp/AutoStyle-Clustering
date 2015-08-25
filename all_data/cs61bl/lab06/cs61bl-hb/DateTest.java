import junit.framework.TestCase;


public class DateTest extends TestCase {
	
	public void testConstructor(){
		Date n = new GregorianDate(2000, 12, 31);
		assertTrue (n.year() == 2000);
		assertTrue (n.month() == 12);
		assertTrue (n.dayOfMonth() == 31);
		
		Date k = new GregorianDate(2000, 12, 31);
		assertTrue (k.year() == 2000);
		assertTrue (k.month() == 12);
		assertTrue (k.dayOfMonth() == 31);
		
	}
	
	public void testNextDayGregorian(){
		Date n = new GregorianDate(2000, 1, 1);
		Date next = n.nextDate();
	
		assertEquals (1, next.month());
		assertEquals (2, next.dayOfMonth());
		assertEquals (2000, next.year());
	
	}
	public void testNextDayFrench(){
		Date k = new FrenchRevolutionaryDate(2000, 13, 5);
		Date fr = k.nextDate();
		assertTrue (fr.year() == 2001);
		assertTrue (fr.month() == 1);
		assertTrue (fr
				.dayOfMonth() == 1);
	}
}
