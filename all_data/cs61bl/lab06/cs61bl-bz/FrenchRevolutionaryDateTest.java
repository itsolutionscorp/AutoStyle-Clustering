import junit.framework.TestCase;


public class FrenchRevolutionaryDateTest extends TestCase {
	public void testNextDate() {
		Date test1 = new FrenchRevolutionaryDate(2015, 5, 30);
		Date next1 = test1.nextDate();
		assertEquals(next1.year(), 2015);
		assertEquals(next1.month(), 6);
		assertEquals(next1.dayOfMonth(), 1);
		assertEquals(test1.year(), 2015);
		assertEquals(test1.month(), 5);
		assertEquals(test1.dayOfMonth(), 30);
		
		Date test2 = new FrenchRevolutionaryDate(2015, 13, 5);
		Date next2 = test2.nextDate();
		assertEquals(next2.year(), 2016);
		assertEquals(next2.month(), 1);
		assertEquals(next2.dayOfMonth(), 1);
		
		Date test3 = new FrenchRevolutionaryDate(2015, 12, 5);
		Date next3 = test3.nextDate();
		assertEquals(next3.year(), 2015);
		assertEquals(next3.month(), 12);
		assertEquals(next3.dayOfMonth(), 6);
	}
}
