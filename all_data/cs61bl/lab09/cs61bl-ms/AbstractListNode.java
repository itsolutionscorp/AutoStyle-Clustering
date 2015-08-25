abstract public class AbstractListNode {

	abstract public Object item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	abstract public Object get(int a);

	abstract public boolean equals(Object b);
	// Every other list-processing method goes here.

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

	public int size() {
		if (this.isEmpty() == true) { // base case
			return this.size();
		} else {
			return 1 + this.next().size();
		}
	}

	public Object get(int a) {
		if (a > size()) {
			return null;
		} else if (a == 0) { // base case
			return this.item();
		} else {
			return this.next().get(a - 1);
		}
	}

	public String toString() {
		String temp = "(";
		for (int i = 0; i < size(); i++) {
			temp = temp + " " + get(i);
		}
		return temp + " )";
	}

	public boolean equals(Object b) {
		boolean temp = true;
		if (((AbstractListNode) b).size() != this.size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (((AbstractListNode) b).get(i) != get(i)) {
				temp = false;
				break;
			}
		}
		return temp;
	}
}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Object item() {
		throw new IllegalArgumentException("There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException("No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object get(int a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object b) {
		// TODO Auto-generated method stub
		return false;
	}

}