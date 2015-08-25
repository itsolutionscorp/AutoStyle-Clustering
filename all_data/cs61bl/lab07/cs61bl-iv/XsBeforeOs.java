public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	values[k]='O';
            	lastXpos++;
            	values[lastXpos]='X';            	
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

        // YOUR CODE HERE
    	for (int i=0; i<k; i++) {
    		if (values[i]!='X') {
    			for (int j=0; j<=k-i; j++) {
	    			if (values[j+i]!='O') {
	    				throw new IllegalStateException("ERROR: not consistent.");
	    			}
    			}
			}
    	}
    }
}
