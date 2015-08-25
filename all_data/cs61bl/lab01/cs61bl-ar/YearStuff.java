
public class YearStuff {
	public static void main(String[]args){
		int year = 2008;
		if (year % 100 != 0) {
			if (year % 4 == 0) {
				System.out.println (year + " is a leap year.");
			}
			else {
				System.out.println (year + " is not leap year.");
			}
		}
		else if (year % 400 == 0) {
				System.out.println (year + " is a leap year.");
				}
		else {
			System.out.println (year + " is not a leap year.");
		}
	}
}
