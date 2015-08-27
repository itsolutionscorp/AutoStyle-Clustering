import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int newValue = 0;
		
		while (true) {
			System.out.println ("Enter new Value");
		    newValue = scanner.nextInt();
		    
		    if (justStarting) {
		    	if (newValue == 0) {
		    		if (total == 0) {
		    			System.out.println("subtotal " + subtotal);
	    			} else {		    			
	    				System.out.println ("total " + total);
	    				return;
	    			}
		    	} else {
		    		justStarting = false;
		    		subtotal += newValue;
		    		total += newValue;
		    	}
		    } else {
		    	if (newValue == 0) {
		    		justStarting = true;
		    		System.out.println ("subtotal " + subtotal);
		    		subtotal = 0;
		    	} else {
		    		subtotal += newValue;
		    		total += newValue;
		    	}
		    }
		}
	}

}
