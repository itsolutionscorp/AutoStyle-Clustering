abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract int size();
    abstract public Object get(int index);
    abstract public String toString();
    abstract public String toString(String current);
    abstract boolean equals(AbstractListNode a);

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
    	return 1 + myNext.size();
    }
    
    public Object get(int index){
        if(index + 1 > this.size())
            throw new IllegalArgumentException ("Out of Range");
        else if (index == 0) {
            return myItem;
        }
        else {
            index = index - 1;
            AbstractListNode next = this.myNext;
            return next.get(index);
        }
    }
    
    public String toString() {
		return this.toString("");
	}
    
	public String toString(String current) {
		return myNext.toString(current + " " + myItem.toString());
	}
	
	public boolean equals(AbstractListNode a) {
		if (this.myItem.equals(a.item()))
			return myNext.equals(a.next());
		else
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
    
    public int size() {
    	return 0;
    }
    
    public Object get(int index) {
		throw new IllegalArgumentException("Out of Range");
	}
    
    public String toString() {
		return this.toString("");
	}
    
    public String toString(String current) {
		return "(" + current + " )";
	}
    
	public boolean equals(AbstractListNode a) {
		if (this.isEmpty() == a.isEmpty())
			return true;
		else
			return false;
	}
}
