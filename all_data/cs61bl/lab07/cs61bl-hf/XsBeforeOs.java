public class XsBeforeOs {

	// Rearrange the elements of values so that all the Xs precede all
	// the Os.
	public static void rearrange(char[] values) {
		int lastXpos = -1;
		for (int k = 0; k < values.length; k++) {
			if (values[k] == 'X') {
				lastXpos++;
				if (lastXpos!=k){
				char a= values[lastXpos];
				values[lastXpos]= values[k];
				 for (int j=k;j < values.length-2;j++){
					 values[j]= values[j+1];
					 
				}
				 values[values.length-1]=a;
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
	public static void isOK(char[] values, int k) {
		char first = values[0];
		if (first == 'o') {
			for (int i = 1; i < k; i++) {
				if (values[i] == 'X') {
					throw new IllegalStateException(
							"Xs and Os are not ordered case(1)");
				}
			}

		}else{
			for (int i = 1,countX=1; i < k; i++) {
				if (values[i] == 'X' && countX==i) {
					countX++;
				}else if (values[i] == 'X') {
					throw new IllegalStateException(
							"Xs and Os are not ordered case(1)");
				}
			}
		}

	}
	public static void main(String[] args){
		
	        char[] arr = {'X', 'X', 'O', 'X', 'O'};
	        XsBeforeOs.rearrange(arr);
	}
}
