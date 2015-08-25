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
        // your missing code goes here
        int lastDigit = id%10;
        int nextNumber = id/10;
        int nextDigit;
        int total = 0;
        while (nextNumber!=0) {       	
        	nextDigit = nextNumber%10; 
        	total = total + nextDigit;
        	nextNumber = nextNumber/10;
        }
        
        if (total%10 == lastDigit) {
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
