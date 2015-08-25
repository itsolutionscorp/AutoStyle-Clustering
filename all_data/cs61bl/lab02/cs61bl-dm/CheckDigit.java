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
        
        int numberCheck = id % 10;
        int numberRest = id / 10;
        int newInt = 0;
        int numberSum = 0;
        while (numberRest > 0) {
        	newInt = numberRest % 10;
        	numberRest = numberRest / 10;
        	numberSum = numberSum + newInt;
        }
        if (numberCheck == numberSum % 10){
        	isLegal = true;
        }
        else {
        	isLegal = false;
        }
        
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
