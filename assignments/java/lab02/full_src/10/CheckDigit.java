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
        int Allbutlast = id / 10;
        int Last_digit = id % 10;
        int Sum = 0;
        while (Allbutlast != 0) {
        	int digit = Allbutlast % 10;
        	Sum = Sum + digit;
        	Allbutlast = Allbutlast / 10;
        }
        if (Sum % 10 != Last_digit) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
