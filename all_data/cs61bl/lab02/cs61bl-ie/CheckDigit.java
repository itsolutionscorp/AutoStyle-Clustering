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
        int lastDigit = id % 10;
        int allButLast = id / 10;
        int sum = 0;
        while (allButLast > 0) {
        	sum = sum + (allButLast % 10);
        	allButLast = allButLast / 10;
        }
        if (id < 10) {
        	isLegal = true;
        } else if (lastDigit != sum % 10) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
