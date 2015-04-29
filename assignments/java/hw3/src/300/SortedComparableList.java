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
        head = head0;
        tail = tail0;
    }

    /** A list with null tail, and head = 0. */
    public SortedComparableList(){
        head = 0;
        tail = null;
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
        if (c.compareTo(head) < 0) {
            Comparable temp = head;
            head = c;
            this.insert(temp);
        } else {
            SortedComparableList pointer = this;
            if (pointer.tail == null) {
                pointer.tail = new SortedComparableList(c, null);
            } else {
            while (pointer.tail.head.compareTo(c) < 0) {
                pointer = pointer.tail;
                if (pointer.tail == null) {
                    break;
                }
            }
            SortedComparableList newcomp = new SortedComparableList(c, pointer.tail);
            pointer.tail = newcomp;
        }
        }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        if (i == 0) {
            return head;
        } else {
            SortedComparableList pointer = this;
            for (int j = 0; j < i; j +=1) {
                pointer = pointer.tail;
            }
            return pointer.head;
        }
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        SortedComparableList pointer = that;
        while (pointer != null) {
            this.insert(pointer.head);
            pointer = pointer.tail;
        }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        if (start == 0) {
            return L;
        } else {
            return subTail(L.tail, start - 1);
        }
    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        if (len == 0) {
            return null;
        }
        SortedComparableList pointer = L;
        for (int i = 0; i < start; i += 1) {
            pointer = pointer.tail;
        }
        SortedComparableList answer = new SortedComparableList(pointer.head, null);
        SortedComparableList answerpointer = answer;
        for (int j = 1; j < len; j += 1) {
            pointer = pointer.tail;
            SortedComparableList newone = new SortedComparableList(pointer.head, null);
            answerpointer.tail = newone;
            answerpointer = answerpointer.tail;
        }
        return answer;
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        SortedComparableList pointer = L;
        for (int i = 0; i < len; i += 1) {
            pointer = pointer.tail;
        }
        pointer.tail = null;
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
        SortedComparableList pointer = this;
        while (pointer.tail != null) {
            if (pointer.head == pointer.tail.head) {
                pointer.tail = pointer.tail.tail;
            } else {
                pointer = pointer.tail;
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
        SortedComparableList pointer = this;
        while (pointer != null) {
            SortedComparableList newone = new SortedComparableList(pointer.head, pointer.tail);
            pointer.tail = newone;
            pointer = pointer.tail.tail;
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