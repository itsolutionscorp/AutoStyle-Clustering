import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.

	abstract public int size();

	abstract public Comparable get(int index);

	abstract public String toString();

	abstract public boolean equals(AbstractListNode obj);

	abstract public AbstractListNode appendInPlace(AbstractListNode list2);

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return smallestHelper(item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (isEmpty()) {
			return smallestSoFar;
		} else {
			return next().smallestHelper(min(item(), smallestSoFar));
		}
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	public AbstractListNode add(Comparable c) {
		if (isEmpty()) {
			AbstractListNode newList = new NonemptyListNode(c);
			return newList;
		} else {
			AbstractListNode newList = new NonemptyListNode(item(), next().add(
					c));
			return newList;
		}
	}

	public AbstractListNode append(AbstractListNode list) {
		if (isEmpty()) {
			return list;
		} else {
			AbstractListNode newList = new NonemptyListNode(item(), next()
					.append(list));
			return newList;
		}
	}

	public AbstractListNode reverse() {
		if (isEmpty()) {
			return this;
		} else if (next().isEmpty()) {
			return new NonemptyListNode(item());
		} else {
			return next().reverse().add(item());
		}
	}
	
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
		if (a.isEmpty()){
			return b;
		} else if (b.isEmpty()){
			return a;
		}
		
	    if (a.item().compareTo(b.item()) > 0) {
	    	AbstractListNode temp = b.next();
	    	((NonemptyListNode) b).setNext(merge(a,temp));
	    	return b;
	    } else {
	    	AbstractListNode temp = a.next();
	    	((NonemptyListNode) a).setNext(merge(b,temp));
	    	return a;
	    }
		
	}
	
	

}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

	public void setItem(Comparable item) {
		myItem = item;
	}

	public void setNext(AbstractListNode next) {
		myNext = next;
	}

	public AbstractListNode appendInPlace(AbstractListNode list2) {
		setNext(next().appendInPlace(list2));
		return this;
	}

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

	// size method
	public int size() {
		return 1 + myNext.size();
	}

	// get method
	public Comparable get(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("negative index");
		} else if (index > (this.size() - 1)) {
			throw new IllegalArgumentException("index out of range");
		}

		if (index == 0) {
			return myItem;
		} else {
			return myNext.get(index - 1);
		}
	}

	// toString method
	public String toString() {
		String stringRepresentation = "(";
		AbstractListNode value = this;

		for (int size = value.size(); size > 0; size--) {
			stringRepresentation += " " + value.item();
			value = value.next();
		}

		stringRepresentation += " )";
		return stringRepresentation;

	}

	// equals method
	public boolean equals(AbstractListNode obj) {

		if (obj instanceof NonemptyListNode) {
			if (this.size() == ((AbstractListNode) obj).size()) {
				if (this.toString().equals(obj.toString())) {
					return true;
				}
			}
		}
		return false;
	}

}

class EmptyListNode extends AbstractListNode {

	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}

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

	// size method
	public int size() {
		return 0;
	}

	// get method
	public Comparable get(int index) {
		throw new IllegalArgumentException("index out of range");
	}

	// toString method

	public String toString() {
		return "( )";
	}

	// equal method

	public boolean equals(AbstractListNode obj) {

		if (obj instanceof EmptyListNode) {
			// since it is an empty list further comparison is not required
			return true;
		}
		return false;

	}
	
	

}
