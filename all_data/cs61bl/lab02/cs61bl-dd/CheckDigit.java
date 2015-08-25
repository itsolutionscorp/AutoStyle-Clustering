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
        int last = id % 10;
        int rightmost = 0;
        int sum = 0;
        int remainder = id;
        while (remainder > last) {
        	rightmost = remainder % 10;
        	sum = rightmost + sum;
        	remainder = Math.floorDiv(remainder, 10);
        }
        if (sum % 10 != remainder) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
