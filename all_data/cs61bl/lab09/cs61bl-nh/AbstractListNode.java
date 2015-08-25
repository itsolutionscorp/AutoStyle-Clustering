abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
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
		int count=1;
		NonemptyListNode temp = new NonemptyListNode(myItem, myNext);
		temp = this;

		while (!temp.next().isEmpty()) {
			count++;
			temp = (NonemptyListNode)temp.next();
		}
		return count;
	}
    public Object get(int pos) {
    	AbstractListNode A = this;

    	if (pos<0)
    		throw new IllegalArgumentException("Position is invalid");
    	while (pos>=0) {
    		if (pos==0) {
    			return A.item();
    		}
    		A = A.next();
    		
    		if (A.isEmpty()) {
    			throw new IllegalArgumentException("Position is invalid");
    		}
    		pos--;
    	}
    	return null;
    }

	public String toString() {
		AbstractListNode A = this;
		String endString = "( ";
		while (!A.isEmpty()) {
			endString = endString +  A.item() + " ";
			A = A.next();
		}
		endString = endString + ")";
		return endString;
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
		return "()";
	}

}
