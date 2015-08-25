public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = false;
        // TODO Your code here
        int s = id / 10;
        int total = s % 10;
        while (s != 0) {
        	s = s / 10;
        	total += s % 10;        		
        }
        if ( id % 10 == total % 10) {
        	isLegal = true;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
