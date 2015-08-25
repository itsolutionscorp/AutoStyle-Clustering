abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    public String toString() {
    	String result = "( ";
    	AbstractListNode currentNode = this;
    	while (!currentNode.isEmpty()) {
    		result += currentNode.item().toString() + " ";
    		currentNode = currentNode.next();
    	}
    	result += ")";
    	return result;
    }
    abstract public boolean equals(Object x);
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
    	int count = 0;
    	AbstractListNode currentNode = this;
    	while (!currentNode.isEmpty()) {
    		currentNode = currentNode.next();
    		count++;
    	}
    	return count;
    }
    
    public Object get(int pos) {
    	if (pos < 0) {
    		throw new IllegalArgumentException("Input is negative");
    	}
    	AbstractListNode currentNode = this;
    	for (int i = pos; i > 0; i --) {
    		currentNode = currentNode.next();
    	}
    	return currentNode.item();
    }
    
    public boolean equals(Object x) {
    	if (x instanceof NonemptyListNode) {
    		if (((NonemptyListNode) x).size() == this.size()) {
    			for (int i = 0; i < size(); i++) {
    				if (!get(i).equals(((NonemptyListNode) x).get(i))) {
    					return false;
    				}
    			}
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
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
    	throw new IllegalArgumentException("Empty List");
    }
    
    public boolean equals(Object x) {
    	return (x instanceof EmptyListNode);
    }
}
