import java.util.*;

public class AddingMachine {

    public static void main (String [ ] args) {
        Scanner scanner = new Scanner (System.in);
        boolean justStarting = true;
        
        int total = 0;
        int subtotal = 0;
        int k;

        int ZeroCount = 0;
        
        
        while (true) {
        	// System.out.println(k);
            k = scanner.nextInt();
            if (k != 0) {
            	subtotal = subtotal + k;
            	ZeroCount = 0;
            
            } else {
            	System.out.println(subtotal);
            	total = total + subtotal;
            	subtotal = 0;
            	ZeroCount++;
            } 
            
            if ( ZeroCount == 2) {
            	System.out.print(total);
            	return;
            }
            
        }
           
    }
}
        
        
        
        
        
        
        
        
        
        
        
//        int i = Integer.parseInt(args[0]);
//        System.out.println(i);
//        int RemainderDigits = k;
//        int ZeroCount = 0;
  //     System.out.println(k);
/*        while (RemainderDigits > 0) {
        	System.out.println(RemainderDigits);
            if (RemainderDigits % 10 != 0) {
            	subtotal = subtotal + RemainderDigits % 10;
            	System.out.println(subtotal);
            	RemainderDigits = RemainderDigits / 10;
            	ZeroCount = 0;
            } else {
            	System.out.println(subtotal);
            	total = total + subtotal;
            	subtotal = 0;
            	ZeroCount++;
            	if (ZeroCount == 2) {
            		System.out.print(total);
            		return;
*/
       
       
       
       
//            	}
            	
//            }
//        }
   // }
//}


//100111101101101