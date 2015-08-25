import java.util.*;

public class AddingMachineLoops {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		//boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int k;
		k=scanner.nextInt();
		while (true) {
		    // TODO Your code here
			
			while(k!=0){
		    	subtotal=subtotal+k;
		        k=scanner.nextInt();
		    }
			
		    System.out.println(subtotal);
		    total=total+subtotal;
		    subtotal=0;
		    k=scanner.nextInt();
		    if (k==0){
			    System.out.println(total);
			    return;
		    }     
		}
	}

}
