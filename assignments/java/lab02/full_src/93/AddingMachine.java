import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int sawZero = 0;
		while (justStarting) {
		    // TODO Your code here
			int input = scanner.nextInt();
			if (sawZero == 0 && input == 0){
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
				sawZero = 1;
			}
			else if (sawZero == 1 && input == 0){
				System.out.println("total " + total);
				return;
			}
			else {
				subtotal += input;
				sawZero = 0;
			}
		}
	}

}