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
        // REPLACE THIS LINE WITH YOUR SOLUTION
        head = head0;
        tail = tail0;
    }

    /** A list with null tail, and head = 0. */
    public SortedComparableList(){
        // REPLACE THIS LINE WITH YOUR SOLUTION
        head = 0;
        tail = null;
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (c != null) {
            // null check
            if (c.compareTo(head) < 0) {
                // edge case: inserting to front of list
                tail = new SortedComparableList(head, tail);
                head = c;
            }
            else {
                // inserting into middle or end of list
                SortedComparableList curr = this;
                while (curr.tail != null && c.compareTo(curr.tail.head) > 0) {
                    curr = curr.tail;
                }
                curr.tail = new SortedComparableList(c, curr.tail);
            }
        }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (head == null || i = 0) {
            return head;
        }
        else {
            SortedComparableList pointer = tail;
            counter = 1;
            while (counter < i && pointer != null) {
                pointer = pointer.tail;
                counter += 1;
            }
            return pointer.head;
        }
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        SortedComparableList pointer = that;
        while (pointer != null) {
            insert(pointer.head);
            pointer = pointer.tail;
        }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (L == null || start == 0) {
            return L;
        }
        else {
            return subTail(L.tail, start-1);
        }
    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (len == 0) {
            //base case
            return null;
        }
        else if (start > 0) {
            return sublist(L.tail, start - 1, len);
        }
        return new SortedComparableList(L.head, sublist(L.tail, 0, len - 1));
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (L == null) {
            return;
        }
        else if (len == 0) {
            L.tail = null;
            return;
        }
        expungeTail(L.tail, len - 1);
    }

    /**
     *  Takes this list and, wherever two or more consecutive items are
     *  equal, it removes duplicates so that only one consecutive copy
     *  remains. No two consecutive items in this list are equals at the
     *  end of this method.
     *
     *  You can assume the list is in sorted order when this method is
     *  called.
     *
     *  For example, if the input list is [ 0 0 0 0 1 1 3 3 3 4 ], the
     *  output list is [ 0 1 3 4 ].
     **/
    public void squish() {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (tail != null) {
            SortedComparableList prev = this;
            SortedComparableList curr = tail;
            while (curr != null) {
                if (prev.head.equals(curr.head)) {
                    prev.tail = curr.tail;
                    curr = prev.tail;
                }
                else {
                    prev = prev.tail;
                    curr = prev.tail;
                }
            }
        }
    }

    /** Duplicates each Comparable so that for every original
     *  Comparable, there will end up being two consecutive Comparables.
     *
     *  You can assume the list is in sorted order when this method is
     *  called.
     *
     *  For example, if the input list is [ 2 3 4 7 ], the
     *  output list is [ 2 2 3 3 4 4 7 7 ].
     *
     *  NOTE: Do not try to make copies of the Comparables. Set
     *  the HEAD variable equal to the HEAD variable you are trying to
     *  duplicate.
     **/
    public void twin() {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        SortedComparableList prev = this;
        while (prev != null) {
            prev.tail = new SortedComparableList(prev.head, prev.tail);
            prev = prev.tail.tail;
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