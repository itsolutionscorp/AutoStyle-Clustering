abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode other);

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
    	if (myNext.isEmpty()) {
    		return 1;
    	} else {
    		return (1 + myNext.size());
    	}
    }
    
    public Object get(int index) {
    	if (index < 0) {
    		throw new IllegalArgumentException("List index out of bounds.");
    	}
    	if (index == 0) {
    		return myItem;
    	} else {
    		if (myNext.isEmpty()) {
    			throw new IllegalArgumentException("List index out of bounds.");
    		} else {
    		return myNext.get(index - 1);
    		}
    	}
    }
    
    public String toString() {
    	return "( " + this.toStringHelper() + ")";
    }
    
    public String toStringHelper() {
    	if (myNext.isEmpty()) {
    		return "" + myItem.toString() + " ";
    	} else {
    		return "" + myItem.toString() + " " + ((NonemptyListNode) myNext).toStringHelper();
    	}
    }
    
    public boolean equals(AbstractListNode other) {
    	if (other.isEmpty()) {
    		return false;
    	} else if (!myItem.equals(other.item())) {
    		return false;
    	} else {
    		return myNext.equals(other.next());
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
    public Object get(int index) {
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals (AbstractListNode other) {
    	return other.isEmpty();
    }
}
