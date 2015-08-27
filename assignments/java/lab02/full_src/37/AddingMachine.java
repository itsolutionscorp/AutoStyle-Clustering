import java.util.*;

public class AddingMachine {

    public static void main (String [ ] args) {
        Scanner scanner = new Scanner (System.in);
        boolean justStarting = true;
        int total = 0;
        int subtotal = 0;
        while (true) {
            // TODO Your code here
        	int curr = scanner.nextInt();
        	if (curr == 0) {
        		
        		if (!justStarting) {
        			System.out.println(total);
        			return;
        		}
        		justStarting = false;
        		System.out.println(subtotal);
        		subtotal = 0;
        	}
        	if (curr != 0) {
        		justStarting = true;
        	}
        	subtotal = subtotal + curr;
        	total = total + curr;
        }
    }
}
