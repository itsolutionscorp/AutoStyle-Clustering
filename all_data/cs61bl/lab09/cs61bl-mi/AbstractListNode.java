abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int i);
    abstract public String toString();
    abstract public boolean equals(Object arg);

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
    
    public Object get(int i) {

    	if (i == 0) {
    		return myItem;
    	}
    	else if (i < 0){
    		throw new IllegalArgumentException("The index is out of bounds");
    	}
    	else {
    		return myNext.get(i - 1);
    	}
    }
    
    public String toString() {
//    	return "(" + myItem.toString() + 
    	String currString = "(";
    	for (int i = 0; i < size(); i++) {
    		currString += " " + get(i).toString();
    	}
    	currString += " )";
    	return currString;
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof NonemptyListNode) {
    		NonemptyListNode NEListNode = (NonemptyListNode) arg;
    		if (size() == NEListNode.size()) {
    			for (int i = 0; i < size(); i ++) {
    				if (get(i).equals(NEListNode.get(i)) == false) {
    					return false;
    				}
    			}
    			return true;
    		}
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
    	return 0;
    }

    public Object get(int i) {
    	throw new IllegalArgumentException("The index is out of bounds");
    }
    
    public String toString() {
    	return "()";
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof EmptyListNode) {
    			return true;
    	}
    	return false;
    }
    
    
}
