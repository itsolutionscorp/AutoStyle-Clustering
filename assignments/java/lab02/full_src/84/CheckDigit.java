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
        
        int sum = 0;
        int lastSum = 0;
        int lastDigit = (id % 10);
        int minusLast = id / 10;
        
        while(minusLast > 0) {
        	sum += (minusLast % 10);
        	minusLast = minusLast / 10;
        }
        
        lastSum = (sum % 10);
        
        if (lastSum != lastDigit) {
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
