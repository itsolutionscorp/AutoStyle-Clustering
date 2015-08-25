import java.util.Arrays;

public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
                // YOUR CODE HERE
            	if(k - lastXpos > 1){
            		values[k] = 'O';
            		values[lastXpos + 1] = 'X';
            	}
            	lastXpos += 1;
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
    	int x = -999;
    	int y = 999;
    	for(int i = 0; i < k; i++){
    		if(values[i] == 'X' && i > x){
    			x = i;
    		}
    		else if(values[i] == 'O' && i < y){
    			y = i;
    		}
    	}
    	if(x > y){
    		throw new IllegalStateException();
    	}
    }
}
