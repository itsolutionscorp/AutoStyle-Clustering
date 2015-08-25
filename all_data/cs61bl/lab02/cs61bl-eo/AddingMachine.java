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
					justStarting = false;
					System.out.print(subtotal);
					total = total + subtotal;
					subtotal = 0;
				} else {
					System.out.print(total);
					return;
				}
			} else {
				subtotal = subtotal + k;
				justStarting = true;
			}
		}
	}
}

