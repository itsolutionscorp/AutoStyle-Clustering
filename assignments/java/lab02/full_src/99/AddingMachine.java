import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
			int input = scanner.nextInt();
			if (input != 0) {
				justStarting = true;
			}
		    total = total + input;
		    subtotal = subtotal + input;
		    if (input == 0) {
		    	if (justStarting == false) {
		    		System.out.println("total "+ total);
		    		return;
		    	}
		    	System.out.println("subtotal " + subtotal);
		    	subtotal = 0;
		    	justStarting = false;
		    }
		}
	}

}