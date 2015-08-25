import junit.framework.TestCase;


public class GregorianDateTest extends TestCase {
	public void testNextDate() {
		Date test1 = new GregorianDate(2015, 12, 31);
		Date next1 = test1.nextDate();
		assertEquals(next1.year(), 2016);
		assertEquals(next1.month(), 1);
		assertEquals(next1.dayOfMonth(), 1);
		assertEquals(test1.year(), 2015);
		assertEquals(test1.month(), 12);
		assertEquals(test1.dayOfMonth(), 31);
		
		Date test2 = new GregorianDate(2015, 5, 31);
		Date next2 = test2.nextDate();
		assertEquals(next2.year(), 2015);
		assertEquals(next2.month(), 6);
		assertEquals(next2.dayOfMonth(), 1);
		
		Date test3 = new GregorianDate(2015, 9, 30);
		Date next3 = test3.nextDate();
		assertEquals(next3.year(), 2015);
		assertEquals(next3.month(), 10);
		assertEquals(next3.dayOfMonth(), 1);
		
		Date test4 = new GregorianDate(2015, 12, 15);
		Date next4 = test4.nextDate();
		assertEquals(next4.year(), 2015);
		assertEquals(next4.month(), 12);
		assertEquals(next4.dayOfMonth(), 16);
		
		Date test5 = new GregorianDate(2015, 2, 28);
		Date next5 = test5.nextDate();
		assertEquals(next5.year(), 2015);
		assertEquals(next5.month(), 3);
		assertEquals(next5.dayOfMonth(), 1);
	}
}
