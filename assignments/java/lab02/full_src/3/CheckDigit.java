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
        int right_most_id = id % 10;
        int total = 0;
        int id2 = id / 10;
        while (id2 > 0) {
        	total = total + id2 % 10;
        	id2 = id2 / 10;
        }
        System.out.println (total);
        if (right_most_id != total % 10) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}


