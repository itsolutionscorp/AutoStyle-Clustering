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
        int lastDigit = id % 10;
        int sumDigit = 0;
        int temp = id;
        while(temp != 0){
        	sumDigit += temp % 10;
        	temp /= 10;
        }
        sumDigit -= lastDigit;
        if (sumDigit % 10 == lastDigit){
        	isLegal = true;
        }
        else{
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
