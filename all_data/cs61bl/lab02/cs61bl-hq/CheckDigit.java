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
        int newId = id;
        
        while (newId >= 10) {
        	int digit = newId % 10;
        	newId = newId / 10;
        	sum = sum + digit;
        }
        
        if (sum % 10 == newId % 10) {
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
