import java.util.*;

public class AddingMachine {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k;
		boolean doubleZero = false;
		while (true) {
			// TODO Your code here
			k = scanner.nextInt();
			if (k != 0) {
				total = total + k;
				subtotal = subtotal + k;
				doubleZero = false;
			} else if (k == 0 && doubleZero) {
				System.out.println("total " + total);
				return;
			}

			else if (k == 0) {

				System.out.println("subtotal " + subtotal);
				subtotal = 0;
				doubleZero = true;
			}
		}
	}

}
