import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int lastInput = 32;
		
		while (true) {
		    // TODO Your code here
			
			int k;
			k = scanner.nextInt();
			total += k;
			subtotal+=k;
			
			if (k==0){
				System.out.println("subtotal: "+ subtotal);
				subtotal = 0;
			}
			if (!justStarting){
				if (lastInput == 0 && k==0){
					System.out.println("total: "+ total);
					return;
					}
				}
			lastInput = k;
			justStarting = false;

		}
	 
	}

}
