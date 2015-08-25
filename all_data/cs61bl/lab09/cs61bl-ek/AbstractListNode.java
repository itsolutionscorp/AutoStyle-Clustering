abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

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
    
    public int size(AbstractListNode s){
    	int total = 0;
//    	NonemptyListNode next  = (NonemptyListNode) this.next();
    	 if (s.isEmpty()){
    		 return total;
    		 }
    	 
    	return 1 + size(s.next());	 
    	 }
    
    public  String toString (AbstractListNode s){
    	
    	String words = "( ";
    	while(s.isEmpty() == false){
    		words += s.item().toString() + " ";
    		s = s.next();
    	}
    	return words + ")";

    }

//    public boolean equals(){
//    	
//    }
//    
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
    	throw new IllegalArgumentException ("nothing has no size");
    	}
	}


