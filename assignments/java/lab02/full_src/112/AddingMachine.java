import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		
		// TODO Your code here
		int last_input = 1;
		while (justStarting) {
			int input;
			input = scanner.nextInt();
			
			subtotal += input;
			total += input;
			if (last_input == 0 && input == 0) {
				System.out.println("total " + total);
				justStarting = false;
			}
			else if (input == 0) {
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			}
			last_input = input;
	    }
		
	}

}
