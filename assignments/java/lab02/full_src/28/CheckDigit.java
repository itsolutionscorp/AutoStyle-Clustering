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
        int number = id;
        int sum = 0, lastDigit;
        lastDigit = number%10;
        number = number/10;
        while (number > 0)
        {
        	sum = sum + number%10;
        	number = number/10;
        }
        sum = sum%10;
        if (sum == lastDigit)
        	isLegal = true;
        else
        	isLegal = false;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
