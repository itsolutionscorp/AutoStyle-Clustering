import junit.framework.TestCase;


public class GregorianDateTest extends TestCase {
	public void testNextDate(){
		GregorianDate g = new GregorianDate (1, 1, 1);
		assertTrue (g.nextDate().toString().equals((new GregorianDate (1, 1, 2)).toString()));
		assertTrue (g.toString().equals((new GregorianDate (1, 1, 1)).toString()));
		GregorianDate g1 = new GregorianDate (1, 2, 28);
		assertTrue (g1.nextDate().toString().equals((new GregorianDate (1, 3, 1)).toString()));
		GregorianDate g2 = new GregorianDate (1, 3, 31);
		assertTrue (g2.nextDate().toString().equals((new GregorianDate (1, 4, 1)).toString()));
		GregorianDate g3 = new GregorianDate (1, 4, 30);
		assertTrue (g3.nextDate().toString().equals((new GregorianDate (1, 5, 1)).toString()));
		GregorianDate g4 = new GregorianDate (1, 12, 31);
		assertTrue (g4.nextDate().toString().equals((new GregorianDate (2, 1, 1)).toString()));
	}
}
