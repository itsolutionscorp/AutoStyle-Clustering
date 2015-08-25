public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        // TODO Your code here
        
        int current = id/10;
        int sum = current%10;
        while (current > 10) {
        	current = current/10;
        	sum = sum + (current % 10);
        }
        if (sum%10 == id%10) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
