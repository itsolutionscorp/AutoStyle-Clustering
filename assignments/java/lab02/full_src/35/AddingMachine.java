import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k = 0;
		while (true) {
		    k = scanner.nextInt();
		    total += k;
		    subtotal += k; 
		    if (k == 0) {
		    	System.out.println("subtotal " + subtotal);
		    	subtotal = 0;
		    	if (justStarting == false) {
		    		System.out.println("total " + total);
		    		return;
		    	}
		    	justStarting = false;
		    }
		    else {
		    	justStarting = true;
		    }		    	    
		}
	}
}
