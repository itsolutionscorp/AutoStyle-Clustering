import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int zeroCount = 0;
		while (true) {
			System.out.println ("Type:");
			int input = scanner.nextInt();
				subtotal += input;
				if (zeroCount == 1 && input == 0){
				System.out.println ("total " + total);
				break;
				}
				else if (input == 0){
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				zeroCount += 1;
				subtotal = 0;
			}
				else{
				zeroCount = 0;
			}
		}
		return;
	}

}
