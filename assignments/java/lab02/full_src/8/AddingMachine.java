import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k;
		int exit = 1;
		while (true) {
		    // TODO Your code here
			k = scanner.nextInt();
			if (k != 0) {
				subtotal = subtotal + k;
				total = total + k;
				exit = exit + 1;
			} else if (k == 0 & exit == 0) {
				System.out.println ("total " + total);
				return;
			} else {
				System.out.println ("subtotal " + subtotal);
				subtotal = 0;
				exit = 0;
			}
			
		}
	}

}
