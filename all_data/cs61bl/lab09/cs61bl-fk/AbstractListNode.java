abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public boolean equals(Object obj);
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
    
    public Object get(int k) {
    	if (myNext == null) {
    		throw new IllegalArgumentException("out of range");
    	} else if (k == 0) {
    		return myItem;
    	} else {
    		return myNext.get(k-1);
    	}
    }
    
    public String toString() {
    	String temp = "( ";
    	AbstractListNode temp2 = this;
    	temp += item();
    	temp += " ";
    	while(!((temp2 = temp2.next()).isEmpty())) {
    		temp += temp2.item();
    		temp += " ";
    	}
    	temp += ")";
    	
    	return temp;
    }
    
    public boolean equals(Object list) {
    	if (this.size() != ((AbstractListNode) list).size()) {
    		return false;
    	} else if (this.item() != ((AbstractListNode) list).item()) {
    		return false;
    	} else {
    		return myNext.equals(((AbstractListNode) list).next());
    	}
    }
}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public int size() {
    	return 0;
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
    
    public AbstractListNode get(int pos) {
    	throw new IllegalArgumentException("No elements follow an EmptyListNode");
    }

    public String toString() {
    	return "( )";
    }

    public boolean equals(Object list) {
    	if (((AbstractListNode) list).isEmpty()) {
    		return true;
    	}
    	return false;
    }

}
