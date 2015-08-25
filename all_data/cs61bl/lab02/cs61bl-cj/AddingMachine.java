import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k;
		k = scanner.nextInt();
		while (true) {
			
			while (justStarting) {
				if (k!=0){
				    subtotal = subtotal + k;
				    k = scanner.nextInt();
				} else {
					System.out.println("subtotal " + subtotal);
					justStarting = false;
				}
			}
			total = total +subtotal;
			subtotal = 0;
			k = scanner.nextInt();
			if (k!=0) {
				justStarting = true;	
			} else {
				System.out.println("total " + total);
				break;
			}
		}
	}

}
