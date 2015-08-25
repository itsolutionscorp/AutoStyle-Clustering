public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os. 
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int j = 0; j < values.length; j++) {
            if (values[j] == 'X') {
            	lastXpos++; }; 
        }
        for (int j = 0; j < values.length; j++) {
        	values[j] = 'X'; 
        }
        for (int j = lastXpos+1; j < values.length; j++) {
        	values[j] = 'O'; 
        }
    // YOUR CODE HERE
        int k = 0; 
            try {
                isOK(values, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
    }
        
    

    // Check for consistency. All the Xs in elements 0 to k of values should
    // precede all the Os. Throw an IllegalStateException if this is not
    // consistent.
    public static void isOK (char[] values, int k) {

        // YOUR CODE HERE
    	for (int x =0; x < k; x++) {
    		if (values[x] == 'O') {
    			if (values[x+1] == 'X') {
    			throw new IllegalStateException();
    		}
    		}
    	}
    }
}
    
