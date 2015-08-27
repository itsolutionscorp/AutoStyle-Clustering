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
        int x = id % 10;
        int newid = id;
        int sum = 0;
        while (newid > 0) {
        	newid = newid/10;
        	sum = sum + newid % 10;}
        if (sum % 10 != x) {
        	isLegal = false;
 
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
