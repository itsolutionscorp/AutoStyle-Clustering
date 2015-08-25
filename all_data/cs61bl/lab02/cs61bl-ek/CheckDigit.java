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
        int key = id % 10;
        int next = 0;
        int total = 0;
        int newId = id / 10;
       
        while (newId > 0){
        	next = newId % 10;
//        	System.out.println ( next );
        	newId = newId / 10;
        	total = total + next;
//        	System.out.println ( total );
        	
        	}
//        System.out.println ( newId );
//        System.out.println ( next );
//        System.out.println ( total );
//        
        
        if (total % 10 != key){
        	isLegal = false;
        	}
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
