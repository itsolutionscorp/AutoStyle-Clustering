public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
                // YOUR CODE HERE
            	//push this value as x and 
            	values[k] = 'O';
            	//check which one is the first O, change the first O to x
            try {
                isOK(values, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            } 
            values[lastXpos+1] = 'x';
            }//
        }
    }

    // Check for consistency. All the Xs in elements 0 to k of values should
    // precede all the Os. Throw an IllegalStateException if this is not
    // consistent.
    public static void isOK (char[] values, int k) {
        // YOUR CODE HERE
    	int i = 0;
    	while (i <= k){
    		if(values[i]=='O'){
    			i++;
    		}else{
    			
    		}
    	}
    	
    }
}
