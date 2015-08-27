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
        int placeholder = id;
        int lastdigit = id % 10;
        int sum = 0;
        placeholder = placeholder / 10;
        while (placeholder > 0) {
        	sum = sum + placeholder % 10;
        	placeholder = placeholder / 10;
        	
        }
        
        isLegal = lastdigit == sum % 10;
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
