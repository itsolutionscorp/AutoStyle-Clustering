abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();


    // Every other list-processing method goes here.
    abstract public String nodeStringing();
    abstract public int size();
    abstract public Object get(int k);
    public String toString() {
    	return "(" + nodeStringing() + ")";
    }
    abstract public boolean equals(AbstractListNode p);
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
    
    public Object get(int k) {
    	if (k > 0) {
    	return myNext.get(k-1);
    	} 
    	else if (k == 0){
    		return myItem;
    	} else {
    		throw new IllegalArgumentException();
    	}
    }
    
    public boolean equals(AbstractListNode p) {
    	return myItem.equals(p.item()) && myNext.equals(p.next());
    }
    
    public String nodeStringing() {
    	 return " " + myItem + myNext.nodeStringing();
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

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Object get(int k) {
    	if (k == 0) {
    		return this;
    	} else {
    	throw new IllegalArgumentException();
    	}
    }
    
    public boolean equals(AbstractListNode p) {
    	return p.isEmpty();
    }
    public String nodeStringing() {
    	return " ";
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

}
