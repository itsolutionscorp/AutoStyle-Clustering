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
        int lastdigit;
        lastdigit = id % 10;
        int newid = id / 10; 
        int sumdigits = 0;
        while (newid > 0) {
        	sumdigits = sumdigits + (newid % 10);
        	newid = newid / 10;
        }
        if ((sumdigits % 10) != lastdigit) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
