import java.util.*;

public class AddingMachine {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k = 0;
		while (true) {
	
			subtotal = subtotal + k;
			k = scanner.nextInt();
			if (k == 0) {
				System.out.println("Subtotal: " + subtotal);
				total = total + subtotal;
				subtotal = 0;
				k = scanner.nextInt();
			} if (k == 0 && subtotal == 0) {
				System.out.println("Total: " + total);
				return;
			}
			
				
		}
	
	}

}
