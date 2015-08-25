import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int input_previous = 0;
		while (true) {
		    // TODO Your code here
			int input = scanner.nextInt();
			if (input == 0 && input_previous == 0) {
				System.out.print("total" + " " + total);
				return;
			}
			else if (input == 0) {
				System.out.print("subtotal" + " " + subtotal);
				subtotal = 0;
			}
			else {
				subtotal = subtotal + input;
				total = total + input;
			}
			input_previous = input;
		}
	}
}
