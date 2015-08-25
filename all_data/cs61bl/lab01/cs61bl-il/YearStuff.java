// package lab01;

public class YearStuff {
	
	public static void leapyear(int year){
		if (year % 4 != 0){
			System.out.println(year + " is not a leap year");
		} else { 
				if (year % 100 != 0) {
					System.out.println(year + " is a leap year");
			
		
				} else {
					if (year % 400 !=0){
						System.out.println(year + " is not a leap year");	
						} 
				
					else {
							System.out.println(year + " is a leap year");
						}
					} 
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		YearStuff.leapyear(2000);
		YearStuff.leapyear(1900);
		YearStuff.leapyear(2004);
		YearStuff.leapyear(2003);
	}

}
