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
        int summed = 0;
        int lastdigit = id % 10;
        int idcheck = Math.floorDiv(id, 10);
        while (idcheck != 0) {
        	summed = summed + (idcheck % 10);
        	idcheck = Math.floorDiv(idcheck, 10);
        }
        if (summed % 10 != lastdigit) {
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}

