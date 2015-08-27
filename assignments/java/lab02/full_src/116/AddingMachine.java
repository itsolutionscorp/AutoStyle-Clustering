import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		boolean prev_is_zero = false;
		int k;
		while (true) {
		    k = scanner.nextInt();
		    subtotal += k;
		    total += k;
		    if (k == 0 && prev_is_zero == true) {
		    	System.out.println ("total " + total);
		    	return;
		    }
		    if (k == 0) {
		    	System.out.println ("subtotal " + subtotal);
		    	subtotal = 0;
		    	prev_is_zero = true;
		    } else {
		    	prev_is_zero = false;
		    }
		    }
		}
	}