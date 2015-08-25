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
        int total = 0;
        int seq = (int) id / 10;
        while (seq != 0) {
        	total += seq % 10;
        	seq = (int) seq / 10;
        	
        }
        int rightmost = id%10;
        int lasttotal = total%10;
        
        if (rightmost != lasttotal) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }   
    }
}
