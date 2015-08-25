import junit.framework.TestCase;


public class FrenchRevolutionaryDateTest extends TestCase {
	
	public void testConstructor(){
		FrenchRevolutionaryDate b = new FrenchRevolutionaryDate(2015, 1, 3);
		assertTrue(b.dayOfMonth() == 3);
		assertTrue(b.month() == 1);
		assertTrue(b.year() ==2015);
	}
	
	public void testdayOfYear(){
		FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(2015, 6, 30);
		assertTrue(a.dayOfYear() == 180);
	}
	
	public void testnextDay(){
		FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(2010,13,5);
		assertTrue (a.nextDate().toString().equals("1/1/2011")); //should be 1/1/2011
		FrenchRevolutionaryDate b = new FrenchRevolutionaryDate(2010,10,30);
		//System.out.println(b.nextDate().toString());
		assertTrue (b.nextDate().toString().equals("11/1/2010"));//should be 11/1/2010
		FrenchRevolutionaryDate c = new FrenchRevolutionaryDate(2015,6,20);
		assertTrue (c.nextDate().toString().equals("6/21/2015"));;//should be 6/21/2015
	}
	
	// test the greogianData
	
	public void testGConstructor(){
		GregorianDate b = new GregorianDate(2015, 1, 3);
		assertTrue(b.dayOfMonth() == 3);
		assertTrue(b.month() == 1);
		assertTrue(b.year() ==2015);
	}
	
	public void testGdayOfYear(){
		GregorianDate a = new GregorianDate(2015, 6, 30);
		assertTrue(a.dayOfYear() == (31+28+31+30+31+30));
	}
	
	public void testGnextDay(){
		GregorianDate a = new GregorianDate(2010,12,31);
		assertTrue (a.nextDate().toString().equals("1/1/2011")); //should be 1/1/2011
		GregorianDate b = new GregorianDate(2010,10,31);
		assertTrue (b.nextDate().toString().equals("11/1/2010"));//should be 11/1/2010
		GregorianDate c = new GregorianDate(2015,6,20);
		assertTrue (c.nextDate().toString().equals("6/21/2015"));;//should be 6/21/2015

	}
	
}
