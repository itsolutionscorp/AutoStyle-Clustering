abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object list);
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

	@Override
	public int size() {
		if (!this.isEmpty())
			return 1 + this.myNext.size();
		return 0;
	}
	
	@Override
    public Object get(int pos) throws IllegalArgumentException{
    	if (pos < 0 || pos > this.size() - 1){
    		throw new IllegalArgumentException ("Enter a valid position.");
    	}
    	while (pos > 0){
    		return this.myNext.get(pos-1);
    	}
    	return this.myItem;
    }

	@Override
	public String toString() {
		String s = "( ";
		for (int i = this.size()-1; i >= 0; i--){
			s += get(i) + " ";
		}
		return s + ")";
	}

	@Override
	public boolean equals(Object list) {
		if (((AbstractListNode) list).size() != this.size())
			return false;
		for (int i = 0; i < this.size(); i++){
			if (((AbstractListNode) list).get(i) != this.get(i)){
				return false;
			}
		}
		return true;
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
		return null;
	}

	@Override
	public String toString() {
		return "( )";
	}

	@Override
	public boolean equals(Object list) {
		if (((AbstractListNode) list).isEmpty())
			return true;
		return false;
	}
	
	

}
