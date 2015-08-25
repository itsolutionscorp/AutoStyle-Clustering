public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	lastXpos++;
            	if(lastXpos != k) {
            		values[k] = 'O';
            		values[lastXpos] = 'X';
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
    	int place = 0; 
    	boolean lastChance = false;
    	while(place <= k) {
    		if(values[place] == 'X'){
    			if(lastChance){
    				throw new IllegalStateException("Illegal State Exception!");
    			}
    		}
    		else if(values[place] == 'O'){
    			lastChance = true;
    		}	
    		place++;
    	}
    }
}
