public class IntSequence {

    // instance variables
    private int[] values;   // sequence elements
    private int count;       // number of array cells used by sequence

    // constructor
    // capacity: actual count of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        values = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (count == values.length)
            throw new IndexOutOfBoundsException("Sequence is full!");

        values[count++] = toBeAdded;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. count < values.length
    // Also, insertPos is between 0 and count, inclusive.
    public void insert(int toInsert, int index) {
        if (index < 0 || index >= values.length || index > count)
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);

        for (int i = values.length - 1; i > index; i--)
            values[i] = values[i - 1];
        values[index] = toInsert;
        count++;
    }

    /**
     * Removes the element at index from the sequence
     * @param index the index of the element to remove
     @throws IndexOutOfBoundsException if the index is outside of the bounds of the sequence (>= count or < 0)
     */
    public void remove(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException("index is out of bounds: " + index);

        for (int i = index; i < count - 1; i++)
            values[i] = values[i + 1];
        count--;
    }

    /**
     * Checks whether the list is empty
     * @return true if the list is empty, false if it is not
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Gets the number of elements in the sequence
     * @return the size of the sequence
     */
    public int size() {
        return count;
    }

    /**
     * Checks whether this sequence contains the specified value.
     * @param val the value to check for the existence of
     * @return true if the sequence contains val, false if it does not
     */
    public boolean contains(int val) {
        for (int i = 0; i < count; i++) {
            if (values[i] == val)
                return true;
        }
        return false;
    }

    /**
     * Gets the element at the specified index from this sequence.
     * @param index the index
     * @return the element at index
     * @throws IndexOutOfBoundsException if the index is outside of the bounds of the sequence (>= count or < 0)
     */
    public int elementAt(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException("index is out of bounds: " + index);

        return values[index];
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "(Empty IntSequence)";

        StringBuilder builder = new StringBuilder();
        builder.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            builder.append(" ");
            builder.append(values[i]);
        }
        return builder.toString();
    }
}

