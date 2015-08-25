import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		boolean condition = true;
		int total = 0;
		int subtotal = 0; 
		int k = 0; 
		int prevk = 0;  
		while (condition) {
			k = scanner.nextInt();
			subtotal += k; 
			
			if (k == 0) {
				if (prevk == k && !justStarting) {
					// two consecutive 0s
					System.out.println("total " + total); 
					condition = false;
				
				}
				else {
					// only one 0
					total += subtotal; 
					System.out.println("subtotal " + subtotal); 
					subtotal = 0; 
				}
			}
			prevk = k;
			justStarting = false;

		}
		scanner.close();
		return; 
		
	}

}
