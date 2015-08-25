import static org.junit.Assert.*;

import org.junit.Test;


public class FrenchRevolutionaryDateTest {

	@Test
	public void testNextDate() {
		FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(2015,13,5);
		Date b= a.nextDate();
		assertTrue(b.month()==1);
		assertTrue(b.dayOfMonth()==1);
		assertTrue(b.year()==2016);
	}

}
