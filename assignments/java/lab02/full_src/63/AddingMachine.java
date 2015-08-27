import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		
		int total = 0;
		int subtotal = 0;
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		boolean lastzero = false;
		
		while (true) {
			int k = scanner.nextInt();
			if (k==0){
				if (lastzero){
					System.out.println("total"+total);
					return;
				} else {
					lastzero=true;
					total+=subtotal;
					System.out.println("subtotal"+subtotal);
					subtotal=0;
				}
			} else {
				lastzero=false;
				subtotal+=k;
						
			}
			
			
		}
		
	
	}

}
