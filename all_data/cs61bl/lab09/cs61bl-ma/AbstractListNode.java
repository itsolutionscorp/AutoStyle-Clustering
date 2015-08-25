abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size(); 
    abstract public Object get(int pos); 
    abstract public String toString(); 
    abstract public boolean equals(Object o); 

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
    	return myNext.size() + 1; 
    }
    
    public Object get(int pos) {
    	if (pos == 0) {
    		return myItem; 
    	} else {
    		return myNext.get(pos-1); 
    	}
    }
    
    public String toString() {
    	String s = "( ";
    	while (!myNext.isEmpty()) {
    		s = s + item().toString(); 
    		myNext = myNext.next(); 
    	}
    	return s + " )";
    }
    
    public boolean equals(Object o) {
    	if (o instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) o;
    		if (node.isEmpty()) {
    			return false;
    		}
    		else if (node.item().equals(myItem)) {
    			return myNext.equals(node.next()); 
    		} else {
    			return false;
    		}
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
    
    public Object get(int pos) throws IllegalArgumentException {
		throw new IllegalArgumentException("Index out of range"); 
    }
    public String toString() {
    	return "()";
    }
    public boolean equals(Object o) {
    	if (o instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) o; 
    		if (node.isEmpty()) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
}
