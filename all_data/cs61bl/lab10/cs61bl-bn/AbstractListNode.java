import java.util.NoSuchElementException;

abstract public class AbstractListNode implements Comparable<AbstractListNode> {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public void setNext(AbstractListNode a);

	abstract public void setItem(Comparable o);

	public int size() {
		int size = 0;
		AbstractListNode placeHolder = this;
		while (!placeHolder.next().isEmpty()) {
			size++;
			placeHolder = placeHolder.next();
		}
		return size + 1;
	}

	public Comparable get(int position) throws IllegalArgumentException {
		AbstractListNode placeHolder = this;

		for (int k = 0; k < position; k++) {
			placeHolder = placeHolder.next();

			if (placeHolder == null) {
				throw new IllegalArgumentException();
			}
		}
		return placeHolder;
	}

	public String toString() {
		String s = "( ";

		AbstractListNode placeHolder = this;
		while (!placeHolder.isEmpty()) {
			s += placeHolder.item() + " ";
			placeHolder = placeHolder.next();
		}

		return s + ")";
	}

	public boolean equals(Comparable o) {
		AbstractListNode placeHolder1 = this;
		AbstractListNode placeHolder2 = (AbstractListNode) o;
		if (placeHolder1.size() != placeHolder2.size()) {
			return false;
		}
		while (!placeHolder1.isEmpty()) {
			if (placeHolder1.item() != placeHolder2.item()) {
				return false;
			}
			placeHolder1 = placeHolder1.next();
			placeHolder2 = placeHolder2.next();

		}

		return true;

	}

	public int compareTo(AbstractListNode o) {
		// TODO Auto-generated method stub
		// return Integer.compare(Integer.parseInt((String) this.item()),
		// Integer.parseInt((String) o.item()));
		return Integer.compare((Integer) this.item(), (Integer) o.item());
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (this.isEmpty()) {
			return smallestSoFar;
		} else {
			return this.next().smallestHelper(min(this, smallestSoFar));
		}

	}

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return this.smallestHelper(this);

	}

	public AbstractListNode add(Comparable c) {
		AbstractListNode newHead;
		if (isEmpty()) {
			newHead = new NonemptyListNode(c, null);
			return newHead;
		} else {
			newHead = new NonemptyListNode(item(), next().add(c));
			return newHead;
		}
	}

	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode theLast = this;
		if (isEmpty()) {
			if (list.isEmpty()) {
				theLast = new EmptyListNode();
				return theLast;
			}
			theLast = new NonemptyListNode(list.item(), append(list.next()));
			return theLast;

		} else {
			theLast = new NonemptyListNode(item(), next().append(list));
			return theLast;

		}
	}

	public AbstractListNode reverse() {

		AbstractListNode placeHolder = this;
		AbstractListNode theTop = new EmptyListNode();

		while (!placeHolder.isEmpty()) {
			AbstractListNode top = new NonemptyListNode(placeHolder.item(),
					theTop);
			theTop = top;
			placeHolder = placeHolder.next();
		}
		return theTop;
	}

	public static AbstractListNode clone(AbstractListNode ListNode) {
		AbstractListNode a;
		if (ListNode.isEmpty()) {
			return ListNode;
		}
		a = new NonemptyListNode(ListNode.item(), clone(ListNode.next()));
		return a;

	}

	public AbstractListNode appendInPlace(AbstractListNode list2) {
		AbstractListNode cloneList2 = AbstractListNode.clone(list2);
		if (isEmpty()) {
			return cloneList2;
		}
		AbstractListNode placeHolder = this;
		while (!placeHolder.next().isEmpty()) {
			placeHolder = placeHolder.next();
		}
		placeHolder.setNext(cloneList2);
		return this;

	}

	/*
	 * this method is constructive
	 */
	// public static AbstractListNode merge(AbstractListNode a, AbstractListNode
	// b) {
	// AbstractListNode result;
	// AbstractListNode aNext;
	// AbstractListNode bNext;
	// Comparable item;
	// if (a.isEmpty() && b.isEmpty()) {
	// return new EmptyListNode();
	// }
	//
	// if (a.isEmpty()) {
	// return b;
	// }
	// if (b.isEmpty()) {
	// return a;
	// }
	//
	// if (a.compareTo(b) < 0) {
	// item = a.item();
	// aNext = a.next();
	// bNext = b;
	// } else {
	// item = b.item();
	// aNext = a;
	// bNext = b.next();
	// }
	// result = new NonemptyListNode(item,
	// AbstractListNode.merge(aNext, bNext));
	//
	// return result;
	// }

	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
		if (a.isEmpty() && b.isEmpty()) {
			return new EmptyListNode();
		}
		if (a.isEmpty()) {
			return b;
		}
		if (b.isEmpty()) {
			return a;
		}
		AbstractListNode start;
		AbstractListNode aPlaceHolder;
		AbstractListNode bPlaceHolder;

		if (a.compareTo(b) < 0) {
			start = a;
			aPlaceHolder = a.next();
			bPlaceHolder = b;
		} else {
			start = b;
			aPlaceHolder = a;
			bPlaceHolder = b.next();
		}
		AbstractListNode result = start;

		while (!aPlaceHolder.isEmpty() && !bPlaceHolder.isEmpty()) {
			if (aPlaceHolder.compareTo(bPlaceHolder) < 0) {
				start.setNext(aPlaceHolder);
				start = aPlaceHolder;
				aPlaceHolder = aPlaceHolder.next();
			} else {
				start.setNext(bPlaceHolder);
				start = bPlaceHolder;
				bPlaceHolder = bPlaceHolder.next();
			}
		}
		if(aPlaceHolder.isEmpty()){
			start.setNext(bPlaceHolder);
		}
		if(bPlaceHolder.isEmpty()){
			start.setNext(aPlaceHolder);
		}
		return result;
	}

}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

	public NonemptyListNode(Comparable item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode(Comparable item) {
		this(item, new EmptyListNode());
	}

	public Comparable item() {
		return myItem;
	}

	public AbstractListNode next() {
		return myNext;
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setNext(AbstractListNode list2) {
		myNext = list2;
	}

	@Override
	public void setItem(Comparable o) {
		myItem = o;
	}
}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

	@Override
	public void setNext(AbstractListNode list2) {
		throw new IllegalArgumentException("the argument is empty");
	}

	@Override
	public void setItem(Comparable o) {
		throw new IllegalArgumentException("this is empty.");
	}

	@Override
	public int compareTo(AbstractListNode o) {
		return Integer.compare((Integer) 0, (Integer) o.item());
	}
}
