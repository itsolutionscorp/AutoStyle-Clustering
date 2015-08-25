public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	lastXpos++;
            	
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
    	    	    		
    	int lastXpos = 0;
    	boolean Ofound = false;
    	for (int j = 0; j <= k; j++) {
    		if (values[j] == 'X') {
    			lastXpos = values[j];
    		}
    		if (values[j] == 'O') {
    			Ofound = true;
    		}
    		if (Ofound == true && values[j] == 'X') {
    			throw new IllegalStateException("Not Consistent");
    		}
    	}
    	
    	
    	
//    	for (int j = firstOIndex; j < k+1; j++) {
//    		if (values[j] == 'X') {
//    			throw new IllegalStateException("Not Consistent");
//    		}
//    	}
    }
}
