abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
	abstract public Object content();
	abstract public boolean equals(Object obj);
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
    	if(this.isEmpty()){
    		return 0;
    	}else{
    		return 1 + this.next().size();
    	}
    }
    
    public Object get(int pos){
    	if(pos < 0){
    		throw new IllegalArgumentException("Index out of bounds");
    	}else if(pos == 0){
    		return this.item();
    	}else{
    		if(this.next().isEmpty()){
    			throw new IllegalArgumentException("Index out of bounds");
    		}else{
    			return this.next().get(pos - 1);
    		}
    	}
    }
    
    public String content(){
    	if(this.next().isEmpty()){
    		return this.item() + "";
    	}else{
    		return this.item() + " " + this.next().content();
    	}
    }
    
    public String toString(){
    	return "( " + this.content() + " )";
    }

    public boolean equals(Object obj){
    	if(this.size() != ((AbstractListNode) obj).size()){
    		return false;
    	}else if(this.isEmpty() && ((AbstractListNode) obj).isEmpty()){
    		return true;
    	}else if(this.item() != ((AbstractListNode) obj).item()){
    		return false;
    	}else{
    		return this.next().equals(((AbstractListNode) obj).next());
    	}
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
    
    public Object get(int pos){
    	throw new IllegalArgumentException("No elements are stored in EmptyListNode.");
    }
    
    public String content(){
    	return "";
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean equals(Object obj){
    	if(((AbstractListNode) obj).isEmpty()){
    		return true;
    	}else{
    		return false;
    	}
    }
}
