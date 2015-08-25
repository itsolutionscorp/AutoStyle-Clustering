abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public AbstractListNode get(int pos);
    abstract public String toString();
    abstract public boolean equals (AbstractListNode other);
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
    	return 1 + myNext.size();
    }
    
    public AbstractListNode get(int pos) {
    	if (pos == 0) {
    		return this;
    	} else if (pos > 0 && size() > 1) { //(pos < size()) also works but doesn't take negative pos into account
    		return next().get(pos-1);
    	} else {
    		throw new IllegalArgumentException("That is not a valid position!");
    	}
    }
    
    public String toString() {
    	String s = "( ";
    	for (int i = 0; i < size(); i++) {
    		s = s + get(i).item() + " ";
    	}
    	s = s + ")";
    	return s;
    }
    
    public boolean equals (AbstractListNode other) {
    	if (size() != other.size()) {
    		return false;
    	}
    	for (int i = 0; i < size(); i++) {
    		if (!get(i).item().equals(other.get(i).item())) {
    			return false;
    		}
    	}
    	return true;
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
    
    public AbstractListNode get(int pos) {
    	throw new IllegalArgumentException("Contains no elements!");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals (AbstractListNode other) {
    	return (isEmpty() == other.isEmpty());
    }

}
