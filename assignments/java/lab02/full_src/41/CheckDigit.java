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
        // TODO Your code here
        int x = id;
        int legalcheck = x % 10;
        x = x / 10;
        int total = 0;
        while (x != 0) {
        	total = total + (x % 10);
        	x = x / 10;	
        }
        if ((total % 10) == legalcheck) {
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
