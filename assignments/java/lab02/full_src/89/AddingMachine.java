import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
			int user = scanner.nextInt();
			if (user != 0){
				subtotal += user;
				total+=subtotal;
			}
			else if (subtotal == 0 && !justStarting){
				System.out.println("total " + total);
				return;
			}
			else{
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			}
			justStarting = false;
		}
	}
}