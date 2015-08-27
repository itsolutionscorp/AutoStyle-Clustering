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
        int num = 0;
        int temp = id;
        int last = temp % 10;
        temp = temp / 10;
        while (temp > 0) {
            num = temp % 10;
            sum = sum + num;
            temp =  temp/ 10;
        }
        if ((sum % 10) == last) {
        	isLegal = true;
        } else {
        	isLegal = false;
        }
        // TODO Your code here
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
