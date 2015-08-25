public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	if (k-1==lastXpos){
            		lastXpos=k;
            	} else {
            		values[k]='O';
            		lastXpos++;
            		values[lastXpos]='X';
            	}
                // YOUR CODE HERE

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
    	for (int i=0; i< k; i++){
    		if (values[i]=='X' && i-1==lastXpos){
    			lastXpos=i;
    		} else if (values[i]=='X' && i-1!=lastXpos) {
    			throw new IllegalStateException("u messed up");
    		}
    		
    	}
        // YOUR CODE HERE

    }
}
