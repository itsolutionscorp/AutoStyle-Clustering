package lab01;

public class YearStuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year = 2000;
		if (year % 400 != 0) {
		/*Check first if year is divisible by 400
		 * If year can be divisible by 400, then print out "this is a leap year
		 * If not check if it is divisble by 4 but not by 100.	
		 */
			if (year % 4 != 0) {
				System.out.println (year + " is not a leap year.");
				}
			else{
				if (year % 100 != 0){
					System.out.println (year + " is a leap year.");
				}
			}
			}
		else{
			System.out.println (year + " is a leap year.");
			}
		}
	}

