import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true; //justStarting is false whenever 0 was last entered
		int total = 0;
		int subtotal = 0;
		while (true) {
			int k;
			k = scanner.nextInt();
			subtotal += k;
			total += k;
			if (k == 0 && justStarting == true) {
				justStarting = false;
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			}
			else if (k == 0 && justStarting == false) {
				System.out.println("total " + total);
				return;
			}
			else //if any other k was entered
				justStarting = true;
		}
	}

}