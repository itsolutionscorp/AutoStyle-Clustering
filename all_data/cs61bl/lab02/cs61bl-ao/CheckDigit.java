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
        int lastDig =0;
        int singleDig = 0;
        int sumDig = 0;
        int b = id;
        
        if (id>0) {
        lastDig = id%10;
        
        	while (b > 1){         
        		int a = b/10;
        		singleDig = a%10;
        		sumDig = singleDig+sumDig;
        		b = a;
        	}
        
        	if(lastDig != sumDig%10){
        	isLegal = false;
        	}
        	

        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
