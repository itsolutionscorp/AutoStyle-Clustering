abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    public String toStringThing = "";
    abstract public boolean equals(Object lst);


}

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;
    public int length = 0;

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
    	length = this.next().size() + 1;
    	return length;
    }
    public Object get(int pos) {
    	if (pos > size() - 1) {
    		throw new IllegalArgumentException("out of bounds Element");
    	}
    	if (pos < 0) {
    		throw new IllegalArgumentException("cannot get negative element");
    	}
    	if (pos == 0) {
    		return this.item();
    	}
    	AbstractListNode x = this;
    	while (pos > 0) {
    		x = x.next();
    		pos--;
    	}
    	return x.item();
    	
    }
    public String toString() {
    	this.toStringThing += "( " + myItem + " " + next().toString().substring(2);
    	return toStringThing;
    }
    public boolean equals(Object lst) {
    	int equal = 0;
    	if (this.size() == ((AbstractListNode) lst).size()) {
    		for (int i = 0; i < this.size() - 1; i++) {
    			if (this.get(i) != ((AbstractListNode) lst).get(i)) {
    				return false;
    			}
    		} 
    		return true;	
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
    public Object get(int pos) {
    	throw new IllegalArgumentException ("Cannot Get from an EmptyListNode");
    }
    public String toString() {
    	this.toStringThing += "  )";
    	return toStringThing;
    }
    public boolean equals(Object lst) {
    	if (((AbstractListNode) lst).size() == 0) {
    		return true;
    	} else {
    		return false;	
    	}
    	
    }
}
