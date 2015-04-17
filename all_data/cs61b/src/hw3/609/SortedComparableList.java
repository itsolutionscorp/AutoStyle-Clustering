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
        //IF NOT NULL
        if (c != null){
          //INSERT FRONT
          if (c.compareTo(head) < 0){  //if c smaller then front  COMPARABLE METHOD  -1 if smaller, 0 if same, 1 if larger
            tail = new SortedComparableList(head, tail); 
            head = c;
          }
          else {
            /*New pointer to same list --> while c is larger than the next head,
              move the list down one --> when c is less than or equal to the 
              next head then insert in front of that head*/
            SortedComparableList temp = this;
            /*This statement below cannot be flipped. If it is then there is 
              a null pointer exception because when get to end of list because
              c is the largest it checks temp.tail.head when temp.tail = null. */
            while (temp.tail != null && c.compareTo(temp.tail.head)>=0){
              temp = temp.tail;
            }
            temp.tail = new SortedComparableList(c, temp.tail);          
          }
        }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */

    /** My own method  **/
    public Comparable get(int i) {
      int index = 0; //if want [1, length] then need index = 0
      SortedComparableList temp = this;
      while (index < i){  //if want [1, length] then need <= so it doesnt stop before last
        if (temp.tail == null){
          return null;
        }
        temp = temp.tail;
        index += 1;
      }
      return temp.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        SortedComparableList temp = this;
        while (that != null){
          insert(that.head);
          that = that.tail;
        }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        SortedComparableList temp = L;
        int index = 0;
        while (index < start && temp.tail != null){
          temp = temp.tail;
          index += 1;
        }
        return temp;

    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        if (len == 0){
          return null;
        }
        else if (start > 0){
          return sublist(L.tail, start -1, len);
        }
        //every time, tail is set to the new list with the next head and whatever next tail is.
        return new SortedComparableList(L.head, sublist((L.tail), 0, len -1));
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        if (L == null){
          return;
        }
        else if (len == 0){
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
     *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
     *  output list is [ 0 1 0 3 1 0 ].
     **/
    public void squish() {
      if (this.tail != null){
        SortedComparableList curr = this;
        SortedComparableList next = this.tail;
        while (curr.tail != null){
          if (curr.head.compareTo(next.head)==0){
            curr.tail = next.tail;
            next = curr.tail;
          }
          else {
            curr = next;
            next = next.tail;
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
        if (this == null) {
          return;
        }
        SortedComparableList curr = this;
        while (curr.tail != null){
            curr.tail = new SortedComparableList(curr.head, curr.tail);
            curr = curr.tail.tail;
        }
        curr.tail = new SortedComparableList(curr.head, curr.tail);
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

    public static void main(String[] args){
        SortedComparableList mine = new SortedComparableList(1,null);
        mine.tail = new SortedComparableList(3,null);
        mine.tail.tail = new SortedComparableList(5,null);
        mine.insert(2);
        SortedComparableList thatList = new SortedComparableList(4,null);
        thatList.tail = new SortedComparableList(0,null);
        System.out.println("first list: "+mine.toString());
        System.out.println("other list: "+thatList.toString());
        mine.extend(thatList);
        mine.insert(6);
        System.out.println("final augmented list: "+mine.toString());
        System.out.println("first "+mine.get(0));
        System.out.println("second "+mine.get(2));
        System.out.println("seventh "+mine.get(6));
        System.out.println("subtail:  " + subTail(mine,2).toString());
        System.out.println("OG:   " + mine.toString());
        System.out.println("sublist:  " + sublist(mine,2,4).toString());
        System.out.println("OG:   " + mine.toString());
        mine.insert(6);
        mine.insert(6);
        mine.insert(2);
        System.out.println("OG:   " + mine.toString());
        mine.squish();
        System.out.println("Squish:   " + mine.toString());
        System.out.println("OG:   " + mine.toString());
        mine.twin();
        System.out.println("Twin:   " + mine.toString());
    }   
}

