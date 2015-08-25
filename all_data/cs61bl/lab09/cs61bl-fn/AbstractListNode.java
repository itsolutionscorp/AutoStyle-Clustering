abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    public int size () {
    	if ( !isEmpty() ) {
    		return 1 + next().size();
    	}
    	return 0;
    }
    public Object get(int pos) {
    	if ( pos < 0 && pos + 1 >= size() ) {
    		throw new IllegalArgumentException("invalid position");
    	} else if ( pos == 0) {
    		return item();
    	} else return next().get(pos-1);
    }
    public String toString() {
    	AbstractListNode test = this;
    	String toReturn = " )";
    	while (!test.isEmpty()) {
    		toReturn = " " + test.item() + toReturn;
    		test = test.next();
    	}
    	toReturn = "(" + toReturn;
    	return toReturn;
    }
    public boolean equals(AbstractListNode toCompare) {
    	if (this.size() != toCompare.size()) {
    		return false;
    	} else if (this.isEmpty() && toCompare.isEmpty()) {
    		return true;
    	} else if ( !this.item().equals(toCompare.item())) {
    		return false;
    	} else return this.next().equals(toCompare.next());
    }
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

}
