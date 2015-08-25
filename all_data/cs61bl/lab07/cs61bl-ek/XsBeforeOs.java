public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	if(k == 0){
            		continue;
            	}if (values[k] != 0){
            		if(values[k] == 'X'){
            			continue;
            		}else{
            			char c = values[k];
            			
            			
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

        // YOUR CODE HERE
    	for (int c = 0; c < values.length; c ++){
    		for (int c2 = 0; c2 < k; c2 ++ ){
    			if(values[c2] != 'X'){
    				throw new  IllegalStateException("Not all X's are first");
    			}
    		}for(int c3= k + 1; c3< values.length; c3 ++){
    			if(values[c3] == 'X'){
    				throw new  IllegalStateException("Not all X's precede O's");
    			}
    		}
    	}

    }
}
