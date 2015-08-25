import static org.junit.Assert.*;

import org.junit.Test;


public class FrenchRevolutionaryDateTest {

	@Test
	public void testnextDate() {
		FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(1996, 10, 29);
//		System.out.println(a.month() + " " + a.year() + " " + a.dayOfMonth());
//		System.out.println(b.month() + " " + b.year() + " " + b.dayOfMonth());
		Date b = a.nextDate();
		assertTrue (b.month() == 10);
		assertTrue (b.year() == 1996);
		assertTrue (b.dayOfMonth() == 30);
		Date c = b.nextDate();
		assertTrue (c.month() == 11);
		assertTrue (c.year() == 1996);
		assertTrue (c.dayOfMonth() == 1);
		FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(1996, 13, 5);
		Date e = d.nextDate();
		assertTrue (e.month() == 1);
		assertTrue (e.year() == 1997);
		assertTrue (e.dayOfMonth() == 1);
	}

}
