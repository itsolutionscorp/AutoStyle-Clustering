public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	if (lastXpos != -1 ) {
            		char temp = values[k];
            		values[k] = values[lastXpos+1];
            		values[lastXpos+1] = temp;
            		lastXpos++;
            	} else {
            		lastXpos = k;
            	}

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
    	boolean foundO = false;
    	for (int i = 0; i < k; i++) {
    		if (foundO == true) {
    			if (values[i] == 'X') {
    				throw new IllegalStateException("It is not consistent!");
    			}
    		}
    		if (values[i] == 'O') {
    			foundO = true;
			}
      	}

    }
}
