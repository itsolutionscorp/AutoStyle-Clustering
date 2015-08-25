import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    // TODO Your code here
			int k; 
			k = scanner.nextInt(); 
			if (k == 0) {
				if (justStarting) {
					System.out.println("subtotal " + subtotal);
					total += subtotal; 
					subtotal = 0; 
					justStarting = false;
				}
				else {
					System.out.println("total " + total);
					return;
				}
			}
			else {
				justStarting = true; 
			}
			subtotal += k; 
		}
	}
}
