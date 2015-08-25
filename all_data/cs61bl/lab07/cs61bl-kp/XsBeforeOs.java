public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE

            	values[k] = 'O';
            	values[lastXpos+1] = 'X';
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
    	boolean hitO = false;
    	for (int n = 0; n < k; n++) {
    		if (values[n] == 'O') {
    			hitO = true;
    		} else if (hitO && values[n] == 'X') {
    			throw new IllegalStateException("O no!");
    		}
    	}
    }
}
