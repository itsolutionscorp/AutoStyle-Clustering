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
        int workID = id;
        int sum = 0;
        int endDigit = workID % 10;
        workID = workID / 10;
        int sumLastDigit = endDigit;
        if (workID > 0) {
	        while (workID > 9) {
	        	int nextLast = workID % 10;
	        	sum += nextLast;
	        	workID = workID / 10;
	        }
	        sum += workID;
	        sumLastDigit = sum % 10;
        }
        if (sumLastDigit != endDigit) { 
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
