public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
                lastXpos++;
                values[k]='O';
                for(int q=0;q<=lastXpos;q++){
                	values[q]='X';
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
    	boolean foundO=false;
    	for(int q = 0;q<=k;q++){
    		if(values[q]=='O'){
    			foundO=true;
    		}
    		if(foundO){
    			if(values[q]=='X')
    				throw new IllegalStateException("That's...not OK");
    		}
    	}
    }
}
