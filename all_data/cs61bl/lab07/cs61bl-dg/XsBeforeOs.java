public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int numOfXs = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	numOfXs++;
            }
        }
        for (int k = 0; k < values.length; k++) {
        	if (k < numOfXs) {
        		values[k] = 'X';
        	} else {
        		values[k] = 'O';
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
    	for (int i = 0; i<k; i++) {
    		if (values[i] == 'X') {
    			continue;
    		}
    		if (values[i] == 'O') {
    			if (values[i+1] == 'X') {
    				throw new IllegalStateException("No O's can be followed by an X.");
    			}
    		}
    	}
    }
}
