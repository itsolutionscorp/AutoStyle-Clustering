import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int tmp;
		int count_zero = 0;
		String sub = "subtotal";
	    String tol = "total";
	    while (true) {
		    // TODO Your code here
			tmp = scanner.nextInt();
			if(tmp != 0){
			  count_zero = 0;
			  subtotal += tmp;
			}
			else{
				
				total += subtotal;
				count_zero += 1;
				if (count_zero == 1){
					System.out.println(sub +" "+subtotal);
					subtotal = 0;
				}
				if (count_zero == 2){
					//justStarting = false;
					System.out.println(tol +" "+ total);
					return;
				}
				
			}
			}
			
		}
	}

