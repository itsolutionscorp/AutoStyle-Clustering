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
        int lastNum = id % 10;
        int temp = id/10;
        int sum = 0;
        while (temp > 0)
        {
        	sum = sum + temp % 10;
        	temp = temp/10;
        }
        
        if ((sum % 10) != lastNum)
        {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
