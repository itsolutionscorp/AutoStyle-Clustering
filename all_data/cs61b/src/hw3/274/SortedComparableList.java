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
        this.head = head0;
        this.tail = tail0;
    }

    /** A list with null tail, and head = 0. */
    public SortedComparableList(){
        this.head = 0;
        this.tail = null;
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
        SortedComparableList lst = this;
        while(c.compareTo(lst.head) > 0 && lst.tail != null) {
          lst = lst.tail;
        }
        if(c.compareTo(lst.head) < 0) {
          SortedComparableList temp = new SortedComparableList(lst.head, lst.tail);
          lst.head = c;
          lst.tail = temp;
        } else {
          SortedComparableList temp = new SortedComparableList(c, lst.tail);
          lst.tail = temp;
        }
                
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        SortedComparableList x = this;
        while(i > 0 && x.tail != null) {
          i -= 1;
          x = x.tail;
        }
        return x.head; // REPLACE THIS LINE WITH YOUR SOLUTION
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        SortedComparableList curr = that;
        while(curr != null) {
          this.insert(curr.head);
          curr = curr.tail;
        }
        // REPLACE THIS LINE WITH YOUR SOLUTION
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        SortedComparableList x = L;
        while(start > 0 && L != null) {
          x = x.tail;
          start -= 1;
        }
        return x; // REPLACE THIS LINE WITH YOUR SOLUTION
    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        // SortedComparableList tail1 = subTail(L, start);
        // int i = 0;
        // SortedComparableList x = tail1;
        // while(i < len) {
        //   x = x.tail;
        //   i += 1;
        // }
        // x.tail = null;
        // return tail1; // REPLACE THIS LINE WITH YOUR SOLUTION
        if(L == null || len == 0) {
          return null;
        } else if(start > 0) {
          return sublist(L.tail, start - 1, len);
        }
        return new SortedComparableList(L.head, sublist(L.tail, start, len - 1));
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        // SortedComparableList x = L;
        // int i = 0;
        // while(i < len) {
        //   x = x.tail;
        //   i += 1;
        // }
        // x.tail = null;
        if(len == 0) {
          L.tail = null;
          return;
        } else if(L == null) {
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
     *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
     *  output list is [ 0 1 0 3 1 0 ].
     **/
    public void squish() {
        // SortedComparableList x = this;
        // SortedComparableList y = null;
        // while(x.tail != null) {
        //   int comp = x.head.compareTo(x.tail.head);
        //   y = new SortedComparableList(x.head, y);
        //   while(x.head.compareTo(x.tail.head) == 0) {
        //     x = x.tail;
        //   }
        //   x = x.tail;
          
        // }    
        // this = y;
        // REPLACE THIS LINE WITH YOUR SOLUTION
        SortedComparableList lst = this;
        while(lst != null && lst.tail != null) {
          SortedComparableList temp = lst;
          while(temp.tail != null && temp.head.compareTo(temp.tail.head) == 0) {
            temp = temp.tail;
          }
          lst.head = temp.head;
          lst.tail = temp.tail;
          lst = lst.tail;
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
        SortedComparableList x = this;
        while(x != null) {
          SortedComparableList temp = new SortedComparableList(x.head, x.tail);
          x.tail = temp;
          x = x.tail.tail;
        }
        // REPLACE THIS LINE WITH YOUR SOLUTION
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