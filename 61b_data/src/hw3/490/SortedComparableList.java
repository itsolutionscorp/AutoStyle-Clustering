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
      //place c when tail.head > c
      //1->2->5  ins(3) --> 1->2->3->5
      SortedComparableList traverser = this;
      while (traverser.tail!=null && traverser.tail.head.compareTo(c) < 0)
        traverser = traverser.tail;
      traverser.tail = new SortedComparableList(c, traverser.tail);
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        //return null; // REPLACE THIS LINE WITH YOUR SOLUTION
      int counter = 0;
      SortedComparableList traverser = this;
      while(counter < i){
        traverser = traverser.tail;
        counter+=1;
      }
      return traverser.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
      
      SortedComparableList traverser2 = that;

      while(traverser2!=null){
        SortedComparableList traverser1 = this;
        // 1->2->5  3->4->7 ==> 1->2->3
        while(traverser1!=null && traverser1.tail!=null && traverser2.head.compareTo(traverser1.tail.head)>0) {

          traverser1 = traverser1.tail;
        }
        traverser1.tail = new SortedComparableList(traverser2.head, traverser1.tail);
        traverser2 = traverser2.tail;

      }

      // while (traverser.tail!=null) {
      //   traverser = traverser.tail;
      // }
      // traverser.tail = that;
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        //return null; // REPLACE THIS LINE WITH YOUR SOLUTION
      SortedComparableList traverser = L;
      int counter = 0;
      while (counter < start && traverser!=null)
      {
        traverser = traverser.tail;
        counter +=1;
      }
      return traverser;

    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        //return null; // REPLACE THIS LINE WITH YOUR SOLUTION

      // if(start>0)
      //   return sublist(L, start-1, len);
      // else if (len>0 && L!=null)
      //   return new SortedComparableList(L.head, sublist(L.tail, start, len-1));
      // else
      //   return null;

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
      SortedComparableList traverser = L;
      int counter = 0;
      while (counter < len && traverser!=null){
        traverser = traverser.tail;
        counter +=1;
      }
      traverser.tail = null;

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
        // REPLACE THIS LINE WITH YOUR SOLUTION
      SortedComparableList traverser = this;

      while(traverser!=null){
        Comparable starting = traverser.head;
        while(traverser.tail!=null && starting.compareTo(traverser.tail.head)==0)
        {
          traverser.tail = traverser.tail.tail;
          starting = traverser.head;
        }
        traverser = traverser.tail;
      }
    }

      
    //   toReturn = new SortedComparableList(head);
    //   while(tail!=null && head.compareTo(tail.head)==0){
        
    //   }

    //   this = toReturn;
    // }

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
      SortedComparableList traverser = this;
      while(traverser!=null ){
        traverser.tail = new SortedComparableList(traverser.head, traverser.tail);
        traverser = traverser.tail.tail;
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