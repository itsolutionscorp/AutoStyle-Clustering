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
        
        int lastdigit=id%10;
        int total=0;
        int idholder=id;
        idholder=(idholder-lastdigit)/10;

       
        
        while (idholder >= 10){
        	total = total + (idholder % 10);
        	idholder = (idholder-idholder%10)/10;
        }
        total=total+idholder;
        
        if (total%10!=lastdigit)
        	isLegal=false;
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
