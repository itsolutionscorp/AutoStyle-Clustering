import static org.junit.Assert.*;

import org.junit.Test;


public class FrenchRevolutionaryDateTest {

	@Test
	public void test() {
		FrenchRevolutionaryDate day = new FrenchRevolutionaryDate(1994,12,30);
		FrenchRevolutionaryDate day1 = new FrenchRevolutionaryDate(1995,1,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day.nextDate());
		System.out.println(day1);
		
		FrenchRevolutionaryDate day2 = new FrenchRevolutionaryDate(1995,1,30);
		FrenchRevolutionaryDate day3 = new FrenchRevolutionaryDate(1995,2,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day2.nextDate());
		System.out.println(day3);
		
		FrenchRevolutionaryDate day4 = new FrenchRevolutionaryDate(1995,2,30);
		FrenchRevolutionaryDate day5 = new FrenchRevolutionaryDate(1995,3,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day4.nextDate());
		System.out.println(day5);
		
		
		FrenchRevolutionaryDate day6 = new FrenchRevolutionaryDate(1995,7,30);
		FrenchRevolutionaryDate day7 = new FrenchRevolutionaryDate(1995,8,1);
		day.dayOfYear();
		day.nextDate();
		System.out.println(day6.nextDate());
		System.out.println(day7);
	
	}
	

}
