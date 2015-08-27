import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k;
		int pre = 1;
		while (true) {
		    // TODO Your code 
			k = scanner.nextInt();
			if (k == 0){
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
				if(pre == 0){
					System.out.println("total " + total);
					return;
				}
			}
			pre = k;
			subtotal += k;
		}
	}

}
