public class ResizableIntSequence extends IntSequence {
	int[] newValues;

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	@Override
	public void add(int toBeAdded) {
        if (myCount == myValues.length) {
            newValues = new int[myValues.length*2];
            for (int k = 0; k < myCount; k += 1) {
            	newValues[k] = myValues[k];
            }
            myValues = newValues;
        }
        myValues[myCount] = toBeAdded;
        myCount += 1;
    }

	@Override
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {
			newValues = new int[myValues.length*2];
            for (int k = 0; k < myCount; k += 1) {
            	newValues[k] = myValues[k];
            }
            myValues = newValues;
		}

        for (int k = myCount; k > insertPos; k -= 1) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    @Override
    public void remove(int pos) {
        if (pos < 0 || pos >= myCount) {
            System.err.println("Negative position or doesn't exist.");
            System.exit(1);
        }
        for (int k = 0; k < myCount; k += 1) {
            if (k >= pos) {
                myValues[k] = myValues[k + 1];
            }
        }
        myCount -= 1;

        // Halve myValues.
        if (myCount <= myValues.length / 2) {
        	newValues = new int[myValues.length/2];
            for (int k = 0; k < myCount; k += 1) {
            	newValues[k] = myValues[k];
            }
            myValues = newValues;
        }
    }
}