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

        int sumOfDigits = 0, tempId = id % 10;
        while (tempId > 0){
        	sumOfDigits += tempId % 10;
        	tempId /= 10;
        }
        
        isLegal = sumOfDigits % 10 == id % 10;
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
