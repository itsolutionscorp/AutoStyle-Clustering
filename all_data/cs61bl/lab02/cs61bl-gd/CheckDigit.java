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
        int LastDigit = id % 10;
        int SumOfDigits = id / 10;
        int NewID = 0;
        
        while (SumOfDigits > 0) {
        	NewID = SumOfDigits % 10 + NewID;
        	SumOfDigits = SumOfDigits / 10;
        }
        int LastID = 0; 
        
        LastID = NewID % 10;
        
        if(LastID != LastDigit){
        	isLegal = false;
        }
        		
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
