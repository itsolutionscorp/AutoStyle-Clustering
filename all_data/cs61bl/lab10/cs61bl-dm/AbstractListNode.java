import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
	protected Comparable myItem;
	protected AbstractListNode myNext;

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();

	abstract public Comparable get(int pos);

	abstract public String toString();

	abstract public boolean equals(AbstractListNode a);
	
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}
		return smallestHelper(item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (!next().isEmpty()) {
			return min(smallestSoFar, next().smallest());
		} else {
			return smallestSoFar;
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
		if (!isEmpty()) {
			return new NonemptyListNode(item(), next().add(c));
		} else {
			return new NonemptyListNode(c, new EmptyListNode());
		}
	}
		
	public AbstractListNode append(AbstractListNode list) {
		if (!isEmpty()) {
			return new NonemptyListNode(item(), next().append(list));
		} else {
			return list;
		}
	}
	
	public AbstractListNode reverse() {
		Comparable temp;
		if (!isEmpty()) {
			return next().reverse().add(item());
		}
		return new EmptyListNode();
	}
	
	public void setItem(Comparable newItem) {
		myItem = newItem;
	}
	
	public void setNext(AbstractListNode newNext) {
		myNext = newNext;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (isEmpty()) {
			return list2;
		}
		if (list2 == this) {
			throw new IllegalArgumentException("Cyclic additions not allowed!");
		}
		if (next().isEmpty()) {
			setNext(list2);
			return this;
		} else {
			return next().appendInPlace(list2);
		}
	}
	
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b ) {
		
		// Would be much simpler if we were allowed to create a new array to store the result
		
		// Base cases
		if(a.isEmpty())
			return b;
		if(b.isEmpty())
			return a;
		
		// Sets a to the list with the lowest value
		AbstractListNode tmp = a;
		if(min(a.item(), b.item()) == b.item()) {
			a = b;
			b = tmp;
		}
		
		// Check to avoid null pointer errors
		if(a.next().isEmpty()) {
			a.setNext(b);
			return a;
		}
		
		// Preserve original reference
		tmp = a;
					
		// Recursion, compares a.next() and b's values
		if(min(a.next().item(), b.item()).equals(a.next().item())) {
			
			// Moves a forward by one
			a = a.next();
			
		} else {
			
			// Inserts b in a between current and next
			AbstractListNode tmp1 = b.next();
			b.setNext(a.next());
			a.setNext(b);
			b = tmp1;
			// Moves a to original next
			a = a.next().next();
		}
		
		// a is now sorted till current position		
		// Calls merge on a.next() and b
		a.setNext(merge(a.next(), b));
		
		return tmp;
	}
}

class NonemptyListNode extends AbstractListNode {
	
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
		return 1 + this.myNext.size();
	}

	public Comparable get(int pos) {
		if (pos == 0)
			return this.item();
		return this.next().get(pos - 1);
	}

	public String toString() {
		AbstractListNode n = this;
		String s = "( ";
		while (!n.isEmpty()) {
			s += n.item() + " ";
			n = n.next();
		}
		s += ")";
		return s;
	}

	public boolean equals(AbstractListNode a) {
		if(this.size() != a.size())
			return false;
		if(this.item() != a.item())
			return false;
		return this.next().equals(a.next());
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

	public Comparable get(int pos) {
		return item();
	}

	public String toString() {
		return "( )";
	}
	
	public boolean equals(AbstractListNode a) {
		if(this.size() != a.size())
			return false;
		if(this.size() == 0)
			return true;
		return false;
	}
}
