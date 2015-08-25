public class ResizableIntSequence extends IntSequence {
    public ResizableIntSequence(int capacity) {
        super(capacity);
    }

    public ResizableIntSequence() {
        super(10);
    }

    public int capacity() {
        return values.length;
    }

    @Override
    public void insert(int toInsert, int index) {
        ensureCapacity();
        super.insert(toInsert, index);
    }

    @Override
    public void add(int toBeAdded) {
        ensureCapacity();
        super.add(toBeAdded);
    }

    /**
     * The minimum size of array to be shrunk
     * (to avoid shrinking and growing too frequently)
     */
    private static final int MIN_SHRINK_SIZE = 50;

    /** The minimum fraction of the array that can be full in order to shrink */
    private static final double SHRINK_THRESHOLD = .5;

    /** The amount to grow the values array by when it fills as a factor of its length */
    private static final double GROWTH_FACTOR = 1.5;

    @Override
    public void remove(int index) {
        super.remove(index);
        if (values.length >= MIN_SHRINK_SIZE && size() <= SHRINK_THRESHOLD * values.length) {
            int[] newValues = new int[(int) Math.ceil(values.length / GROWTH_FACTOR)];
            for (int i = 0; i < size(); i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }

    /**
     * Makes sure there's room for another element in the values array.
     * If not, values is copied into a new, bigger array.
     */
    private void ensureCapacity() {
        if (values.length == 0) {
            values = new int[1];
            return;
        }

        if (size() == values.length) {
            int[] newValues = new int[(int) Math.ceil(values.length * GROWTH_FACTOR)];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }
}
