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
        int last = id%10;
        System.out.println("last digit is " + last);
        
        int tempid = (id - last)/10;
        int total = 0;
        int templast = 0;
        while (tempid!=0) {
        	templast = tempid%10;
        	tempid = (tempid - templast)/10;
        	total = total + templast;
        }
        isLegal = total%10 == last;
        System.out.println("total of all digits preceding last is " + total);
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
