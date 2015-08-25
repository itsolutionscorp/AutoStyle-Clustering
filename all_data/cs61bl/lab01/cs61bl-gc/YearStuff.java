package lab01;

public class YearStuff {

	public static void main(String[] args) {
		leap(1992);
	}
	public static void leap(int year) {
		if (year % 4 != 0) {
			System.out.println (year + " is not a leap year.");
		}
		else if (year % 100 != 0) {
			System.out.println (year + " is a leap year.");
		}
		else if (year % 400 != 0) {
			System.out.println (year + " is not a leap year.");
		} else
			System.out.println (year + " is a leap year.");
	}	
}