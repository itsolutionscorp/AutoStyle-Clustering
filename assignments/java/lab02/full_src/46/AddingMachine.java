import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int nextNum, prevNum = 1; 
		while (true) {
			nextNum = scanner.nextInt();
			if (nextNum != 0){
				total += nextNum;
				subtotal += nextNum;
			} else if (nextNum == 0){
				if (prevNum == 0){
					System.out.println("total " + total);
					return;
				}
				System.out.println("subtotal " + subtotal);
				subtotal = 0;
			}
			prevNum = nextNum;
		}
	}

}
