abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public String allItems();
    abstract public boolean equals(AbstractListNode l);
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

	@Override
	public int size() {
		return 1 + myNext.size();
	}
	
	@Override
	public Object get(int pos) {

		if (pos < 0 || (pos > size())){
    		throw new IllegalArgumentException();
    	} 
    	if (pos == 0) {
			return item();
		}else {
			return myNext.get(pos-1);
		}
    }

	public String allItems() {
		return item() + " " + myNext.allItems();
	}
	
	@Override
	public String toString() {
		return "( " + allItems() + ")";
	}

	@Override
	public boolean equals(AbstractListNode l) {
		return this.toString().equals(l.toString());
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

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object get(int pos) {
		return item();
	}

	@Override
	public String toString() {
		return "( )";
	}

	@Override
	public String allItems() {
		return "";
	}

	@Override
	public boolean equals(AbstractListNode l) {
		return this.toString().equals(l.toString());
	}
	
}
