import junit.framework.TestCase;

public class DateTest extends TestCase {
	
	public void testgDate() {
		Date d = new GregorianDate(1995, 8, 28);
		assertTrue(d.year() == 1995);
		assertTrue(d.month() == 8);
		assertTrue(d.dayOfMonth() == 28);
	}
	
	public void testfDate() {
		Date d = new FrenchRevolutionaryDate(1995, 8, 28);
		assertTrue(d.year() == 1995);
		assertTrue(d.month() == 8);
		assertTrue(d.dayOfMonth() == 28);
	}
	
	public void testgtoString() {
		Date d = new GregorianDate(1995, 8, 28);
		assertTrue(d.toString().equals("28/8/1995"));
	}
	
	public void testftoString() {
		Date d = new FrenchRevolutionaryDate(1995, 8, 28);
		assertTrue(d.toString().equals("28/8/1995"));
	}
	
	public void testgdayofYear() {
		Date d = new GregorianDate(1995, 12, 31);
		assertTrue(d.dayOfYear() == 365);
		Date f = new GregorianDate(1995, 3, 1);
		assertTrue(f.dayOfYear() == 60);
		Date g = new GregorianDate(1995, 4, 1);
		assertTrue(g.dayOfYear() == 91);
	}
	
	public void testfdayofYear() {
		Date d = new FrenchRevolutionaryDate(1995, 12, 31);
		assertTrue(d.dayOfYear() == 361);
		Date f = new FrenchRevolutionaryDate(1995, 3, 1);
		assertTrue(f.dayOfYear() == 61);
		Date g = new FrenchRevolutionaryDate(1995, 4, 1);
		assertTrue(g.dayOfYear() == 91);
	}
	
	public void testgNextDay() {
		Date d = new GregorianDate(1995, 12, 31);
		Date a = d.nextDate();
		assertTrue(a.toString().equals("1/1/1996"));
		Date e = new GregorianDate(1995, 2, 28);
		Date b = e.nextDate();
		assertTrue(b.toString().equals("1/3/1995"));
		Date f = new GregorianDate(1995, 2, 26);
		Date c = f.nextDate();
		assertTrue(c.toString().equals("27/2/1995"));
	}
	
	public void testfNextDay() {
		Date d = new FrenchRevolutionaryDate(1995, 13, 5);
		Date a = d.nextDate();
		assertTrue(a.toString().equals("1/1/1996"));
		Date e = new FrenchRevolutionaryDate(1995, 2, 30);
		Date b = e.nextDate();
		assertTrue(b.toString().equals("1/3/1995"));
		Date f = new FrenchRevolutionaryDate(1995, 2, 28);
		Date c = f.nextDate();
		assertTrue(c.toString().equals("29/2/1995"));
	}

}
