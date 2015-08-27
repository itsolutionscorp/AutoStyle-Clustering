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
        int last_digit = id % 10;
        int sumNum = 0;
        int newId = id / 10;
        while (newId != 0) {
        	
        	sumNum = sumNum + (newId % 10);
        	newId = newId / 10;
        }
        if ((sumNum % 10) != last_digit){
        	isLegal = false;
        }
        
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
