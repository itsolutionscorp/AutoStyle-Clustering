import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    int k;
		    k = scanner.nextInt(); 
		    if (k == 0) {
			System.out.println("subtotal "+ subtotal); 
			subtotal = 0;
			k = scanner.nextInt();
			if (k == 0) {
			    System.out.println("total "+ total); 
			    return;
			} else {
			    subtotal = subtotal + k;
			    total = total + k;
			}
		    } else {
			subtotal = subtotal + k;
			total = total + k;
			}
		}
	}

}