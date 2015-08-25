public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	lastXpos = k;
            } else {
            	for (int i = k; i < values.length - 1; i++) {
            		values[i] = values [i + 1];
            	}
            	values[values.length - 1] = 'O';
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
    	boolean x = false;
    	for (int i = 0; i < k; i++) {
    		if (x && values[i] == 'X')
				throw new IllegalStateException("O occurs before X");
    		if (values[i] == 'O')
    			x = true;

    	}

    }
}
