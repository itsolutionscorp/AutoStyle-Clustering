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
        int first_digits = id /= 10;
        int total = 0;
        while (first_digits > 0){
        	total += first_digits % 10;
        	first_digits /= 10;
        }
        isLegal = last_digit == total % 10;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
