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
        int sum = 0; 
        int remainder = id;
        while (remainder>=10){
        	
        	 remainder = remainder /10;  
        	 sum = sum + remainder%10;
        }
        
        
        if (id%10!=sum%10) {
        	isLegal =false; 
        	 }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
