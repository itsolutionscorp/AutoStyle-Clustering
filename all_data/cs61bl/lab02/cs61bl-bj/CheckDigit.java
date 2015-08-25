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
        int left, right,sum;
        right = id % 10;
        left = (id - right)/10;
        sum = 0;
        while (left > 0){
        	sum = sum + left % 10;
        	left = left /10;
        }
        if (sum%10 == right){
        	isLegal = true;
        }else{
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
