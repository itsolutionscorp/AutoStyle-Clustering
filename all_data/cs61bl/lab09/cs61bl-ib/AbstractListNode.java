abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public Object get();
    abstract public int size();
    abstract public String toString();
    abstract public String toString(String current);
    
    
    

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
    	return this.next().size() + 1;
    }
    
    /*  public String toString(){
    	String current = "" + this.item();
    	return current + this.next().toString();
    }*/
    
    /*public String toString(){
    	return this.toString("");
    }
    public String toString(String current){
        return this.next().toString(current + " " + this.item().toString() );
    }*/
    
    /*public String toString(){
    	String s = "(";
    	String t = ")";
    	String values = "";
    	while(this.next() != null){
    		Object x = this.item();
    		values += x;
    	}
    	return s + values + t;
    }*/
    
    public String toString(){
    	String s = "(";
    	String t = ")";
    	AbstractListNode n = this;
    	while(n.next().isEmpty() == false){
    		 s = s + this.item() + " ";
    		 n = n.next();
    	}
    	return s + t;
    }
    
    public boolean equals(Object n){
    	if(this.toString() == n.toString()){
    		return true;
    	}
    	return false;
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
    
    /* public String toString(){
    	return "(" + current + ")";
    }*/
    
    public String toString(){
    	return this.toString("");
    }
    public String toString(String current){
        return "(" + current + ")";
    }
    
    /*public boolean equals(Object n){
    	if(this.toString() == n.toString()){
    		return true;
    	}
    	return false;
}*/
}
}
