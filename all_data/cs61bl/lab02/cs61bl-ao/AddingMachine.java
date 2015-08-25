import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
		    // TODO Your code here
		
			while(true) {
				int input = scanner.nextInt();
				subtotal = input + subtotal;
				if (input == 0)
				{
					System.out.println(subtotal);
					break;					
				}				
			}
			
			total = subtotal + total;
			subtotal = 0;
			int input2 = scanner.nextInt();
			if (input2 == 0){
				System.out.println(total);
				return;
				}
			
		}
	}
}
		
		
		
		
	

