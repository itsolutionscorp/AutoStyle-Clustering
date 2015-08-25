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
        int changeid = id; 
        int last = changeid % 10;
        changeid = changeid / 10;
        int total = 0;
        while (changeid > 0) {
        	total = total + (changeid % 10);
        	changeid = changeid / 10;
        }
        if (last != total % 10) {
        	isLegal = false; 
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}

