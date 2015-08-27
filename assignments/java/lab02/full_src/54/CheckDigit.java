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
        
        int id_first = id / 10;
        int id_last = id % 10;
        int sum = 0;
        
        while (id_first > 0) {
        	sum = sum + id_first % 10;
        	id_first = id_first / 10;
        }
        
        if (sum % 10 != id_last) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}
