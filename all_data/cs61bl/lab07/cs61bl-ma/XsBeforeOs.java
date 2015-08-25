public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;	
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	values[lastXpos+1] = 'X';
            	if (lastXpos < k - 1) {
            		values[k] = 'O';
            	}
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
    	for (int i = 0; i < k-1; i++) {
    		char current = values[i]; 
    		char next =  values[i+1];
    		if ((current == 'O') && (next == 'X')) {
    			throw new IllegalStateException("Not all X's in the first k elements precede all the O's");
    		}
    	}

    }
}
