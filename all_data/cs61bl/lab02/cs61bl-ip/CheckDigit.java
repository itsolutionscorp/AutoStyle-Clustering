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
        int check = id % 10;
        int sum = 0;
        int checkid = id / 10;
        while (checkid > 9) {
        	sum = sum + (checkid % 10);
        	checkid = checkid / 10;
        }
        sum = sum + checkid;
        if ((sum % 10) != check) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
