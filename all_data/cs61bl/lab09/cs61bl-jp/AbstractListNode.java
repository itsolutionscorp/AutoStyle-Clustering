abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    abstract public int size();
    abstract public AbstractListNode get(int i);
    abstract public String toString();
    abstract public boolean equals(Object arg);
    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;
    private int length = 1;

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
    	if (this.next().isEmpty() == false) {
    		return this.length + next().size();
    	}
    	return this.length;
    }
    
    public NonemptyListNode get(int pos) {
    	if (pos < 0) {
    		throw new IllegalArgumentException("Position is out of range421421414321");
    	}
    	if (pos == 0) {
    		return this;
    	} else {
    		if (next().isEmpty()) {
    			throw new IllegalArgumentException("Position is out of rangeGGGGg");
    		} else {
    			return (NonemptyListNode) next().get(pos - 1);
    		}
    	}
    }
    
    public String toString() {
    	String ret = "( ";
    	for (int i = 0; i < size(); i++) {
    		ret += get(i).myItem + " ";
    	}
    	ret += ")";
    	return ret;
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof NonemptyListNode) {
    		NonemptyListNode listnode = (NonemptyListNode) arg;
    		return this.toString().equals(listnode.toString());
    	}
    	return false;
    }

}

class EmptyListNode extends AbstractListNode {
    
	private int length = 0;
	
    public EmptyListNode() {
        
    }
    
    public boolean equals (Object arg) {
    	if (arg instanceof EmptyListNode) {
    		EmptyListNode listnode = (EmptyListNode) arg;
    		return this.toString().equals(listnode.toString());
    	}
    	return false;
    }
    
    public int size() {
    	return length;
    }
    
    public String toString() {
    	return "()";
    }
    
    public EmptyListNode get(int pos) {
    	throw new IllegalArgumentException ("There is not item at this position");
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
