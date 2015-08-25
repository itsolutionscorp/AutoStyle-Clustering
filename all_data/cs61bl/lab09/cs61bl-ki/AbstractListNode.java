abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int n);
    abstract public String toString();
    abstract public boolean equals(Object arg);
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
    
    public int size(){
    	return 1 + myNext.size();
    }
    
    public Object get(int n){
    	if (n==0){
    		return this.myItem;
    	}
    	if (!this.myNext.isEmpty()){
    		return this.myNext.get(n-1);    		
    	} else {
    		throw new IllegalArgumentException("get out of range");
    	}
    }
    
    public boolean equals(Object arg){
    	if (arg instanceof AbstractListNode){
    		AbstractListNode node = (AbstractListNode) arg;
    		if (this.myItem.equals(node.item())){
    			return this.myNext.equals(node.next());
    		}
    	}
    	return false;
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
    
    public String toString(){
    	String rtn = "(";
    	AbstractListNode current = this;
    	while (!current.isEmpty()){
    		rtn += " " + current.item().toString(); 
    		current = current.next();
    	}
    	return rtn + " )";
    }

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public int size(){
    	return 0;
    }
    
    public Object get(int n){
    	return null;
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
    
    public boolean equals(Object arg){
    	if (arg instanceof EmptyListNode){
    		return true;
    	}
    	return false;
    };
    
    public String toString(){
    	return "( )";
    }

}
