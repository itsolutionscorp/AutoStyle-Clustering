public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        int lastOpos = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
                // YOUR CODE HERE
            	if (k > lastOpos) {
            		values[lastOpos] = 'X';
            		values[k] = 'O';
            	} else {
            		lastXpos = k;
            		lastOpos++;
            	}
            }
            if (values[k] == 'O') {
            	lastOpos = k;
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
    	boolean is_o = false;
    	for (int i = 0; i < k; i++) {
    		if (values[i] == 'O') {
    			is_o = true;
    		}
    		if (is_o && values[i] == 'X') {
    			throw new IllegalStateException("There is an O before a X.");
    		}
    	}

    }
}
