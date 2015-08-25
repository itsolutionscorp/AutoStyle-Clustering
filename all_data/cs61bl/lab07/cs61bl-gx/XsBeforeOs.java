public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        char temp;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	lastXpos++;
            	temp = values[k];
            	values[k] = values[lastXpos];
            	values[lastXpos] = temp;
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
    	boolean set = false;
    	
    	for (int i = 0; i <= k; i++) {
    		if (values[i] == 'O' && set == false) {
    			set = true;
    		} else if (values[i] == 'X' && set) {
    			throw new IllegalStateException("X is after!");
    		}
    	}
    }
}
