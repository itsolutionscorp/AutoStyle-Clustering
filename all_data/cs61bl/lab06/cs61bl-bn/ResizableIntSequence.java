public class ResizableIntSequence extends IntSequence {

	public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public void add(int toBeAdded) {
		if (myCount < myValues.length) {
			myValues[myCount] = toBeAdded;
			myCount++;
		} else {
			int[] previous = myValues;
			myValues = new int[myValues.length + 1];
			for (int i = 0; i < previous.length; i++) {
				myValues[i] = previous[i];
			}
			myCount++;
			myValues[myCount - 1] = toBeAdded;

		}

	}

	public void insert(int[] values, int pos, int newInt) {
		if (pos < 0) {
			return;
		} else if (pos >= values.length) {
			int[] previous = myValues;
			myValues = new int[pos + 1];
			for (int i = 0; i < previous.length; i++) {
				myValues[i] = previous[i];
			}
			myValues[pos] = newInt;
			myCount++;

		} else {
			int[] ref = new int[values.length];
			for (int j = 0; j < values.length; j++) {
				ref[j] = values[j];
			}

			for (int k = 0; k < values.length; k++) {
				if (k > pos) {
					values[k] = ref[k - 1];
				} else if (k == pos) {
					values[k] = newInt;
				} else {
					values[k] = values[k];
				}
			}
		}
	}

	public void remove(int[] values) {
		myCount = myCount / 2;
		int[] previous = myValues;
		myValues = new int[myCount];
		for (int k = 0; k < myCount; k++) {
			myValues[k] = previous[k];
		}

	}

	public int[] getNewValues() {
		return myValues;
	}
}
