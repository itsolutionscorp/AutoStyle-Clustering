public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        int encounter = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {

                // YOUR CODE HERE
            	lastXpos = k;
            	encounter = 1;
            }
            if(encounter == 1){
            if (values[k] == 'O') {
            	for(int j = k; j < values.length; ++j) {
   				 if(values[j] == 'X'){
   					values[lastXpos + 1] = values[j];
   	            	values[j] = 'O';
   				 }
   			 }
                // YOUR CODE HERE
            	
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
    	int encounter = 0;
    	 for (int i = 0; i < k; i++) {
    		 if(encounter == 1){
    		 if (values[i] == 'O') {
    			 for(int j = i; j < k; ++j) {
    				 if(values[j] == 'X'){
    					 throw new IllegalStateException("Invalid State");
    				 }
    			 }
    		 }
    		 }
             if (values[i] == 'X') {
            	 encounter = 1;
                 // YOUR CODE HERE 
             }
             
    }
}
}
