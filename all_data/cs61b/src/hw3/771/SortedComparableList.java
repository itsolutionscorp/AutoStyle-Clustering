import java.util.Formatter;

/**
 * SortedComparableList.java
 * A list of Comparables in ascending order.
 */
public class SortedComparableList {
    /** First element of list. */
    public Comparable head;
    /** Remaining elements of list. */
    public SortedComparableList tail;

    /** A list with head HEAD0 and tail TAIL0. */
    public SortedComparableList(Comparable head0, SortedComparableList tail0) {
        this.head = head0;
        this.tail = tail0;
    }

    /** A list with null tail, and head = 0. */
    public SortedComparableList(){
        this(0, null); // Re-use the constructor that's already been written above.
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
        if (c != null) { // Null check
            if (c.compareTo(this.head) < 0) { // Check if c is smaller than head
                // if true, put c in front of the linked list
                this.tail = new SortedComparableList(this.head, this.tail);
                this.head = c;
            } else {
                SortedComparableList pointer = this;
                // Already know c is larger than this.head
                while (pointer.tail != null && c.compareTo(pointer.tail.head) > 0) { // Have to check is tail is not null first. Otherwise comparing tail.head will cause null pointer exception!
                    pointer = pointer.tail;
                }
                pointer.tail = new SortedComparableList(c, pointer.tail);
            }
        }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        if (head == null || i == 0) {
            return head;
        }
        SortedComparableList pointer = this;
        for (int k = 0; k < i || pointer.tail != null; k += 1) {
            pointer = pointer.tail;
        }
        return pointer.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        // SortedComparableList pointer = that;
        while (that != null) {
            this.insert(that.head);
            that = that.tail;
        }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        SortedComparableList pointer = L;
        for (int i = 0; i < start && L != null; i += 1) {
            pointer = pointer.tail;
        }
        return pointer;
    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        if (L != null) {
            for (int i = 0; i < start && L != null; i += 1) {
                L = L.tail;
            }
            SortedComparableList result = new SortedComparableList(L.head, null);
            SortedComparableList pointer = result;
            for (int k = 1; k < len && L != null; k += 1) { // len doesn't start from index 0 but 1 so k has to starting counting from 1
                L = L.tail;
                pointer.tail = new SortedComparableList(L.head, null);
                pointer = pointer.tail;
            }
            return result;
        }
        return null;
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        for (int i = 0; i < len && L != null; i += 1) {
            L = L.tail;
        }
        L.tail = null;
    }

    /**
     *  Takes this list and, wherever two or more consecutive items are
     *  equal, it removes duplicates so that only one consecutive copy
     *  remains. No two consecutive items in this list are equals at the
     *  end of this method.
     *
     *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
     *  output list is [ 0 1 0 3 1 0 ].
     **/
    public void squish() {
        if (this.tail != null) {
            SortedComparableList prev = this;
            SortedComparableList curr = this.tail;
            while (curr != null) {
                if (prev.head.equals(curr.head)) {
                    prev.tail = curr.tail;
                    curr = prev.tail;
                } else {
                    prev = prev.tail;
                    curr = prev.tail;
                }
            }
        }
    }

    /** Duplicates each Comparable so that for every original
     *  Comparable, there will end up being two consecutive Comparables.
     *
     *  For example, if the input list is [ 3 7 4 2 2 ], the
     *  output list is [ 3 3 7 7 4 4 2 2 2 2].
     *
     *  NOTE: Do not try to make copies of the Comparables. Set
     *  the HEAD variable equal to the HEAD variable you are trying to
     *  duplicate.
     **/
    public void twin() {
        SortedComparableList result = this;
        while (result != null) {
            result.tail = new SortedComparableList(result.head, result.tail);
            result = result.tail.tail;
        }
    }

    /** Returns NULL if no cycle exists, else returns cycle location. */
    private int detectCycles(SortedComparableList A) {
        SortedComparableList tortoise = A;
        SortedComparableList hare = A;
        if (A == null) {
            return 0;
        }
        int cnt = 0;
        while (true) {
            cnt++;
            if (hare.tail != null) {
                hare = hare.tail.tail;
            }
            else{
                return 0;
            }
            tortoise = tortoise.tail;
            if (tortoise == null || hare == null) {
                return 0;
            }
            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    /** Returns true iff X is a SortedComparableList containing the
     *  same sequence of Comparables as THIS. Cannot handle cycles. */
    public boolean equals(Object x) {
        if (!(x instanceof SortedComparableList)) {
            return false;
        }
        SortedComparableList L = (SortedComparableList) x;
        SortedComparableList p;
        for (p = this; p != null && L != null; p = p.tail, L = L.tail) {
            if (p.head != L.head) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;
        for (SortedComparableList p = this; p != null; p = p.tail) {
            out.format("%s%d", sep, p.head);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}