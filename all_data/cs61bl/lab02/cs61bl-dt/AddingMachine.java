import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    // TODO Your code here
			int input = scanner.nextInt();
			if (input == 0 && justStarting){
				System.out.println("subtotal "+ subtotal);
				subtotal = 0;
				justStarting = false;
			}else if (input == 0 && !justStarting){
				System.out.println("total " + total);
				break;
			}else{
				justStarting = true;
				total += input;
				subtotal += input;
			}

		}
		scanner.close();
	}

}