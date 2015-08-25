public class XsBeforeOs {

	// Rearrange the elements of values so that all the Xs precede all
	// the Os.
	public static void rearrange(char[] values) {
		int lastXpos = -1;
		for (int k = 0; k < values.length; k++) {
			if (values[k] == 'X') {
				lastXpos++;
				// YOUR CODE HERE
				if (k != lastXpos) {
					char temp;
					temp = values[lastXpos];
					values[lastXpos] = values[k];
					values[k] = temp;
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
		int lastXpos = -1;
		// YOUR CODE HERE
		for (int i = 0; i < k; i++) {
			if (values[i] == 'X') {
				lastXpos++;
				if (i != lastXpos) {
					throw new IllegalStateException("all X has to be before O");
				}
			}

		}

	}
}
