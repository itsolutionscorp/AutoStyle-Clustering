import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		
		while (justStarting) {
		    // TODO Your code here
			
			int k;
			k = scanner.nextInt();
			
			if (k == 0){
				total += subtotal;
				System.out.print (subtotal);
				subtotal = 0;
				
				k = scanner.nextInt();
				if (k == 0){
				
				System.out.print (total);
				justStarting = false;
				scanner.close();
				}
				else {
					subtotal += k;
				}
				
			}
			
			else{
				subtotal += k;
			
			}
		
		}
	}

}
