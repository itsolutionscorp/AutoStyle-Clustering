public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

            	values[k] = 'O';
            	values[lastXpos + 1] = 'X';

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
    	
        for (int counter = 0, seenZero = 0; counter <= k; counter ++) {
        	if (values[counter] == 'X') {
        		if (seenZero > 0) {
        			throw new IllegalStateException("There is an O before an X");
        		} 
        	} else {
        		seenZero++;
        	}
        }

    }
}
