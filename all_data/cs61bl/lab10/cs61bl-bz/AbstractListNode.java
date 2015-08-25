import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	abstract public AbstractListNode get(int index);

	public String toString() {
		AbstractListNode p = this;
		String result = "( ";
		while (p.size() > 0) {
			result = result + p.item() + " ";
			p = p.next();
		}
		result = result + ")";
		return result;
	}

	abstract public boolean equals(Comparable input);

	// Every other list-processing method goes here.

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return this.item();
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (this.isEmpty()) {
			return this.item();
		} else {
			return this.next().smallestHelper(smallestSoFar);
		}

	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	abstract public NonemptyListNode add(Comparable x);

	abstract public AbstractListNode append(AbstractListNode l);

	abstract public AbstractListNode reverse();

	abstract public AbstractListNode appendInPlace(AbstractListNode list2) ;

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

	public int size() {
		if (next() instanceof EmptyListNode) {
			return 1;
		}

		int size = 1 + next().size();
		return size;
	}

	public AbstractListNode get(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("invalid input");
		}
		if (index == 0) {
			return this;
		}
		return next().get(index - 1);
	}

	public boolean equals(Comparable input) {
		if (input instanceof AbstractListNode) {
			AbstractListNode p = (AbstractListNode) input;
			AbstractListNode q = this;
			if (q.size() == p.size()) {
				while (!(q.next().isEmpty())) {
					if (q.item() != p.item()) {
						return false;
					}
					q = q.next();
					p = p.next();
				}
				return true;
			}
			return false;
		}
		return false;
	}

	public NonemptyListNode add(Comparable x) {
		NonemptyListNode n = new NonemptyListNode(this.item(), this.next().add(
				x));
		return n;

	}

	public AbstractListNode append(AbstractListNode l) {
		AbstractListNode n = new NonemptyListNode(this.item(), this.next()
				.append(l));
		return n;
	};

	public AbstractListNode reverse() {
		AbstractListNode toReturn = new NonemptyListNode(null);
		while(!this.next().isEmpty()){
			Toreturn;
		}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) { 
		if (this.isEmpty()) { 
			return list2; }  
		return this.next().appendInPlace(list2); 
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

	public int size() {
		return 0;
	}

	public AbstractListNode get(int index) {
		if (index != 0) {
			throw new IllegalArgumentException("invalid input");
		}
		return this;
	}

	public boolean equals(Comparable input) {
		if (input instanceof EmptyListNode) {
			return true;
		}
		return false;
	}

	public NonemptyListNode add(Comparable x) {
		NonemptyListNode n = new NonemptyListNode(x);
		return n;
	}

	public AbstractListNode append(AbstractListNode l) {
		return l;
	};

	public AbstractListNode reverse() {
		return this;
	};
	
	public AbstractListNode appendInPlace(AbstractListNode list2) { 
		return new EmptyListNode();
	}

}
