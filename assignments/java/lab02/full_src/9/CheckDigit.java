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
        // your missing code goes here
        int check = id % 10;
        int sumId = 0;
        int id2 = id / 10;
        while (id2 > 9) { // for case more than 2 digits
        sumId = sumId + (id2 % 10);
        id2 = id2 / 10;
        }
        sumId = sumId + id2; // add remaining number
        if (sumId % 10 != check) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}