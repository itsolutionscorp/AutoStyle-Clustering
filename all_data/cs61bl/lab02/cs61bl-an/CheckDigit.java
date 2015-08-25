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
        int rightMost;
        int lastDigit;
        int sumDigit;
        int checkId;
        rightMost=id%10;
        checkId=(int)id/10;
        sumDigit=0;
        while(checkId>0){
        	sumDigit=sumDigit+checkId%10;
        	checkId=(int)checkId/10;
        }
        lastDigit=sumDigit%10;
        isLegal=rightMost==lastDigit;
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
