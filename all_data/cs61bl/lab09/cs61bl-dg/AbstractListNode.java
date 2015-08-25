abstract public class AbstractListNode {

    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
	abstract public int size();
	abstract public Object get(int x);
	abstract public String toString();
	public String toString(boolean iscontinuation) {return toString();}
	abstract public boolean equals(AbstractListNode compare);

    // Every other list-processing method goes here.

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
		return 1 + next().size();
	}

	public Object get(int pos) {
		if (pos == 0) {
			return item();
		}
		return next().get(pos-1);
	}

	public String toString() {
		return "( " + toString(true);
	}

	public String toString(boolean continuation) {
		return item().toString() + " " + next().toString(true);
	}

	public boolean equals(AbstractListNode compare) {
		if (compare.isEmpty()) {
			return false;
		}
		if (item().equals(compare.item())) {
			return next().equals(compare.next());
		}
		return false;
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
		return 1;
	}

	public Object get(int noref) {
		throw new IndexOutOfBoundsException("List index out of bounds");
	}

	public String toString() {
		return "()";
	}

	public String toString(boolean iscontinuation) {
		return ")";
	}

	public boolean equals(AbstractListNode compare) {
		if (compare.isEmpty()) {
			return true;
		}
		return false;
	}

}
