import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    int input = scanner.nextInt();
		    if (justStarting == false){
		    	if (input == 0){
		    		System.out.println("total: " + total);
		    		return ; 	
		    	}else{
		    		subtotal += input;
		    		total += input;
		    		justStarting = true;
		    	}
		    }else{
		    	if (input == 0){
		    		System.out.println(subtotal);
		    		subtotal = 0;
		    		justStarting = false;
		    	}else{
		    		subtotal += input;
		    		total += input;
		    	}
		    	
		    }	
		    
	
		    
		}
	}

}
