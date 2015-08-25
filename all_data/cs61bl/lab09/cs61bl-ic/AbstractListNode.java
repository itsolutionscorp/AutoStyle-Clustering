abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int i);
    public String toString() {
    	return "( " + elements();
    }
    abstract protected String elements();
    abstract public boolean equals(AbstractListNode L);

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
    
    public Object get(int i) throws IllegalArgumentException {
    	if (i < 0) {
    		throw new IllegalArgumentException("Value to get is negative");
    	} else if (i == 0) {
    		return item();
    	} else {
    		return next().get(i-1);
    	}
    }

    public String elements() {
    	return item().toString() + " " + next().elements();
    }
    
    public boolean equals(AbstractListNode L) {
    	if (size() != L.size()) {
    		return false;
    	} else if (get(0) == L.get(0)){
    		return next().equals(L.next());
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
    
    public Object get(int i) throws IllegalArgumentException {
		throw new IllegalArgumentException("Value to get is longer than list length");
    }
    
    public String elements() {
    	return ")";
    }
    
    public boolean equals(AbstractListNode L) {
    	return L.isEmpty();
    }

}
