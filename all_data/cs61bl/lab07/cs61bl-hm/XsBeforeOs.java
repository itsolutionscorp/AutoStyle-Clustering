public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        boolean hit0 = false;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X' && hit0 == false) {
            	lastXpos = k;
            }
            else if (values[k] == 'O'){
            	hit0 = true;
            }
            else if (values[k] == 'X'){
            	values[lastXpos + 1] = 'X';
            	values[k] = 'O';
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
    	int last_x_index = k;
    	for (int i =0; i < k; i++) {
    		if (values[i] == 'O') {
	    		last_x_index = i;
	    		break;
    		}
    	}
    	for (int i = last_x_index; i < k; i++) {
    		if (values[i] == 'X')
    			throw new IllegalStateException("0's not before X's");
    	}

    }
}
