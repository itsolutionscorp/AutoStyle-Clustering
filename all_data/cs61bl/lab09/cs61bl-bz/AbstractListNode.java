abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public AbstractListNode get(int index);
    public String toString() {
    	AbstractListNode p = this;
    	String result = "( ";
    	while(p.size() > 0){
    		result = result + p.item() + " ";
    		p = p.next();
    	}
    	result = result + ")";
    	return result;
    }
    abstract public boolean equals(Object input);

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
    	if(next() instanceof EmptyListNode){
    		return 1;
    	}
    	
    	int size = 1 + next().size();
    	return size;
    }
    
    public AbstractListNode get(int index) {
    	if(index < 0){
    		throw new IllegalArgumentException("invalid input");
    	}
    	if(index == 0 ) {
    		return this;
    	}
    	return next().get(index - 1);
    }
    
    public boolean equals(Object input){
    	if(input instanceof AbstractListNode){
    		AbstractListNode p = (AbstractListNode) input;
    		AbstractListNode q = this;
    		if(q.size()== p.size()){
    			while(!(q.next().isEmpty())){
    				if(q.item() != p.item()){
    					return false;
    				}
    				q = q.next();
    				p = p.next();
    			}
    			return true;
    		}
    		return false;
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
    
    public int size(){
    	return 0;
    }
    
    public AbstractListNode get(int index) {
    	if(index != 0) {
    		throw new IllegalArgumentException("invalid input");
    	}
    	return this;
    }
    
    public boolean equals(Object input){
    	if(input instanceof EmptyListNode){
    		return true;
    	}
    	return false;
    }


}