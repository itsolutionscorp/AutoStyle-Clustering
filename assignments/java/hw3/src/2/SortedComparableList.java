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
        this(0, null);
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
        if(c != null) {
            if(c.compareTo(head) < 0) {
                tail = new SortedComparableList(head, tail);
                head = c;
            } else {
                SortedComparableList current = this;
                while(current.tail != null && c.compareTo(current.tail.head) >= 0) {
                    current = current.tail;
                }
                current.tail = new SortedComparableList(c, current.tail);
            }
        }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        if(head == null) {
            return head;
        }
        SortedComparableList current = this;
        while(current.tail != null && i != 0){
            current = current.tail;
            i -= 1;
        }
        return current.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        SortedComparableList temp = that;
        while(temp != null) {
            insert(temp.head);
            temp = temp.tail;
        }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        if(L == null || start == 0) {
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
        if(len == 0) {
            return null;
        } else if(start == 0) {
            return new SortedComparableList(L.head, sublist(L.tail, 0, len - 1));
        } else {
            return sublist(L.tail, start - 1, len);
        }
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        if(L == null) {
            return;
        } else if(len == 0) {
            L.tail = null;
            return;
        } else {
          expungeTail(L.tail, len - 1);
        }
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
        SortedComparableList start = this;
        SortedComparableList next = tail;
        while(next != null) {
            if(start.head.equals(next.head)) {
                start.tail = next.tail;
                next = start.tail;
            } else {
                start = start.tail;
                next = next.tail;
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
        SortedComparableList temp = this;
        while(temp != null) {
            temp.tail = new SortedComparableList(temp.head, temp.tail);
            temp = temp.tail.tail;
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

    /**
     * Checking the appropriate implementations of SortedComparableList
     */
    public static void main(String[] args) {
        SortedComparableList lst = new SortedComparableList();
        lst.insert(1);
        lst.insert(2);
        lst.insert(4);
        lst.insert(3);
        System.out.println(lst.toString());
        lst.insert(4);
        lst.insert(3);
        System.out.println(lst.toString());
        lst.squish();
        System.out.println(lst.toString());
        lst.twin();
        lst.twin();
        System.out.println(lst.toString());
        lst.squish();
        System.out.println(lst.toString());
    }
}