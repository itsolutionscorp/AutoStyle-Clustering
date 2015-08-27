public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
            //System.out.println(id);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = true;
        // TODO Your code here
        int sum = 0;
        int copy = id; 
        while (copy > 0){
        	copy = copy/10;
        	sum = copy%10 + sum; 
        }
        if (sum%10 != id%10){
        	isLegal = false; 
        }
       
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
