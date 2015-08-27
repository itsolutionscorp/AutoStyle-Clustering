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
        int id1 = id;
        int other = id1 / 10;
        int veri = id1 % 10;
        int s = 0; //sum of digits
       
        while (other > 0) {
        	s = other % 10 + s;
        	other = other / 10;
        }

        isLegal = (s%10 == veri);
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
