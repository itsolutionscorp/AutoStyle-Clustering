abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int i);
    abstract public String toString();
    abstract public boolean equals(Object other);

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
    	while (currentNode.isEmpty() == false) {
    		currentNode = currentNode.next();
    		count++;
    	} return count + 1;
    }
    
    public Object get(int i) {
    	int n = 0;
    	Object currentItem = this.myItem;
    	AbstractListNode currentNode = this;
    	while (i > n && currentNode.next().isEmpty() == false) {
    		if (currentNode.item() == null) {
    			throw new IllegalArgumentException("Position is out of range.");
    		}
    		currentNode = currentNode.next();
    		currentItem = currentNode.item();
    		n++;	
    	}
		return currentItem;
    }
    
    public String toString() {
    	String myString = "";
    	AbstractListNode currentNode = this;
    	while (currentNode.isEmpty() == false) {
    		myString = myString + currentNode.item() + " ";
    		currentNode = currentNode.next();
    	}
		return "( " + myString + ")";
    }
    
    public boolean equals(Object arg) {
    	AbstractListNode currentNode;
    	AbstractListNode argCurrentNode;
    	if (arg instanceof NonemptyListNode) {
    		NonemptyListNode node = (NonemptyListNode) arg;
    		if (this.size() == node.size()) {
    			currentNode = this;
    			argCurrentNode = node;
    			while (currentNode.isEmpty() == false) {
    				if (currentNode.item() == argCurrentNode.item()) {
    					currentNode = (AbstractListNode) currentNode.next();
    					argCurrentNode = (AbstractListNode) argCurrentNode.next();
    				} else
    					return false;
    			} return true;
    		} return false;
    	}
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
    	return 1;
    }
    
    public Object get(int i) {
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof EmptyListNode) {
    		return true;
    	} else
    		return false;
    }

}
