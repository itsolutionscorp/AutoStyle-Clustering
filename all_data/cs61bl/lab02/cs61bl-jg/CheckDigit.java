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
        int sum = 0;
        int lastdigit = id%10;
        int remaining = id;
        int remainder = remaining%10;
        
        // TODO Your code here
        while(remaining/10>0){
        	
        	
        	remaining = remaining/10;
        	remainder = remaining%10;
        	sum = sum + remainder;
        	
        }
        System.out.println(sum);
        if ((sum)%10 != lastdigit){
        	isLegal = false;
        }
       
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
