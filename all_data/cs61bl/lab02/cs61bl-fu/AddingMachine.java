import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
			int nextnumber;
			nextnumber = scanner.nextInt();
			if (nextnumber == 0) {
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
				nextnumber = scanner.nextInt();
				if (nextnumber == 0) {
					System.out.println("total " + total);
					return;
				}
			}
			subtotal += nextnumber;
		}
	}

}
