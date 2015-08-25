public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        int sum = 0;
        int last_digit = 0;
        int rest= 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = true;
        last_digit = id % 10;
        rest = id / 10;
        while (rest > 0) { 
        	sum += rest % 10;
        	rest = rest / 10;
        	
        }
        
        if (sum % 10 != last_digit) {
        isLegal = false; 
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
