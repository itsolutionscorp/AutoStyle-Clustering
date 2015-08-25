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
        int sumdigits = 0;
        int last = id % 10;
        int store = id;
        store = id / 10;        		
        while (store > 0) {
        	sumdigits  = store % 10 + sumdigits;
        	store = store / 10;
        }
        isLegal = sumdigits % 10 == last;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
