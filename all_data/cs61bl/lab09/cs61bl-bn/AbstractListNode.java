abstract public class AbstractListNode {

	abstract public Object item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	public int size() {
		int size = 0;
		AbstractListNode placeHolder = this;
		while (!placeHolder.next().isEmpty()) {
			size++;
			placeHolder = placeHolder.next();
		}
		return size + 1;
	}

	public Object get(int position) throws IllegalArgumentException {
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

	public boolean equals(Object o) {
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

}

class NonemptyListNode extends AbstractListNode {

	private Object myItem;
	private AbstractListNode myNext;

	public NonemptyListNode(Object item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode(Object item) {
		this(item, new EmptyListNode());
	}

	public Object item() {
		return myItem;
	}

	public AbstractListNode next() {
		return myNext;
	}

	public boolean isEmpty() {
		return false;
	}

	// public void changeNext()

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Object item() {
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

}
