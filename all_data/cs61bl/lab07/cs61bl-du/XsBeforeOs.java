public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	lastXpos++;
        		if(k > lastXpos) {
        			char tmp = values[k];
        			values[k] = values [lastXpos];
        			values[lastXpos] = tmp;
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
    	int lastXpos = -1;
    	
        for(int i = 0; i < k; i++) {
        	if(values[i] == 'X') {
        		lastXpos++;
        		if(i > lastXpos)
        			throw new IllegalStateException();
        	}
        }

    }
}
