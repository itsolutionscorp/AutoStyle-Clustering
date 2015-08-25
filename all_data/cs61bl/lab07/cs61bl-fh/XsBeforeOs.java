public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	if(lastXpos == (k - 1)) { //check if this is still on the first string of Xs
            		lastXpos++;
            	} else {                 //So there has been an O already
            		values[k] = 'O';
            		values[lastXpos + 1] = 'X';  //switch the places of the X and the thing which comes after the last X in the first string.
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
    	boolean hasBeenO = false;
    	for (int index = 0; index <= k; index++) {
    		if (hasBeenO) {
    			if (values[index] == 'X') {
    				throw new IllegalStateException("All Xs must come before all Os");
    			}
    		} else {
    			if (values[index] == 'O') {
    				hasBeenO = true;
    			}
    		}
    	}
    }
}
