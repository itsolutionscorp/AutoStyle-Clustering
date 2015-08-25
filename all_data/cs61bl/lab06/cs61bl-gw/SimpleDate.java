import junit.framework.TestCase;


public class FrenchRevolutionaryDateTest extends TestCase {

	
	@Test
	public void testDate1() {
		FrenchRevolutionaryDate testDay1 = new FrenchRevolutionaryDate(2014, 2, 4);
		assertEquals(testDay1.nextDate().toString(), "5/2/2014"); 
		System.out.println(testDay1.nextDate());
	}
	
	
	@Test
	public void testDate2() {
		FrenchRevolutionaryDate testDay2 = new FrenchRevolutionaryDate(2014, 12, 30);
		assertEquals(testDay2.nextDate().toString(), "1/13/2014");
		System.out.println(testDay2.nextDate()); 
	}
	
	@Test
	public void testDate3() {
		FrenchRevolutionaryDate testDay3 = new FrenchRevolutionaryDate(2014, 13, 5);
		assertEquals(testDay3.nextDate().toString(), "1/1/2015");
		System.out.println(testDay3.nextDate()); 
	}
	
	@Test
	public void testIsLeapYear1 () {
		FrenchRevolutionaryDate testLeap1 = new FrenchRevolutionaryDate(2014, 12, 31);
		assertEquals(testLeap1.LeapYear(), false);
		System.out.println (testLeap1.LeapYear());
		
	}
	
	@Test
	public void testIsLeapYear2 () {
		FrenchRevolutionaryDate testLeap1 = new FrenchRevolutionaryDate(2004, 12, 31);
		assertEquals(testLeap1.LeapYear(), true);
		System.out.println (testLeap1.LeapYear());
	}
	
	
}
