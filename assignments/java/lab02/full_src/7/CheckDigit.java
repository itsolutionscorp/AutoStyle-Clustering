

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
        int lastDigit = (id % 10);
        int SumOfDigits = 0;
        int id2 = (id/10);
        
        while (id2 > 0) {
        	SumOfDigits = SumOfDigits + (id2 % 10);
        	id2 = (id2/10);
        }
        isLegal = ((SumOfDigits % 10) == lastDigit);
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
