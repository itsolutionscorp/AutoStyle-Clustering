abstract public class AbstractListNode {

	abstract public Object item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract int size();

	abstract Object get(int index);

	abstract String toString(boolean parenthesis);

	public abstract boolean equals(Object arg);
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
		return 1 + myNext.size();
	}

	public Object get(int index) {
		if (index == 0)
			return myItem;
		return myNext.get(index - 1);
	}

	public String toString(boolean parenthesis) {
		if (parenthesis) {
			return "( " + myItem.toString() + " " + myNext.toString(false);
		}
		return myItem.toString() + " " + myNext.toString(false);
	}

	public boolean equals(Object arg) {
    	try {
    		return size() == ((AbstractListNode)arg).size() && myItem.equals(((AbstractListNode) arg).get(0)) && myNext.equals(((AbstractListNode) arg).next());
    	}
    	catch (ClassCastException e) {
    		return false;
    	}
    	
    }
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

	public Object get(int index) throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}

	public String toString(boolean parenthesis) {
		return ")";
	}
	
	public boolean equals(Object arg) {
		return ((AbstractListNode)arg).isEmpty();
	}
}
