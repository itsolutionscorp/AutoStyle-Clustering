public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	char exchange = values[lastXpos+1];
            	values[lastXpos+1]='X';
            	values[k] = exchange;
                lastXpos += 1;

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
    		if (values[i] == 'X' && values[i+1] == 'O') {
    			for (int j=i+1; j<k; j++) {
    				if (values[j] == 'X') {
    					throw new IllegalStateException();
    				}
    			}
    		}
    	}
    }
}
