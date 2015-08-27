import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		boolean beforeZero = false;
		int numZero = 0;

		while (true) {
		    // TODO Your code here
			int num = scanner.nextInt();
			if (num == 0) {
				numZero++;
				if (numZero == 2) {
					System.out.println("total " + total);
					return;
				}
				beforeZero = true;
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			} else {
				beforeZero = false;
			}
			
			if (!beforeZero) {
				numZero = 0;
			}
			subtotal += num;
			total += num;	
		}
	}
}
