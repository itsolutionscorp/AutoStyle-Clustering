import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		//boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int tracker = 0;
		while (true) {
		    int value;
		    value = scanner.nextInt();
		    if (value == 0) {
		    	total += subtotal;
			    if (tracker == 1) {
			    	System.out.println(total);
			    	break;
			    }
		    	System.out.println(subtotal);
		    	subtotal = 0;
		    	tracker ++;
		    } else { 
		    	subtotal += value;
		    	tracker = 0;
		    }
		}
		scanner.close();
	}

}
