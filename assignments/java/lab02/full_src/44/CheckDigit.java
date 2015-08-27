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
        // We assume that the id is greater than one digit number.
        int total = 0;
        int last = id % 10;
        int number = id / 10;
        while (number > 0) {
        	total = total + (number % 10);
        	number = number / 10;
        }
        isLegal = (total % 10)==last;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
