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
        int sum = 0;
        int firstnums = id / 10;
        int lastnum = id % 10;
        while (firstnums / 10 != 0) {
        	sum = sum + (firstnums % 10);
        	firstnums = firstnums / 10;
        }
        sum = sum + firstnums;
        if (sum % 10 != lastnum) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}
