import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;

		int last = 1;

		while (true) {
			int num = scanner.nextInt();
			if (num == 0 && last == 0) {
				System.out.println("total " + total);
				return;
			} else if (num == 0) {
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
			} else {
				subtotal += num;
			}
			last = num;
		}
	}

}
