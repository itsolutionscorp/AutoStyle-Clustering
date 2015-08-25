public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length - 2; k++) {
            if (values[k] == 'X') {
            	lastXpos++;
            	if (values[k + 1] == 'O' && values[k + 2] == 'X') {
            		values[lastXpos + 1] = 'X';
            		values[lastXpos + 2] = 'O';
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
    	for (int i = 0; i < k; i++) {
    		if (values[i] == 'O' && values[i + 1] == 'X') {
    			throw new IllegalStateException("Inconsistent array");
    		}
    	}

    }
}
