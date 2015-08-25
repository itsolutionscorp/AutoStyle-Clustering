public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	continue;
            } else {
            	for (int j = k+1; j<values.length-1; j++) {
            		values[j] = values[j+1];
            	}
            	values[values.length-1] = 'O';
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
    	for (int i = 0; i<k+1; i++) {
    		if (values[i]=='X') {
    			continue;
    		} else {
    			for (int j = i; j<k+1; j++) {
    				if (values[j] == 'X') {
    					throw new IllegalStateException("Xs do not precede Os!");
    				} else {
    					continue;
    				}
    			}
    		}
    	}

    }
}
