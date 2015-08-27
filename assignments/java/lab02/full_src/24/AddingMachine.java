import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k = 0;
		int temp = 0;
		k = scanner.nextInt();
		while (true) {
		    temp = k;
		    if (k !=0 ) {
		    	subtotal = subtotal + k;// TODO Your code here
		    	
		    } else {
		    	System.out.println("subtotal " + subtotal);		 
		    	total = total + subtotal;
		    	subtotal = 0;
		    
		    }
		    k = scanner.nextInt();
		    if (temp == 0 && k == 0) {
		    	System.out.println("total " + total);
		    	return;
		    }
		    
		}
	}

}
