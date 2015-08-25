abstract public class AbstractListNode {

	abstract public Object item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();
	abstract public Object get(int pos);
	abstract public String toString();
	abstract public boolean equals(Object arg);
}

class NonemptyListNode extends AbstractListNode {

	private Object myItem;
	private AbstractListNode myNext;

	public NonemptyListNode (Object item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode (Object item) {
		this (item, new EmptyListNode());
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

	public int size() {
		if (this.isEmpty()) {
			return 0;
		}
		else {
			return 1 + this.next().size(); 
		}
	}
	
	public Object get(int pos) {
		if (pos == 0) {
			return this.item();
		}
		else if (this.isEmpty() && pos > 0) {
			throw new IllegalArgumentException("out of range");
		}
		else {
			return this.next().get(pos-1);
		}
		
	}
	
	public String toString() {
		String total = "( ";
		int counter = 0;
		while (counter < this.size()) {
			total += this.get(counter) + " ";
			counter ++;
		}
		return total + ")";
	}
	
	public boolean equals(Object arg) {
		if (arg instanceof NonemptyListNode) {
			NonemptyListNode node = (NonemptyListNode) arg;
			int counter = 0;
			if (this.size() != node.size()) {
				return false;
			}
			while (counter < this.size()) {
				if (this.get(counter) != node.get(counter)) {
					return false;
				}
				counter ++;
			} 
			return true;
		} else {
			return false;
		}
		
	}
}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Object item() {
		throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}
	public int size() {
		return 0;
	}
	public Object get(int pos) {
		return null;
	}
	public String toString() {
		return "()";
	}
	public boolean equals(Object arg) {
		if (arg instanceof EmptyListNode) {
			return true;
		} else {
			return false;
		}
	}
}
