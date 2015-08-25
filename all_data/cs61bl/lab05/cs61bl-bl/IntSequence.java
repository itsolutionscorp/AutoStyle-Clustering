public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (myCount - 1 == myValues.length) {
            System.err.println("Sequence is full.");
            System.exit(1);
        }
        myValues[myCount] = toBeAdded;
        myCount += 1;
    }

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
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k -= 1) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // Returns true when the sequence is empty, else false.
    public boolean isEmpty() {
        if (myCount == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of values in this sequence
    public int size() {
        return myCount;
    }

    // Returns the value at the given position in the sequence
    public int elementAt(int pos) {
        if (pos > myValues.length) {
            System.err.println("Index out of bounds.");
            System.exit(1);
        }
        return myValues[pos];
    }

    public boolean contains(int k) {
        for (int n = 0; n < myCount; n += 1) {
            if (myValues[n] == k) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder elems = new StringBuilder();
        for (int k = 0; k < myValues.length - 1; k += 1) {
            elems.append(myValues[k] + " ");
        }
        elems.append(myValues[myValues.length - 1]);

        return elems.toString();
    }
}

