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
        int sum = 0;
        int idholder = id;
        while (idholder > 1) {
        	sum = sum + idholder % 10;
        	idholder = idholder / 10;
        }
        if (sum % 10 != idholder) {
        	isLegal = false;
        }
        if (id < 10) {
        	isLegal = true;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
