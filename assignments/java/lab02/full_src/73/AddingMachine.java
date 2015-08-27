import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k;
		while (true) {
		    // TODO Your code here
			k = scanner.nextInt();
			total += k;
			if (justStarting) {
				subtotal += k;
				if (k == 0) {
					System.out.println("subtotal " + subtotal);
					subtotal = 0;
					justStarting = false;
				}
			} else {
				if (k == 0) {
					System.out.println("total " + total);
					return;
				}
				subtotal += k;
				justStarting = true;
			}
		}
	}

}
