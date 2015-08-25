import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    // TODO Your code here
			int k = scanner.nextInt();
			subtotal = subtotal + k;
			if (k == 0) {
				System.out.println("subtotal " + subtotal);
				total = total + subtotal;
				subtotal = 0;
				if (scanner.nextInt()==0); {
					System.out.println("total " + total); 
					return; 
				}
			} 
		}
	}
}