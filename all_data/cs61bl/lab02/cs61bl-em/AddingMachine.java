import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		
		int k, temp = 1;
	
		while (true) {
			k = scanner.nextInt();
			subtotal += k;
			
			if (temp == 0 && k == 0 && !justStarting) {
				System.out.println("     total: " + total);
				return;
			}
			if (k == 0) {
				System.out.print("    subtotal: " + subtotal);
				System.out.println();
				total += subtotal;
				subtotal = 0;
			}
			justStarting = false;
			temp = k;
			
		}
	}

}
