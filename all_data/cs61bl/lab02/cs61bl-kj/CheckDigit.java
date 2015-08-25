public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }
        boolean isLegal;
        int sum = 0;
        int id2 = id/10;
        while (id2 > 0){
        	sum = sum + (id2%10);
        	id2 = id2/10;
        }
        if (id%10 == sum%10){
        	isLegal = true;
        }
        else{
        	isLegal = false;
        }
        // TODO Your code here
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
