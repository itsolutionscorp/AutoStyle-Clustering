public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            
        	if (values[k] == 'X') {
        		lastXpos++;
            	for (int i = k; i>lastXpos; i--){
                	values[i] = values[i-1];
                }
            	values[lastXpos] = 'X';
            	
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
        int count = 0;
        // YOUR CODE HERE
        for (int i = 0; i<=k-1; i++) {
        	if (values[i]=='X')
        	{
        		count++;
        	}
        
        }
        for (int index = 0; index <=count-1; index++){
        	if (values[index]!='X'){
        		throw new IllegalStateException("Xs precede Os");
        	}
        }
    }
}
