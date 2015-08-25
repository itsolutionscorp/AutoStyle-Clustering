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
        int X, temp, lastdigit;
        X=0;
        temp = id;
        while (temp > 0) {
        	X += (temp % 10);
        	temp /= 10;
        }
        lastdigit = id % 10;
        X -= lastdigit;

        if (X % 10 != lastdigit) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
