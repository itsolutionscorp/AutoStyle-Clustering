public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	char firstO = values[lastXpos + 1];
            	values[lastXpos + 1] = 'X';
            	values[k] = firstO;
            	lastXpos += 1;
                // YOUR CODE HERE

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
    	char previous = values[0];
    	for (int i = 1; i <= k; i++) {
    		if (previous == 'O' && values[i] == 'X') {
    			throw new IllegalStateException("all Xs must come before Os");
    		}
    		previous = values[i];
    	}
        // YOUR CODE HERE

    }
}
