abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size(){
    	if (this.isEmpty()){
    		return 0;
    	}
    	return 1+this.next().size();
    }
    
    public Object get(int k){
    	if(k<0){
    		throw new IllegalArgumentException("Input positive number");
    	}
    	if(k==0){
    		return this.item();
    	}
    	if(this.next().isEmpty()){
    		throw new IllegalArgumentException("Out of bounds");
    	}
    	return this.next().get(k-1);
    }
    
    public String toString() {
    	String str = "( ";
    	AbstractListNode current = this;
    	while (!current.isEmpty()) {
    		str += current.item().toString() + " ";
    		current = current.next();
    	}
    	str += ")";
    	return str;
    }
    
    public boolean equals(Object R) {
    	if(R instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) R;
    		if (this.isEmpty() && node.isEmpty()) return true;
        	if (this.isEmpty() || node.isEmpty()) return false;
        	if (!this.item().equals(node.item())) return false;
        	return this.next().equals(node.next());
    	}
    	return false;
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

}
