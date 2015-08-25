import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true; // it is false
		int total = 0;
		int subtotal = 0;
		while (true) {
		    int k = scanner.nextInt();
		    total += k;
		    subtotal += k;
		    if (k == 0){
		    	if (justStarting){// it is the first time that appears '0'
		    		System.out.println("subtotal " + subtotal);
			    	subtotal = 0;
		    	} else {// it is the second time that appears '0'
		    		System.out.println("total " + total);
		    		return;
		    	}
		    	justStarting = false;
		    } else {// the input is not '0'
		    	justStarting = true;
		    }
		}
	}

}
