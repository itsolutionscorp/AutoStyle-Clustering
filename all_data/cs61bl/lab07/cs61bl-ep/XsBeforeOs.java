public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
                lastXpos++;
                if (lastXpos != k) {
                	values[lastXpos] = 'X';
                	values[k] = 'O';
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
    	
        int numX = 0;
        int numO = 0;
        for (int m = 0; m <= k; m++) {
        	if (values[m] == 'X') {
        		numX++;
        		if (numO > 0) {
        			throw new IllegalStateException("All Xs in elements 0 to "+k+" of values should precede all Os!");
        		}
        	} 
        	if (values[m] == 'O') {
        		numO++;
        	}
        }
        
    }
}
