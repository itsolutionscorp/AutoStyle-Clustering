package lab09;
abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size(){
    	int size=0;
    	if(!this.isEmpty()){
    		size++;
    		size+=this.next().size();	
    	}
    	else{
    		return 0;
    	}
    	return size;
    }
    
    public Object get(int pos){
    	
    	if( pos < 0 ){
    		throw new IllegalArgumentException("");
    	}
    	AbstractListNode n = this;
    	for(int i=0;i<pos;i++){
    		if(n==null)
    			throw new IllegalArgumentException("");
    		n.next();
    	}
    	return n.item();
    }
    public boolean equals(Object o2){
    	AbstractListNode n2=(AbstractListNode) o2;
    	if(isEmpty()&&n2.isEmpty()) return true;
    	if(isEmpty()||n2.isEmpty()) return false;
    	if(item().equals(n2.item())){
    		return next().equals(n2.next());
    	}else{
    		return false;
    	}
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
