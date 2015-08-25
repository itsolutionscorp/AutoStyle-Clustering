abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size(){
    	AbstractListNode current = this;
    	int count = 1;
    	while (current.next() instanceof NonemptyListNode){
    		count++;
    		current = current.next();
    	}return count;
    }
    
    public Object get(int pos){
   	 AbstractListNode current = this;
   	 for(int i = 0; i < pos; i++){
   		 current = current.next();
   	 } return current.item();
   }
    
    public String toString(){
    	String s = "( ";
    	for(int i = 0;  i < this.size(); i++){
    		s += this.get(i) + " ";
    	} 
    	s += ")";
    	return s;
    }
    
    public boolean equals (Object o){
    	boolean same = true;
    	if(o instanceof AbstractListNode){
    		if (this.size() != ((AbstractListNode) o).size()){
    			return false;
    	    }
    		else {
    			for(int i = 0; i < this.size(); i++) {
    				if (this.get(i) != ((AbstractListNode) o).get(i)) {
    					same = false;
    				}
    			}
    		}
    	} 
		return same;
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
    
    public int size(){
    	return 0;
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
