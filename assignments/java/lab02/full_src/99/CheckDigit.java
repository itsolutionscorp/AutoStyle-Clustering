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
        int new_id = id/10;
        int sum = 0;
        while (new_id > 0) {
        	sum = sum + (new_id%10);
        	new_id = new_id / 10;
        }
        
        if (last_digit != (sum%10)) {
        	isLegal = false;
        }
        
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
