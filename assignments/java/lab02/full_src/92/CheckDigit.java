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
        int idClone = id;
        int sumDig = 0;
        int lastDig = id%10;
        idClone = idClone/10;
        while (idClone!=0){
        	sumDig = sumDig + idClone%10;
            idClone = idClone/10;
        }
        if ((sumDig%10) != lastDig){
        	isLegal=false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
