public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	values[lastXpos+1] = values[k];
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
    	for (int c = 0; c<k-1; c++){
    		if (values[c] == 'O' && values[c+1] == 'X') {
    			throw new IllegalStateException("out of order X");
    		}
    	}
//    	for (int i = k; i < values.length; i++){
//    		if (values[i] != 'O') {
//    			throw new IllegalStateException("From k to the end has a non O");
//    		}
//    	}

    }
}
