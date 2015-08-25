import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int previous = 1;
		while (true) {
		    // TODO Your code here
			int input;
			input = scanner.nextInt();
			
			if (input != 0) {
				total += input;
				subtotal += input;
				previous = input;
			} else if (input == 0) {
				if (previous == 0) {
					System.out.println("total " + total);
					return;
				} else {
					System.out.println("subtotal " + subtotal);
					subtotal = 0;
					previous = 0;
					}
				}
			}
		}
	}

