import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int lastint = -1;
		while (justStarting == true) {
		    // TODO Your code here
			int k;
			k = scanner.nextInt();
			subtotal += k;
			
			if (k == 0) {
				if(k == lastint) { 
					System.out.println(total);
					justStarting = false;
				}
				else {
				total = total + subtotal;
				System.out.println(subtotal);
				subtotal = 0;
				}
			}
			lastint = k;

		}
	}

}
