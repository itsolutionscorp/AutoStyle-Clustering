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
        int copyid = id;
        copyid = copyid / 10; // truncate last digit 
        while (copyid > 0) {
        	sum = sum + copyid % 10; 
        	copyid = copyid / 10; 
        }
        
        // check if the rightmost digit is the last digit of the 
        // sum of all the other digits in the number
        isLegal = sum % 10 == id % 10; 
        	
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
