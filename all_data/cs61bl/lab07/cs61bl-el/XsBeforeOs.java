public class XsBeforeOs {

	// Rearrange the elements of values so that all the Xs precede all
	// the Os.
	public static void rearrange(char[] values) {
		int lastXpos = -1;
		boolean startOh = false;
		char temp;
		for (int k = 0; k < values.length; k++) {
			if (values[k] == 'X' && startOh == false) {

				// YOUR CODE HERE
				lastXpos++;
			}

			if (values[k] == 'O' && startOh == false) {
				startOh = true;
			}

			if(values[k] == 'X' && startOh == true){
				temp = values[lastXpos + 1];
				values[lastXpos + 1] = 'X';
				values[k] = temp;
				lastXpos ++;
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

		boolean startOh = false;
		// YOUR CODE HERE
		if (values.length < k) {
			throw new IllegalStateException("length cannot be less than k");
		}

		for (int i = 0; i <= k; i++) {
			if (values[i] == 'O') {
				startOh = true;
			}

			if (startOh == true && values[i] == 'X') {
				throw new IllegalStateException("no X's after the O's");
			}
		}

	}
}
