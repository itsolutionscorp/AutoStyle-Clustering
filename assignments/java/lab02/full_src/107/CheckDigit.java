public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }
        int copy = id;
        boolean isLegal = true;
        int check = copy % 10;
        copy/=10;
        int sum =0;
        while(copy>0){
        	sum+=copy%10;
        	copy/=10;
        }
        if(sum%10!=check)
        	isLegal = false;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
