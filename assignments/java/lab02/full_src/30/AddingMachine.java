import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    int input;
		    input = scanner.nextInt();
		    if (input == 0) {
		    	if (subtotal == 0) {
		    		if (justStarting) {
		    			System.out.println("subtotal " + subtotal);
		    		} else {
		    			System.out.println("total " + total);
		    			return;
		    		}
		    	} else {
		    		System.out.println("subtotal " + subtotal);
		    		total = total + subtotal;
		    		subtotal = 0;
		    		justStarting = false;
		    	}
		    } else {
		    	subtotal += input;
		    	justStarting = true;
		    }
		}
	}

}
