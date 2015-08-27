import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int prev=1; 
		while (true) {
		    // TODO Your code here
			int k;
			k = scanner.nextInt();
			total = total + k; 
			if (prev == 0 && k == 0){
				System.out.println(total);
				return; 
			}
			else if (k == 0){
				//subtotal = 0;
				System.out.println(subtotal);
				subtotal = 0;
				
			} 
			else {
				subtotal = subtotal + k; 
			} 
			prev=k;
		}
	}
}
