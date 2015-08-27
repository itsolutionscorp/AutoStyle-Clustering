public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        int lastdigit,sum=0,m=10,remainder;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }
        boolean isLegal = true;
        // your missing code goes here
        lastdigit=id%10;
    m=id;
        while (m>0)
 {m= m /10;
 remainder=m%10;
 sum=sum+remainder;
 }     
        if(sum%10==lastdigit) isLegal = true;
        else isLegal = false;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}