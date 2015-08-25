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
        int rest = id/10;
        int total = 0;
        while (rest%10 != 0){
        	total = total + rest%10;
        	rest = rest/10;
        }
        total = total%10;
        if (total != lastDigit){
        	isLegal = false;
        }
        /*
        System.out.print(total);
        System.out.print(lastDigit);
        */
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
  
    }

}
