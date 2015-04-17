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
        this.head = 0;
        this.tail = null;
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
      if (c != null) {
        SortedComparableList singleCell = new SortedComparableList(c,null);
        SortedComparableList tempRef = this;
        if (tempRef.head.compareTo(c) > 0) {
          // edge case, c goes in front of everything
          singleCell.head = tempRef.head;
          singleCell.tail = tempRef.tail;
          tempRef.head = c;
          tempRef.tail = singleCell;
        } else {
            while (tempRef.tail != null && tempRef.tail.head.compareTo(c) < 0) {
              tempRef = tempRef.tail;
            }
            // now tempRef.tail.head is FIRST ELEMENT >= c
            //  OR such an element doesn't exist and tempRef.tail = null
            singleCell.tail = tempRef.tail;
            tempRef.tail = singleCell;
        }
      }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        SortedComparableList curr = this;
        for (int j = 0; j < i; j += 1) {
          curr = curr.tail;
        }
        return curr.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        SortedComparableList working = this;
        SortedComparableList remaining = that;
        while (that != null) {
          working.insert(that.head);
          that = that.tail;
        }
        this.head = working.head;
        this.tail = working.tail;
      }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        if (L == null || start == 0) {
          return L;
        } else {
          return subTail(L.tail,start-1);
        }
    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        if (L == null || len == 0 || subTail(L, start) == null) {
          return null;
        } else {
          SortedComparableList wantedExtra =  new SortedComparableList(subTail(L, start).head,subTail(L, start).tail);
          SortedComparableList temp = wantedExtra;
          int numBehind = 1;
          while (temp.tail != null && numBehind < len) {
              temp = temp.tail;
              numBehind  = numBehind  + 1;
          }
          temp.tail = null;
          return wantedExtra;
        }
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        // len = 0 --> set L.tail = null
      if (L != null) {
        int count  = 0;
        SortedComparableList temp = L;
        while (temp != null && count < len) {
          temp = temp.tail;
          count = count + 1;
        }
        temp.tail = null;
      } else {
        // do nothing
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
        SortedComparableList aheadList = this;
        SortedComparableList fixedList = this;
        while (aheadList.tail != null) {
          Comparable ahead = aheadList.tail.head;
          if (fixedList.head.compareTo(ahead) != 0) {
            fixedList.tail = aheadList.tail; // writes changes to "this" object
            fixedList = fixedList.tail; // moves fixed reference forward
          }
          aheadList = aheadList.tail; // moves ahead reference forward
        }
        fixedList.tail = null; // closes out "this" object
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
      // REPLACE THIS LINE WITH YOUR SOLUTION
      SortedComparableList curr = this;
      while (curr != null) {
        curr.tail = new SortedComparableList(curr.head,curr.tail);
        curr = curr.tail.tail;
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