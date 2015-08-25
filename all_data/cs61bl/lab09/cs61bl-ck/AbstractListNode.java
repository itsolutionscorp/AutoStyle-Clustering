abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int idx);
    abstract public String toString();
    abstract public boolean equals(Object com);

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
		if (this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.next().size(); 
		}
	}
	
	public Object get(int idx) {
		int i = 0;
		AbstractListNode temp = this.next();
    	while (temp.next() != null) {
    		temp = temp.next();
    		if (i == idx) {
    			return temp.item();
    		}
    		i+=1;
    	}
    	if (idx > i) {
    		throw new IllegalArgumentException("out of bounds");
    	}
		return temp.item();
    }

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "";
		} else {
			return this.myItem + " " + this.next().toString();
		}
	}

	@Override
	public boolean equals(Object com) {
		// TODO Auto-generated method stub
		if (size() != ((AbstractListNode) com).size()) {
			return false;
		}
		return (((AbstractListNode) com).item() == item()) && this.next().equals(((AbstractListNode) com).next());
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

	@Override
	public Object get(int idx) {
		if (idx > 0) {
    		throw new IllegalArgumentException("out of bounds");
    	}
		return null;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public boolean equals(Object com) {
		if (com == null) {
			return true;
		}
		return false;
	}

}
