import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	@SuppressWarnings("rawtypes")
	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	private Comparable smallest;

	// Every other list-processing method goes here.
	abstract public int size();

	@SuppressWarnings("rawtypes")
	abstract public Comparable get(int n);

	abstract public String toString();

	abstract public boolean equals(Object arg);

	abstract public AbstractListNode add(Comparable c);

	abstract public AbstractListNode append(AbstractListNode list);

	abstract public void setItem(Comparable c);

	abstract public void setNext(AbstractListNode list);

	abstract public AbstractListNode appendInPlace(AbstractListNode list);
	
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b){
		if (b.isEmpty()){
			return a;
		}
		if (a.isEmpty()){
			return a.appendInPlace(b);
		}
		if (a.item().compareTo(b.item()) > 0){
			Comparable tempComparable = a.item();
			AbstractListNode tempNode = a.next();
			a.setItem(b.item());
			a.setNext(new NonemptyListNode(tempComparable, tempNode));
			return merge(a.next(),b.next());
		} else if (a.item().compareTo(b.item()) < 0) {
			return merge(a.next(), b);
		} else {
			Comparable tempComparable = a.item();
			AbstractListNode tempNode = a.next();
			a.setItem(b.item());
			a.setNext(new NonemptyListNode(tempComparable, tempNode));
			return merge(a.next(),b.next());
		}
	}

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return this.next().smallestHelper(this.item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (isEmpty()) {
			return smallestSoFar;
		}
		return this.next().smallestHelper(min(this.item(), smallestSoFar));

	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}

	}
}

class NonemptyListNode extends AbstractListNode {

	@SuppressWarnings("rawtypes")
	private Comparable myItem;
	private AbstractListNode myNext;

	@SuppressWarnings("rawtypes")
	public NonemptyListNode(Comparable item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public int size() {
		return 1 + myNext.size();
	}

	@SuppressWarnings("rawtypes")
	public Comparable get(int n) {
		if (n == 0) {
			return this.myItem;
		}
		if (!this.myNext.isEmpty()) {
			return this.myNext.get(n - 1);
		} else {
			throw new IllegalArgumentException("get out of range");
		}
	}

	public boolean equals(Object arg) {
		if (arg instanceof AbstractListNode) {
			AbstractListNode node = (AbstractListNode) arg;
			if (this.myItem.equals(node.item())) {
				return this.myNext.equals(node.next());
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public NonemptyListNode(Comparable item) {
		this(item, new EmptyListNode());
	}

	@SuppressWarnings("rawtypes")
	public Comparable item() {
		return myItem;
	}

	public void setItem(Comparable c) {
		this.myItem = c;
	}

	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(this.myItem, this.next().add(c));
	}

	public AbstractListNode append(AbstractListNode list) {
		return new NonemptyListNode(this.myItem, this.next().append(list));
	}

	public AbstractListNode appendInPlace(AbstractListNode list) {
		if (this.next().isEmpty()) {
			this.setNext(list);
			return this;
		}
		return this.next().appendInPlace(list);
	}

	public AbstractListNode next() {
		return myNext;
	}

	public void setNext(AbstractListNode list) {
		this.myNext = list;
	}

	public boolean isEmpty() {
		return false;
	}

	public String toString() {
		String rtn = "(";
		AbstractListNode current = this;
		while (!current.isEmpty()) {
			rtn += " " + current.item().toString();
			current = current.next();
		}
		return rtn + " )";
	}

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public int size() {
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public Comparable get(int n) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	public void setItem(Comparable c) {

	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	public void setNext(AbstractListNode list) {
	}

	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	public AbstractListNode append(AbstractListNode list) {
		return list;
	}

	public AbstractListNode appendInPlace(AbstractListNode list) {
		return list;
	}

	public boolean isEmpty() {
		return true;
	}

	public boolean equals(Object arg) {
		if (arg instanceof EmptyListNode) {
			return true;
		}
		return false;
	};

	public String toString() {
		return "( )";
	}

}
