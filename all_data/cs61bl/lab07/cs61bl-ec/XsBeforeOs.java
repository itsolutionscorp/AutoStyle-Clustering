public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	if (k - lastXpos == 1) {
            		// don't swap
            		lastXpos++; 
            	}
            	else {
                	values[lastXpos+1] = 'X'; 
                	values[k] = 'O'; 
                	lastXpos++; 
            	}
            }
            else {
            	// do nothing because it is O
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
    	for (int i = 0; i < k - 1; i++) {
    		if (values[i + 1] == 'X' && values[i] == 'O') {
    			throw new IllegalStateException(); 
    		}
    	}
        // YOUR CODE HERE

    }
}
