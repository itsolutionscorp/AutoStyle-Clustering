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
        int checkDigit = id % 10;
        int sum = 0;
        int tempId = id/10;
        while (tempId > 0) {
        		sum += tempId % 10;
        		tempId = tempId/10;
        }
        isLegal = (sum % 10 == checkDigit);
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
