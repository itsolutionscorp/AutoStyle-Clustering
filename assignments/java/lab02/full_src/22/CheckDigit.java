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
        int temp = id; 
        int total = 0; //total of the first i-1 digits
        int last = temp % 10; //last digit of id
        while (temp != 0) {
        	temp = temp / 10;
        	total += temp % 10;
        }
        isLegal = (total == last);
        // TODO Your code here
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
