import static org.junit.Assert.*;

import org.junit.Test;


public class DateTest {

	@Test
	public void testGregorian() {
		Date d = new GregorianDate(1996,12,31);
		Date n = d.nextDate();
		assertEquals(n.year(),1997);
		assertEquals(n.month(),1);
		assertEquals(n.dayOfMonth(),1);
		
		d = new GregorianDate(1990, 1, 31);
		n = d.nextDate();
		assertEquals(n.year(), 1990);
		assertEquals(n.month(),2);
		assertEquals(n.dayOfMonth(),1);
	}
	
	@Test
	public void testFrench() {
		Date d = new FrenchRevolutionaryDate(1996,13,5);
		Date n = d.nextDate();
		assertEquals(n.year(),1997);
		assertEquals(n.month(),1);
		assertEquals(n.dayOfMonth(),1);
		d = new FrenchRevolutionaryDate(1996,11,30);
		n = d.nextDate();
		assertEquals(n.year(),1996);
		assertEquals(n.month(),12);
		assertEquals(n.dayOfMonth(),1);
	}

}
