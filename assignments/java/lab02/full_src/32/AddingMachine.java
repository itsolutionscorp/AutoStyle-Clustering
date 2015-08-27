import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int consecutiveZeroes = 0;
		while (true) {
		    // TODO Your code here
			int k = scanner.nextInt();
			if (k == 0) {
				consecutiveZeroes++;
				if (consecutiveZeroes > 1) {
					System.out.println("total " + total);
					return;
					}
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			} else {
			consecutiveZeroes = 0;
			subtotal = subtotal + k;
			total = total + k;
			}
		}
	}
}