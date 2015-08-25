public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	if(k==lastXpos+1)
                // YOUR CODE HERE
            	{lastXpos =k;}
            	else{
            		if(lastXpos<0){
            			lastXpos=0;
            			char out = values[0];
            			values[0]=values[k];
            			values[k]=out;
            		}else{
            			lastXpos++;
            			char out =values[lastXpos];
            			char x = values[k];
            			values[k]=out;
            			values[lastXpos]=x;
            			
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
    	int track =-1;
    	for(int i=0;i<=k;i++){
    		
    		if(values[i] == 'X'){
    			System.out.println(i);
    		if(i-track!=1){throw new IllegalStateException("inconsistent!!");}
    		else{track = i;}
    	}
    }
    	System.out.println("finished");

    }
}
