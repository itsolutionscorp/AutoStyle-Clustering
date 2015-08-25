public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	lastXpos += 1;
            	if (k > lastXpos) {
            		values[lastXpos + 1] = values[k];
            		lastXpos += 1;
            		for (int i = lastXpos + 1; i < k; i++) {
            			values[k] = 'O';
            		}
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
    	boolean containsO = false;
    	for (int i = 0; i < k; i++) {
    		if (values[i] == 'O') {
    			containsO = true;
    			for (int j = 0; j < i; j ++) {
    				if (values[j] != 'X') {
    					throw new IllegalStateException();
    				}
    			for (int d = i + 1; d < values.length; d ++) {
    				if (values[d] != 'O') {
    					throw new IllegalStateException();
    				}
    			}
    			}
    			break;
        		}
    		
    		}
    	
    	}

    }

