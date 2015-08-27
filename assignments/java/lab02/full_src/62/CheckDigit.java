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
        int rest = id / 10;
        int sum = 0;
        while (rest > 0) {
        	sum = sum + (rest % 10);
        	rest = rest / 10;
        }
        isLegal = (sum % 10 == last);
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
