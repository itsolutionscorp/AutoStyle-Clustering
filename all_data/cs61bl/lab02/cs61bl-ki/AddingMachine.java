import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int second = 0;
		while (true) {
		    int first = scanner.nextInt();
		    if (first == 0 && justStarting == false && second == 0) {
		    	System.out.println (total);
		    	return;
		    }
		    else if (first == 0) {
		    	System.out.println(subtotal);
		    	subtotal = 0;
		    }
		    else if (first != 0) {
		    	subtotal = subtotal + first;
		    	total = total + first;
		    }
		    second = first;
		    justStarting = false;
		    
		}
	}

}
