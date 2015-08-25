/* Samuel Chia -dh
 * Samuel Steady - ds
 */
public class YearStuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year = 2000;
		if (year % 400 != 0){
			if (year % 4 != 0){
				System.out.println(year + " is not a leap year.");
			}
			else{
				if (year % 100 != 0){
					System.out.println(year + " is a leap year.");
				}
				else{
					System.out.println(year + " is not a leap year.");
				}
			}
		}
		else{
			System.out.println(year + " is a leap year.");
		}
	}
}