abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    
    public int size(){
    	int size=0;
    	if (!this.isEmpty()){
    		size++;
    		size += this.next().size();
    	} else {
    		return 0;
    	}
    	return size;
    }
    
    public Object get(int index){
 	   AbstractListNode n = this;
 	   if(index < 0) throw new IllegalArgumentException("");
 	   for (int i=0; i<index; i++) {
 		   if(n.isEmpty()) throw new IllegalArgumentException("");
 		   n=n.next();
 	   }
 	   return n.item();
     }
    // Every other list-processing method goes here.
    
    public String toString(){
    	AbstractListNode n = this;
    	String s="";
    	while(!n.isEmpty()){
    		if (!n.next().isEmpty()){
    			s +=(String)n.item()+" ";
    		} else{
    			s +=(String)n.item();
    		}
    		n=n.next();
    	} 
    		return "("+s+")";
    	
    }
    
    public boolean equals(Object o2) {
    	AbstractListNode n2 = (AbstractListNode) o2;
    	
    	
    	if(isEmpty() && n2.isEmpty()) return true;
    	if(isEmpty() || n2.isEmpty()) return false;
    	
    	if(item().equals(n2.item())) {
    		return next().equals(n2.next());
    	} else {
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
