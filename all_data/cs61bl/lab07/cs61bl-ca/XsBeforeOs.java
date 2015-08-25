public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	if (k != lastXpos + 1) {
            		values[lastXpos + 1] = 'X';
            		values[k] = 'O';
            		lastXpos = lastXpos + 1;
            	}
            	lastXpos = k;
            	

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
    	int count = 0;
        for(int t = 0; t <= k; t++) {
        	if (count > 0 && values[t] == 'X') {
        		throw new IllegalStateException("X's do not precede O's");
        	}
        	if (values[t] == 'O') {
        		count++;
        	}
        	
        }

    }

}
