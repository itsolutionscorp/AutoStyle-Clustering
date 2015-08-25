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
			if (justStarting) {
				if (k == 0) {
					total += subtotal;
					System.out.println("subtotal " + subtotal);
					subtotal = 0;
					justStarting = false;
				} else {
					subtotal += k;
				}
			} else {
				if (k == 0) {
					System.out.println("total " + total);
					return;
				} else {
					subtotal += k;
					justStarting = true;
				}
			}
		}
	}

}
