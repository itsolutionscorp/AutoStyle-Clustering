import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
			int k = scanner.nextInt();
		    while (k != 0) {
		    	justStarting = true;
		    	total = total + k;
		    	subtotal = subtotal + k;
		    	k = scanner.nextInt();
		    }
		    if (justStarting == false) {
		    	System.out.println("total " + total);
		    	return;
		    }
		    justStarting = false;
		    System.out.println("subtotal " + subtotal);
		    subtotal = 0;
		}
	}

}
