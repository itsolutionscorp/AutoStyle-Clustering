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
        int id2 = id;
        int lastDigit = id%10;
        int sum = 0;
        while(id2>0){
        	id2 = id2/10;
        	sum += id2%10;
        }
        if(lastDigit != (sum%10))
        	isLegal = false;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
