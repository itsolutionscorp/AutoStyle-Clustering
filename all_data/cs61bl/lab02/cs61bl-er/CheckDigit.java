

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
        int last_digit = id % 10;
        int sum = 0;
        int leftover = id / 10;
        while (leftover >= 10) {
        	sum = sum + (leftover % 10);
        	leftover = leftover / 10;
        	}
        sum = sum + leftover;
        isLegal = (sum % 10)==last_digit;

        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
