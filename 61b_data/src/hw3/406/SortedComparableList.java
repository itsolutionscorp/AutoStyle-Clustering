import static org.junit.Assert.assertEquals;

import java.util.Formatter;

/**
 * SortedComparableList.java A list of Comparables in ascending order.
 */
public class SortedComparableList {
	/** First element of list. */
	public Comparable head;
	/** Remaining elements of list. */
	public SortedComparableList tail;

	public static void main(String[] args) {
		
	}

	/** A list with head HEAD0 and tail TAIL0. */
	public SortedComparableList(Comparable head0, SortedComparableList tail0) {
		this.head = head0;
		this.tail = tail0;
	}

	/** A list with null tail, and head = 0. */
	public SortedComparableList() {
		this.head = 0;
		this.tail = null;
	}

	/** Inserts Comparable c into its correct location in this list. */
	public void insert(Comparable c) {
		Comparable temp;
		
		if (c == null){
			return;
		}

		if (c.compareTo(this.head) < 0) {
			temp = this.head;
			this.head = c;
			if (this.tail == null) {
				this.tail = new SortedComparableList(temp, null);
			} else {
				this.tail.insert(temp);
			}

		} else {
			if (this.tail == null) {
				this.tail = new SortedComparableList(c, null);
			} else {
				this.tail.insert(c);
			}

		}
	}

	/**
	 * Returns the i-th int in this list. The first element, which is in
	 * location 0, is the 0th element. Assume i takes on the values [0, length
	 * of list - 1].
	 */
	public Comparable get(int i) {
		if (i == 0 || head == null) {
			return this.head;
		} else {
			return this.tail.get(i - 1);
		}
	}

	/** Adds every item in THAT to this list. */
	public void extend(SortedComparableList that) {
		while (that != null) {
			this.insert(that.head);
			that = that.tail;
		}
	}

	/**
	 * Returns a list consisting of the elements of L starting from position
	 * START, and going all the way to the END. The head of the list L is the
	 * 0th element of the list.
	 *
	 * This method should NOT modify L.
	 */
	public static SortedComparableList subTail(SortedComparableList L, int start) {
		SortedComparableList newList;
		SortedComparableList ptr = L;
		int counter = 0;
		while (counter < start) {
			ptr = ptr.tail;
			counter++;
		}
		newList = new SortedComparableList(ptr.head, null);
		ptr = ptr.tail;
		while (ptr != null) {
			newList.insert(ptr.head);
			ptr = ptr.tail;
		}
		return newList;

	}

	/**
	 * Returns the sublist consisting of LEN items from list L, beginning with
	 * item START (where the first item is 0).
	 *
	 * Does not modify the original list elements. Assume START and END are >=
	 * 0.
	 */
	public static SortedComparableList sublist(SortedComparableList L,
			int start, int len) {
		SortedComparableList newList;
		SortedComparableList ptr = L;
		int counter = 0;
		while (counter < start) {
			ptr = ptr.tail;
			counter++;
		}
		newList = new SortedComparableList(ptr.head, null);
		ptr = ptr.tail;
		while (counter < len+start - 1) {
			newList.insert(ptr.head);
			ptr = ptr.tail;
			counter++;
		}
		return newList;
	}

	/** Removes items from L at position len+1 and later. */
	public static void expungeTail(SortedComparableList L, int len) {
		int counter = 0;
		SortedComparableList ptr = L;
		while (counter < len) {
			ptr = ptr.tail;
			counter++;
		}
		ptr.tail = null;
	}

	/**
	 * Takes this list and, wherever two or more consecutive items are equal, it
	 * removes duplicates so that only one consecutive copy remains. No two
	 * consecutive items in this list are equals at the end of this method.
	 *
	 * For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
	 * output list is [ 0 1 0 3 1 0 ].
	 **/
	public void squish() {
		SortedComparableList ptr = this, ptr2 = this;

		while (ptr2 != null) {
			while (ptr.head == ptr2.head) {
				ptr2 = ptr2.tail;
				if (ptr2 == null) {
					break;
				}
			}

			ptr.tail = ptr2;
			ptr = ptr2;

		}
	}

	/**
	 * Duplicates each Comparable so that for every original Comparable, there
	 * will end up being two consecutive Comparables.
	 *
	 * For example, if the input list is [ 3 7 4 2 2 ], the output list is [ 3 3
	 * 7 7 4 4 2 2 2 2].
	 *
	 * NOTE: Do not try to make copies of the Comparables. Set the HEAD variable
	 * equal to the HEAD variable you are trying to duplicate.
	 **/
	public void twin() {
		SortedComparableList ptr = this, temp = this;
		while (ptr != null) {
			temp = ptr.tail;
			ptr.tail = new SortedComparableList(ptr.head, ptr.tail);
			if (temp == null){
				break;
			}
			ptr = temp;
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
			} else {
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

	/**
	 * Returns true iff X is a SortedComparableList containing the same sequence
	 * of Comparables as THIS. Cannot handle cycles.
	 */
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