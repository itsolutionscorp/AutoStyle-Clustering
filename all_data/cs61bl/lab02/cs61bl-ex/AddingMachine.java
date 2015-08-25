import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		boolean t = false;
		while (true) {
			int s = scanner.nextInt();
			subtotal += s;
	    	if (s == 0 && t){
	    		System.out.println("The total is " + total);
	    		return;
	    	}
		    if (s == 0){
		    	System.out.println("The subtotal is " + subtotal);
		    	total += subtotal;
		    	subtotal = 0;
		    }
		    if (s == 0)
		    	t = true;
		    else t = false;
		}
	}

}
