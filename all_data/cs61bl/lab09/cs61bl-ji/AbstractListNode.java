abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int x);
    abstract public String toString();
    //abstract public boolean equals(Object arg);
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
    
    public int size(){
    	//int Count = 1;
    	return 1 + this.next().size();
    	//return Count;
    }
    
    public Object get(int x) throws IllegalArgumentException{
    	if (x>this.size()) {
    		throw new IllegalArgumentException("wrong");
    	}
    	
    	AbstractListNode currentNode = this;
    	for (int i =0; i<=x; i++){
    		if (i !=x ){
    			currentNode = currentNode.next();
    		}
    		else {
    			return currentNode.item();
    		}
    	}
        return currentNode.item();
    }
    
    public String toString() {
    	String newString = "( " + this.item() + " ";
    	AbstractListNode current = this.next();
    	for (int i=1; i<size(); i++) {
    		newString += current.item() + " ";
    		current = current.next();
    	}
    	return newString + ")";
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) arg;
    		if (this.size() == node.size()) {
    			AbstractListNode current = this;
    			while (!current.isEmpty()) {
    				if (current.item() != node.item()) {
    					return false;
    				} 
    			
    				current = current.next();
    				node = node.next();
    			}
    		} else {
    			return false;
    		}
    	} else {
    		return false;
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
    public int size(){
    	return 0;
    }
    
    public Object get(int x) {
    	return null;
    	
    }

    public String toString() {
    	return "";
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) arg;
    		if (this.size() == node.size() && node.size() ==0) {
    			return true;
    		}
    	}
    	return false;
    }
}
