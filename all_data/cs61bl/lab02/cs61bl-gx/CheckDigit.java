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
        int leftSide = id / 10;
        int rightSide = id % 10;
        int sumDigits = 0;
        
        while (leftSide != 0) {
        	sumDigits = leftSide % 10 + sumDigits;
        	leftSide = leftSide / 10;
        }
        
        if ((sumDigits % 10) != rightSide) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
