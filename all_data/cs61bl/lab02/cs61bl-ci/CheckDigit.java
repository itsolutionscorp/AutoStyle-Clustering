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
        int digit_sum = 0; // record the sum of the digit
        int last_digit = id % 10; // the last digit to be checked
        int id_remain = (id - last_digit) / 10; // the remain id that should be added
        while (id_remain > 0 ) {
        	int this_digit = id_remain % 10;
        	digit_sum += this_digit;
        	id_remain = (id_remain - this_digit) / 10;
        }
        isLegal = (digit_sum % 10 == last_digit);
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
