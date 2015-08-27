import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int x, i = 0;
		while (true) {
		    x = scanner.nextInt();
		    if (x == 0){
		    	if(i > 0){
		    		System.out.println("total " + total);
		    		return;
		    	}
		    	else{
		    		System.out.println("subtotal " + subtotal);
		    	}
		    	i += 1;
		    	subtotal = 0;
		    }
		    else{
		    	i = 0;
		    	subtotal = subtotal + x;
		    	total = total + x;
		    }   
		}
	}
}
