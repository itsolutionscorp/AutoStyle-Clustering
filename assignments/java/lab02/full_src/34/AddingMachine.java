import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    // TODO Your code here
//			int k;
//			k = scanner.nextInt();
//			if (k == 0) {
//				if (subtotal == 0 && !(total != 0) && justStarting != true) {
//					System.out.println(total);
//					return;
//				}
//				total = subtotal + total;
//				System.out.println(subtotal);
//				subtotal = 0;
//			} else {
//				subtotal = subtotal + k;
//			}
//			if (justStarting) {
//				justStarting = false;
//			}
			
			int k;
			k = scanner.nextInt();
			if (k == 0) {
				total = subtotal + total;
				System.out.println(subtotal);
				subtotal = 0;
				int j = scanner.nextInt();
				if (j == 0) {
					System.out.println(total);
					return;
				}
				subtotal = subtotal + j;
			} else {
				subtotal = subtotal + k;
			}
		}
	}

}
