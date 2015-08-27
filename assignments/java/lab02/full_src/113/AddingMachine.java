import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
			int k = scanner.nextInt();
			
			// below is the one-loop version of AddingMachine
//			if (justStarting == true) {
//				if (k == 0) {
//					System.out.println("subtotal " + subtotal);
//				}
//				justStarting = false;
//			}
//			else {
//				if (k == 0) {
//					if (subtotal == 0) {
//						System.out.println("total " + total);
//						return;
//					}
//					else {
//						System.out.println("subtotal " + subtotal);
//						subtotal = 0;
//					}
//				}
//			}
//			subtotal += k;
//			total += k;
			
			// below is the two-loop version of AddingMachine
			while (k != 0) {
				if (justStarting) {
					justStarting = false;
				}
				subtotal += k;
				total += k;
				k = scanner.nextInt();
			}
			if (justStarting) {
				System.out.println("subtotal " + subtotal);
			}
			else {
				if (subtotal == 0) {
					System.out.println("total " + total);
					return;
				}
				else {
					System.out.println("subtotal " + subtotal);
					subtotal = 0;
				}
			}
		}
	}

}

/* Input should be handled as follows: a nonzero value should be
 * added into a subtotal; a zero value should print the subtotal
 * and reset it to zero; two consecutive zeroes should print the
 * total of all values input, then terminate the program.*/