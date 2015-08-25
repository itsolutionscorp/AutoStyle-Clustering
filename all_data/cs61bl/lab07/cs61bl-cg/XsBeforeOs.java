public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	values[k] = values[lastXpos+1];
            	values[lastXpos+1] = 'X';
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
    	for (int i = 1; i < k - 1; i++){
    		if (values[i] == 'O' && values[i +1] == 'X'){
    			throw new IllegalStateException("inconsistency at position " + i);	
    		}
    	}

    }
}
