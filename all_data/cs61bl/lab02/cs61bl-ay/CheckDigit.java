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
        int lastDigit = 0;
        int firstDigits = 0;
        lastDigit = id % 10;
        firstDigits = id / 10;
        
        int sum = 0;
        
        while(firstDigits >= 10){
        	sum += firstDigits %10;
        	firstDigits = firstDigits /10;
        
        }
        
        sum += firstDigits;
        
        if(sum % 10 == lastDigit){
        	isLegal = true;
        	
        }else{
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
