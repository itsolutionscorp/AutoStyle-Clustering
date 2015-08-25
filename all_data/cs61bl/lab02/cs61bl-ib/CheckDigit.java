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
        int next_digit = id;
        int sum = 0;
        while (next_digit > 10){
        	sum += next_digit%10;
        	next_digit /= 10;
        }
        sum = sum + next_digit - id%10;
        int last_digit = id % 10;
        isLegal = (sum%10 == last_digit);
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
