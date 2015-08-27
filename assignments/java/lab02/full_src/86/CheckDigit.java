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
        /*
         *  In a legal ID number, 
         *  the rightmost digit is the last digit of the sum of 
         *  all the other digits in the number. 
         */
        int idCopy = id / 10;
        int lastDigit = id % 10;
        int sumDigit = 0;
        while (idCopy > 0){
        	sumDigit += idCopy % 10;
        	idCopy = idCopy / 10;
        }
        int lastSumDigit = sumDigit % 10;
        if (lastSumDigit != lastDigit)
        	isLegal = false;
     
  
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
