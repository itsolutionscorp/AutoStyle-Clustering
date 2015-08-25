import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int input;
		System.out.println("Enter inputs: ");
		while (true) {
		   input = scanner.nextInt();
		   if (input == 0){
			   justStarting = false;
			   subtotal = total + subtotal;
			   subtotal = 0;
			   
		   }
		   subtotal = input + subtotal;
		   total = input + total;
		   System.out.println ("subtotal: " + subtotal);
		   System.out.println ("total: " + total);
		}
	}

}
