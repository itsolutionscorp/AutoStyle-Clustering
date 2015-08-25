package lab02;

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
        int x = 0;
        int y = id;
        while (y/10 !=0) {
        		x = x + (y/10)%10;
        		y = y/10;
    	}

        if ((id % 10) != (x % 10)) {
        isLegal = false;
        }
        			
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}

