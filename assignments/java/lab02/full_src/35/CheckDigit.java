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
        int finalDigit = id % 10; //id = 123456786, lastDigit = 6;
        int number = id/10;
        int sum = 0;
        int lastDigit = 0;
        while (number > 0) {     	
        	lastDigit = number%10; 
        	sum += lastDigit;     	
        	number = number/10;   	
        }
        if ((sum % 10) != finalDigit) {      	
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
