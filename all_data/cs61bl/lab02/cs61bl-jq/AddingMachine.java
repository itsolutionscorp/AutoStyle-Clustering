import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		boolean zero = false;
		while (true) {
			int k;
			k = scanner.nextInt();
				if(k==0){
					if(zero){
						System.out.println(total);
						zero = false;
					}else{
						System.out.println(subtotal);
						subtotal = 0;
						zero = true;
					}
				}else{
					zero = false;
					subtotal += k;
					total += k;
				}
			}
		}
	}


