public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	char temp = values[lastXpos+1];
            	values[lastXpos+1] = values[k];
            	values[k] = temp;

            }
            try {
                isOK(values, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
        }
    }

    // Check for consistency. All the Xs in elements 0 to k of values should
    // precede all the Os. Throw an IllegalStateException if this is not
    // consistent.
    public static void isOK (char[] values, int k) {
    	boolean is_O = false; 
        for (int i = 0; i<k; i++){
        	if (values[i] == 'O'){
        		is_O = true;
        	}
        	if(values[i] == 'X' && is_O){
        		throw new IllegalStateException("all X's must be fore O's");
        	}
        }
    }
}
