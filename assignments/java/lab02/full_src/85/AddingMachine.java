import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int last = 1;
		while (true) {
		    int i = scanner.nextInt();
		    if(i==0){
		    	if(last==0){
		    		System.out.println("total " + total);
		    		return;
		    	}
		    	System.out.println("subtotal " + subtotal);
		    	subtotal = 0;
		    }
		    total += i;
		    subtotal += i;
		    last = i;
		}
	}

}
