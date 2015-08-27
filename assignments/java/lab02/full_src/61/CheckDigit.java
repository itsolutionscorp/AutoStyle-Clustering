public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        int sum_id = 0;
        int last_sum_id;
        int last_id,remainder,tmp_id;

        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }
        tmp_id = id;
        boolean isLegal = true;
        // TODO Your code here
        last_id = tmp_id%10;
        tmp_id = tmp_id/10;
        while(tmp_id != 0){
          remainder = tmp_id%10;
          tmp_id = tmp_id/10;
          sum_id = sum_id + remainder;
        }
        last_sum_id = sum_id%10;
        if(last_sum_id != last_id){
        	isLegal = false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
