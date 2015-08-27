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
        int newId = id;
        id = id / 10;
        int sum = 0;
        while (id > 0) {
        	 sum += id % 10;
        	 id = id / 10;
        	 
        	 
        }
        	isLegal = sum % 10 == lastDigit;
     
        
        if (isLegal) {
            System.out.println (newId + " is legal");
        } else {
            System.out.println (newId + " is not legal");
        }
    }

}
