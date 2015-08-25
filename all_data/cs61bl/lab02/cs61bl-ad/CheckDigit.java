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
        int remainder = id % 10;
        int subtotal = 0;
        int test_id = id/10;
        while (test_id >= 1) {
        	int new_remainder = test_id % 10;
        	subtotal += new_remainder;
        	test_id = test_id/10;
        }
        int check_remainder = subtotal % 10;
        if (remainder != check_remainder) {
        	isLegal = false;
        } else {
        	isLegal = true;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}