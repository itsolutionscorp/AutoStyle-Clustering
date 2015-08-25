abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    
    abstract int size();
    abstract Object get(int pos);
    public abstract String toString();
    public abstract boolean equals(Object arg);
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
    	} else if (next() == null) {
    		throw new IllegalArgumentException ("Position is out of range.");
    	} else {
    		return next().get(pos - 1);
    	}
    }
    
    public String toString() {
    	String left = next().toString().substring(0, 2);
    	String right = next().toString().substring(2);
    	return left + "" + item() + " " + right;
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof AbstractListNode) {
    		AbstractListNode casted = (AbstractListNode) arg;
    		if (casted.size() != this.size()) {
    			return false;
    		} else {
    			if (casted.item().equals(this.item())) {
    				return casted.next().equals(this.next());
    		}
    	} 
    } return false;
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
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object arg) {
    	AbstractListNode casted = (AbstractListNode) arg;
    	if (casted instanceof EmptyListNode) {
    		return true;
    	}
    	return false;
    }

}
