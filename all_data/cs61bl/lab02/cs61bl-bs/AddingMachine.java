import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int prev_k = 5;
		while (true) {
			justStarting = false;
			int k;
			k = scanner.nextInt();
			subtotal += k;
			total += k;
			
			if (k == 0){
				if (prev_k == 0 && !justStarting){
					System.out.println("total "+total);
					return ;}
				System.out.println("subtotal "+subtotal);
				subtotal = 0;}
			prev_k = k;
				
		}
	}

}
