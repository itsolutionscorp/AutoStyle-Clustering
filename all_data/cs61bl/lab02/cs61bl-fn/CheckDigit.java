

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
        int ones_digit;
        int ones_digit_id;
        int sum=0;
        int placeholder;
        int remainder;
        placeholder = id /10;
        while (placeholder > 0) {
        	remainder = placeholder % 10;
        	placeholder = placeholder / 10;
            sum = sum + remainder;            
        }
        ones_digit = sum % 10;
        ones_digit_id = id % 10;
        
        if (ones_digit != ones_digit_id) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
