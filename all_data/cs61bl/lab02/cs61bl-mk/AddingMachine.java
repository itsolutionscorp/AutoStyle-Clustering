import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    int nextNum = scanner.nextInt();
		    if (nextNum == 0) {
		    	if (justStarting == false){
		    		System.out.println(total);
		    	} else {
		    	System.out.println(subtotal);
		    	subtotal = 0;
		    	justStarting = false;
		    	}
		    } else {
		    	justStarting = true;
		    }
		    subtotal += nextNum;
		    total += nextNum;
		}
	}

}
