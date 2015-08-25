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
        int temp = id;
        int sum = 0;
        int digit = id%10;
        while (temp > 10) {
        	temp = temp/10;
        	sum = sum + temp%10;
        }
        if (sum%10 != digit) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
