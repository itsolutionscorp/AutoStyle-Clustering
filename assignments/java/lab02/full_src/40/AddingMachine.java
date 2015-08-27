import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int lastk = 1;
		while (true) {
		    // TODO Your code here
			int k;
			k = scanner.nextInt();
			if (lastk == 0) {
				if (k == 0) {
					System.out.println ("total " + total);
					return;
				}
			}
			if (k == 0) {
				System.out.println ("subtotal " + subtotal);
				total = total + subtotal;
				subtotal = 0;
			}
			else {
				subtotal = subtotal + k;
			}
			lastk = k;
		}
	}

}
