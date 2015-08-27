 import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int last = 1;
		while (true) {
		    // TODO Your code here
			int k = scanner.nextInt();
	
			if (k == 0) { 
				if (last == 0) {
					System.out.println("total " + total);
					return;
				} else {
					last = 0;
					System.out.println("subtotal " + subtotal);
					subtotal = 0;
				}
			} else {
				last = k;
				subtotal = subtotal + k;
				total = total + k;
			}
		}
	}
}
