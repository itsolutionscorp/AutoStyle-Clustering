import java.util.*;

public class AddingMachine {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int z_tracker = 0;
		while (true) {

			int k;
			k = scanner.nextInt();

			if (k == 0) {

				z_tracker = z_tracker + 1;
				
				if (z_tracker == 2) {
					System.out.println(total);
					return;
				}

				total = total + k;
				System.out.println(subtotal);
				subtotal = 0;
			} else {
				subtotal = subtotal + k;
				total = total + k;
				z_tracker = 0;
			}
		}
	}

}
