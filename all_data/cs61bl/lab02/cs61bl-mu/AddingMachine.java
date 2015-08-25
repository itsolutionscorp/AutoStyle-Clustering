import java.util.*;

public class AddingMachine {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int zero = 1;
		while (true) {
			int k;
			k = scanner.nextInt();
			if (justStarting) {
				if (k != 0) {
					subtotal += k;
					zero = 1;

				} else if (k == zero) {
					justStarting = false;
					System.out.println(total);
				} else {
					System.out.println(subtotal);
					total += subtotal;
					subtotal = 0;
					zero = 0;
				}
				// TODO Your code here
			}
		}

	}
}
