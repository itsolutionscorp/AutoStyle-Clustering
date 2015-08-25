
public class YearStuff {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int year = 1963 ;
		// TODO Auto-generated method stub
		if (year % 100 != 0){
			if (year % 400 !=0) {
				if (year % 4 != 0) 
					System.out.println ( year + " is not a leap year.");
				else
					System.out.println ( year + " is a leap year.");
			}
			else {
				System.out.println ( year + " is a leap year.");	
			}
		}
		else 
			if (year %400 !=0)
			System.out.println ( year + " is not a leap year.");
			else 
			System.out.println ( year + " is a leap year.");
		
	}
					
}
	
		



