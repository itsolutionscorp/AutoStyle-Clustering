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
        int sum = 0;
        int lastIdDigit = id%10;
        int idCopy = id;
        while (idCopy > 0){
        	sum += (idCopy % 10);
        	idCopy /= 10;
        	
        }
        
        if (lastIdDigit != (sum - lastIdDigit) % 10){
        	isLegal = false;
        }
       
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
