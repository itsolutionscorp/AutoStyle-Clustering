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
        int last_digit = id % 10;
        int factor = id / 10;
        int sum = 0;
        		while (factor > 0) {
        			sum = sum + factor % 10;
        			factor = factor / 10;
        			}
                int la = sum % 10;
        if (la != last_digit) {
            isLegal = false;
        }         
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
