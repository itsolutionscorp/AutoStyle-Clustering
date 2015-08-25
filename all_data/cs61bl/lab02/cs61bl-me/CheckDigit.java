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
        // TODO Your code here   123456786
        int sofar = 0;
        int current = 0;
        int check = id % 10;
        int num = 0;
        num = id / 10;
        while (num > 0){
        	current = num % 10;
        	num = num / 10;
        	sofar += current;
        }
        if (check != sofar%10){
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
