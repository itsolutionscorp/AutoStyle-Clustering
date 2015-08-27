import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int zero_counter = 0;
		while (true) {
			int k = scanner.nextInt();
			if (k == 0){
				zero_counter++;
				if (zero_counter == 2){
					System.out.println("total " + total);
					return;
				}
				total += subtotal;
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			} else {
				subtotal += k;
				zero_counter = 0;
			}
		}
	}
}
