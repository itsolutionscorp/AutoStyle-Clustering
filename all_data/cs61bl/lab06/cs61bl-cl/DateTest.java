//package lab06;

import junit.framework.TestCase;

public class DateTest extends TestCase {
	
	public class DateTest {

		@Test
		public void nextDayGregorianDateTest() {
			Date day1;
			Date day2;
			Date next_day1;
			day1 = new GregorianDate(5,12,3);
			day2 = new GregorianDate(5,12,4);
			// Advancing the day by 1
			next_day1= day1.nextDay();
	    	// Comparing two dates are equal after calling nextDay method
			//Assert.assertEquals(day2.compareTo(next_day1),0);
		}
		
		// Same process for GregorianDateTest
		

	}
	public static void main (String[] args){
		DateTest dt = new DateTest();
		dt.DateTest();
	}

}
