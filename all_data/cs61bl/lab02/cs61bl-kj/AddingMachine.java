import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt();
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int prevalue = 1;
		while (true) {
		    // TODO Your code here
			k = scanner.nextInt();
			if (k == 0){
				total = subtotal + total;
				if (prevalue == k){
					System.out.print("total " + total);
					return;
				}
				else
				{
					System.out.println("subtotal " + subtotal);
				}
				subtotal = 0;
			}
			subtotal = subtotal + k;
			prevalue = k;
		}
	}
}
