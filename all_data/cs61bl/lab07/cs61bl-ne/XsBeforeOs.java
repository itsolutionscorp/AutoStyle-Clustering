public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        //int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	for (int j = k+1; j < values.length; j++){
            		if(values[j]=='O'){
            			char temp = values[k];
            			values[k] = values[j];
            			values[j]=temp;
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
    	for (int i =0; i< k; i++){
    	if(values[i]!='X'){
    		for (int j = i+1; j<k; j++){
    			if (values[j]=='X'){
    	    		throw new IllegalStateException ("Xs are not in front");
    			}
    		}

	    	}
    	}

    }
}
