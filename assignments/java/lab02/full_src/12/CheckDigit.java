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
        int id_temp = id;
        int check = id_temp % 10;
        int sum = 0;
        
        do {
        	id_temp = id_temp / 10;
        	sum += id_temp % 10;
        	
        } while (id_temp > 10);
        
        if (sum % 10 != check) {
        	isLegal = false;
        }
        // 
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
