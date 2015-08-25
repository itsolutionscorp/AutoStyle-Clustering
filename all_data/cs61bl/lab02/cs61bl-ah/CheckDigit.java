
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
        String id_str= String.valueOf(id);
        int len = id_str.length();
        int sum = 0;
        for(int i = 0; i < len-1; i++){
        	sum += Integer.parseInt(id_str.substring(i,i+1));
        }
        System.out.println(sum);
        int rem_sum = sum%10;
        int rem_id = id%10;
        if (rem_sum == rem_id){
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
