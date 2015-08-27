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
        int last_digit = id % 10;
        int sum_digits = 0;
        int temp_id = id;
        while (temp_id % 10 !=0){
        		temp_id = temp_id - temp_id%10;
        		temp_id = temp_id/10;
        		sum_digits += temp_id % 10;
        }
        if(last_digit != sum_digits %10){
        	isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
