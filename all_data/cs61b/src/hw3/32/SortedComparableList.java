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
      if (c != null) {

        SortedComparableList newThis = this;

        if (c.compareTo(head) < 0) {
          this.tail = new SortedComparableList(this.head, this.tail);
          this.head = c;
        } else {

          while (newThis.tail != null && c.compareTo(newThis.tail.head) > 0) {
            newThis = newThis.tail;
          } 
            newThis.tail = new SortedComparableList(c, newThis.tail);
        }
      }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
      if (this == null || i == 0) {
        return head;
      }

      SortedComparableList newThis = this;
      while ((newThis.tail != null) && (i > 0)) {
        newThis = newThis.tail;
        i -= 1;
      }
      return this.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
      while (that != null) {
        this.insert(that.head);
        that = that.tail;
        // if (that.head.compareTo(this.head) > 0) { // IF that's head is larger than this's head, then insert it at the front
        //   this.tail = new SortedComparableList(this.head, this.tail);
        //   this.head = that.head;
        //   that = that.tail;
        // } else if (this.tail == null) {
        //   this.tail = new SortedComparableList(that, null);
        // } else {
        //   this = this.tail;
        // } 
      }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
      if ((start == 0) || (L.tail == null)) {
        return L;
      } else {
        return subTail(L.tail, start - 1);
      }

      // SortedComparableList S = new SortedComparableList();

      // while (start != 0) {
      //   S = S.insert(L.head);
      //   L = L.tail;
      //   start -= 1;
      // }

      // return S;
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
      } else if (start > 0) {
        return sublist(L.tail, start - 1, len);
      } else {
        return new SortedComparableList(L.head, sublist(L.tail, start, len - 1));
      }
      // SortedComparableList S = new SortedComparableList();

      // while (start > 0) {
      //   L = L.tail;
      // }

      // while (len > 0) {

      // }

    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
      if (L == null) {
        return;
      } else if (len == 0) {
        L.tail = null;
        return;
      } else {
        expungeTail(L.tail, len -1);
      }

      // if ((len == 0) || (L == null)) {
      //   L = SortedComparableList();
      // } else {
      //   expungeTail(L.tail, len - 1);
      // }
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
          SortedComparableList l = this;
          SortedComparableList n = this.tail;

          while (n != null) {
              if (l.head.equals(n.head)) {
                  l.tail = n.tail;
                  n = l.tail;
              } else {
                  l = l.tail;
                  n = l.tail;
              }
          }
      }
      // SortedComparableList s = new SortedComparableList(this.head, null);
      // this = this.tail;

      // while (this.tail != null) {
      //   if ((this.head == this.tail.head) || (this.head == s.head)) {
      //     this = this.tail
      //   } else {
      //     s.insert(this.head);
      //   }
      // }
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
      SortedComparableList s = this;

      while (s != null) {
        s.tail = new SortedComparableList(s.head, s.tail);
        s = s.tail.tail;
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