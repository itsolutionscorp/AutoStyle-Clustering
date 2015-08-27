	import java.util.*;
	
	public class AddingMachine {
	
		public static void main (String[] args) {
			Scanner scanner = new Scanner(System.in);
			int k=0;
			boolean justStarting = true;
			int total = 0;
			int subtotal = 0;
			while (true) {
				if(k==0){
			   k=scanner.nextInt();}
			
	
			   if (k == 0) {
				   total = total+subtotal;
				   System.out.println("subtotal" + subtotal);
				   subtotal=0;
			   }
			   else {
				   subtotal = subtotal + k;
				   k=0;
			   }
			   
			   if(subtotal==0){
				   k=scanner.nextInt();
				   if(k==0){
				   System.out.println("total" + total);
				break;   
				}
			   }
				
			}
		}
	
	}