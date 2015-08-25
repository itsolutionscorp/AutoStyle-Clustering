abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode compare);

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
    	if (myNext.size() == 0) {
    		return 1;
    	} else {
    		return 1 + myNext.size();
    	}
    }
    
    public Object get(int pos) {
    	if (pos == 0) {
    		return myItem;
    	}
    	if (pos < 0 || (myNext.get(pos-1) == null && pos > 0)) {
    		throw new IllegalArgumentException("Position is out of range.");
    	} else {
    		return myNext.get(pos-1);
    	}
    }
    
    public String toString() {
    	if (myNext.toString().equals("()")) {
    		return "( " + myItem + " )";
    	} else {
    		String the_string = "( ";
    		for (int i = 0; i < size(); i++) {
    			the_string += get(i) + " ";
    		}
    		return the_string + ")";
    	}
    }
    
    public boolean equals(AbstractListNode compare) {
    	if (size() != compare.size()) {
    		return false;
    	} else {
    		for (int i = 0; i < size(); i++) {
    			if (!get(i).equals(compare.get(i))) {
    				return false;
    			}
    		}
    		return true;
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
    	return null;
    }
    
    public String toString() {
    	return "()";
    }
    
    public boolean equals(AbstractListNode compare) {
    	if (compare.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
