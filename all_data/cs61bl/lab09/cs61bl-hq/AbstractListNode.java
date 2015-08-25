abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();    
    abstract public Object get(int position);
    abstract public String toString();
    abstract public int size();

    public boolean equals(Object obj) {
		String s1 = this.toString();
		String s2 = obj.toString();
		return s1.equals(s2);
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
    
    public int size() {
    	if (myNext.isEmpty()) {
			return 1;
		}
		return myNext.size() + 1;
    }
    
    public Object get(int position) {
    	int count = 0;
    	AbstractListNode nextNode = this;
    	while (count < position) {
    		if (nextNode instanceof EmptyListNode) {
    			nextNode.item();
    		}
    		nextNode = nextNode.next();
    		myItem = nextNode.item();
    		count++;
    	}
    	return myItem;
    }
    
    public String toString() {
    	String rep = "( ";
    	AbstractListNode nextNode = this;
    	while (!(nextNode.isEmpty())) {
    		rep += nextNode.item() + " ";
    		nextNode = nextNode.next();
    	}
    	rep += ")";
		return rep;
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
    
    public Object get(int position) {
    	return this.item();
    }

    public String toString() {
    	return "( )";
    }
}
