import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();
	abstract public String toString();
	abstract protected String toStringHelper();
	abstract public boolean equals(AbstractListNode ln);
	abstract public AbstractListNode add (Comparable c);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode reverse();
	abstract public void setItem(Comparable item);
	abstract public void setNext(AbstractListNode next);
	public AbstractListNode appendInPlace(AbstractListNode list2){
		if (this.isEmpty()) return list2;
		AbstractListNode ln = this;
		for (int i = 1; i < size(); i++) {
			ln = ln.next();
		}
		ln.setNext(list2);
		return this;
	}
	
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}
		return smallestHelper(this.item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		AbstractListNode currentNode = this;
		while ( !currentNode.isEmpty() ) {
			smallestSoFar = min(smallestSoFar, currentNode.item() );
			currentNode = currentNode.next();
		}
		return smallestSoFar;
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
	    // Fill this out
		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} else {
			if (a.item().compareTo(b.item()) < 0) {
				a.setNext( merge(a.next(), b) );
				return a;
			} else {
				b.setNext( merge(b.next(), a) );
				return b;
			}
 		}
	}
}

class NonemptyListNode extends AbstractListNode {

	public Comparable myItem;
	private AbstractListNode myNext;

	public NonemptyListNode (Comparable item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode (Comparable item) {
		this (item, new EmptyListNode());
	}

	public void setItem(Comparable item) {
		myItem = item;
	}
	
	public void setNext(AbstractListNode next) {
		myNext = next;
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

	public int size() {
		return 1 + this.next().size();
	}

	public String toString() {
		return "(" + toStringHelper();
	}

	protected String toStringHelper() {
		return " " + item().toString() + next().toStringHelper();
	}

	public boolean equals(AbstractListNode ln) {
		if (ln.isEmpty()) {
			return false;
		} else if (!ln.item().equals(item())) {
			return false;
		} else {
			return next().equals(ln.next());
		}

	}

	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(this.item(), this.next().add(c));
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		// TODO Auto-generated method stub
		return new NonemptyListNode(this.item(), this.next().append(list));
	}

	@Override
	public AbstractListNode reverse() {
		// TODO Auto-generated method stub
		return next().reverse().add(item());
	}

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Comparable item() {
		throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
	}
	
	public void setItem(Comparable item) {
		throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
	}

	public void setNext(AbstractListNode next) {
		throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}

	public String toString() {
		return "( )";
	}

	protected String toStringHelper() {
		return " )";
	}

	public boolean equals(AbstractListNode ln) {
		return ln.isEmpty();
	}
	
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		if (list.isEmpty()) return new EmptyListNode();
		return new NonemptyListNode(list.item(), new EmptyListNode().append(list.next()));
	}

	@Override
	public AbstractListNode reverse() {
		return new EmptyListNode();
	}

}
