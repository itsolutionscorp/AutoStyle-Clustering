public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }
        //id = 123456786
        boolean isLegal = true;
        int idLast = id %10; //6
        int idRest = id / 10;// 12345678
        int sum = 0;
        while (idRest > 0){
        	sum += idRest%10; // 0+8
        	idRest= idRest/10;
        }
        if (sum %10 != idLast){
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}