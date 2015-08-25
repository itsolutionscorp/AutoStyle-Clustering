
public class YearStuff {

	
	public static void main(String[] args) {
		int year = 20034;
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			System.out.println (year + " is a leap year.");
			
		}

		
		else {
			System.out.println (year + " is not a leap year.");
			
		}

	}

}
