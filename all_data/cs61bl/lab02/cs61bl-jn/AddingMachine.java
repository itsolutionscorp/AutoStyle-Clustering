import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int prev = 1;
		int next;
		while (true) {
		    // TODO Your code here
			next = scanner.nextInt();
			if (next == 0 && prev == 0) {
				System.out.println("total " + total);
				break;
			} else if (next == 0 && prev != 0){
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			} else {
				total += next;
				subtotal += next;
			}
			prev = next;
		}
	}

}
