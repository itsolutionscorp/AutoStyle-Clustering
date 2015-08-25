package lab01;

/**
 * @author annakang
 *A leap year is either divisible by 400 or divisible by 4 and not by 100.
 */
public class YearStuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year = 2007;
		if (year % 100 != 0) {
			if (year % 4 != 0) {
				System.out.println (year + " is not a leap year.");
			}
			else{
				System.out.println (year + " is a leap year.");
			}
		}
		else{
			if (year % 400 != 0) {
				System.out.println (year + " is not a leap year.");
			}
			else{
				System.out.println (year + " is a leap year.");
			}
		}
		
	}
	}
