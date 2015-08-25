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
        // id = 123456786
        int x, y, z = 0;
        y = id % 10; // y = 6
        x = id / 10; // x = 12345678
        while(x > 0){
        	z = x % 10 + z; // 8 + 0, 7 + 8, 6 + 15
        	x = x / 10; // 1234567, 123456, 12345,
        }
        if (z % 10 != y){
        	isLegal = false;
        }
        
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
