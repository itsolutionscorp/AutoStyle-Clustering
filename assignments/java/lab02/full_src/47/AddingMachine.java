import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int input;
		int lastinput = 1;
		while (true) {
			input = scanner.nextInt();
		    if (input == 0) {
		    	if (lastinput == 0) {
		    		System.out.println (total);
		    		return;
		    	} else {
		    	System.out.println (subtotal);
		    	subtotal = 0;
		    	lastinput = 0;
		    	}
		    } else {
		    	total = total + input;
		    	subtotal = subtotal + input;
		    	lastinput = 1;
		    }
		}
	}

}
