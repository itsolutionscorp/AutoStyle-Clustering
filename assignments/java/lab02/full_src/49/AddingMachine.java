import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int prev = 235;
		while (true) {
		    // TODO Your code here
			int k = scanner.nextInt();
			if (k == 0) { 
				if (prev == 0) {
					System.out.println("total " + total);
					return; 
				}
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			}
			prev = k;
			subtotal += k;
			total += k;
			
		}
	}

}
