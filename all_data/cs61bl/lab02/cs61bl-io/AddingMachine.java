import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int last = 0;
		while (true) {
		    // TODO Your code here
			int k;
			k = scanner.nextInt();
			if (justStarting) {
				System.out.println ("subtotal " + subtotal);
				justStarting = false;
			} else if (last == 0 && k == 0) {
				System.out.println ("total " + total);
				return;
			} else if (k == 0) {
				System.out.println ("subtotal " + subtotal);
				subtotal = 0;
			} else {
				total = total + k;
				subtotal = subtotal + k;
			}
			last = k;
		}
	}

}
