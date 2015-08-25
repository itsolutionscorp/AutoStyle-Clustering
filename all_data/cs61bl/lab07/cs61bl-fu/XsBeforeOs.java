public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        boolean sorted = false;
        for (int k = 0; !sorted && k < values.length; k++) {
            if (values[k] == 'O') {
            	for (int l = k+1; l < values.length; l ++) {
            		if (values[l] == 'X') {
            			values[k] = 'X';
            			values[l] = 'O';
            			break;
            		}
            		if (l == values.length - 1) {
            			sorted = true;
            		}
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
    	for (int i = 0; i < k; i += 1) {
    		if (values[i] == 'O' && values[i+1] == 'X') throw new IllegalStateException("Found a 'O' preceding and 'X'!");
    	}

    }
}
