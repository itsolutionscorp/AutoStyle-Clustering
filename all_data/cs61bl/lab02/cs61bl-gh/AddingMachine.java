import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		boolean previouszero = false;
		while (true) {
		    // TODO Your code here
			int nextInt = scanner.nextInt();
			subtotal += nextInt;
			total += nextInt;
			if (nextInt == 0){
				if (previouszero == true){
					System.out.println("total " + total);
					return;}
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
				previouszero = true;}
			else {
				previouszero = false;
			}
			
			
				
			}
		}
	}


