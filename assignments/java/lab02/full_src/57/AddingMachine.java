import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		while (true) {
			
		    // TODO Your code here
			int k;
			k = scanner.nextInt();
			if (justStarting==true){
				
				if(k!=0){
					subtotal=subtotal+k;
				}
				else{
					total=total+subtotal;
					System.out.println("subtotal = "+ subtotal);
					subtotal=0;
					justStarting=false;
				}
			}
			else{
				if(k!=0){
					justStarting=true;
					subtotal=subtotal+k;
				}
				else{
					System.out.println("total = "+ total);
					return;
				}
			}
	
		}
	}
}
