public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	lastXpos++;
            	if (k != lastXpos) {
            		char c = values[lastXpos];
            		values[lastXpos] = values[k];
            		values[k] = c;
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
    	for (int i = 0; i <= k - 1; i++) {
			if (values[i] == 'O' && values[i + 1] == 'X') {
				throw new IllegalStateException("An 'O' proceeds an 'X'");
			}
    	}
    }
}
