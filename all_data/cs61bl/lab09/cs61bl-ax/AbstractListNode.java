abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public String toString();
    abstract protected String toStringHelper();
    abstract public boolean equals(AbstractListNode ln);

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
    
    public String toString() {
    	return "()";
    }
    
    protected String toStringHelper() {
    	return " )";
    }

	public boolean equals(AbstractListNode ln) {
		return ln.isEmpty();
	}

}
