
public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }
        boolean isLegal = true;
        
        int subtotal = 0;
        int subtotalLastDigit = 0;
        int RemainderDigits = id / 10;
        int FinalLastDigit = id % 10;
        
        	while(RemainderDigits > 0){
        		subtotal = subtotal + (RemainderDigits % 10);
        		RemainderDigits = RemainderDigits / 10;
        	}
        	subtotalLastDigit = subtotal % 10;
        if(subtotalLastDigit == FinalLastDigit) {
        	isLegal = true;
        } else {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}
