import static org.junit.Assert.*;

import org.junit.Test;


public class GregorianDateTest {

	@Test
	public void test() {
		GregorianDate day = new GregorianDate(1994,12,31);
		GregorianDate day1 = new GregorianDate(1995,1,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day.nextDate());
		System.out.println(day1);
		
		GregorianDate day2 = new GregorianDate(1995,1,31);
		GregorianDate day3 = new GregorianDate(1995,2,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day2.nextDate());
		System.out.println(day3);
		
		GregorianDate day4 = new GregorianDate(1995,2,28);
		GregorianDate day5 = new GregorianDate(1995,3,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day4.nextDate());
		System.out.println(day5);
		
		
		GregorianDate day6 = new GregorianDate(1995,7,31);
		GregorianDate day7 = new GregorianDate(1995,8,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day6.nextDate());
		System.out.println(day7);
	
	}


		
	
}
