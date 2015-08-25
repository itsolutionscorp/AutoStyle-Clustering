abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object o);

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
    	return 1 + next().size();
    }
    
    public Object get(int pos){
    	if(pos<0 || (next().isEmpty() && pos!=1)){
    		throw new IllegalArgumentException("position out of range");
    	}
    	else if(pos==0){
    		return item();
    	}
    	else if(!next().isEmpty()){
    		return next().get(pos-1);
    	}
    	return null;
    }
    
    public String toString(){
    	//return " " + item() + " " + next().toString();
    	AbstractListNode node = this;
    	String s = "( ";
    	while(true){
    		if(node.size()==0){
    			break;
    		}
    		s = s + node.item() + " ";
    		node = node.next();
    	}
    	return s + ")";
    }
    
    public boolean equals(Object o){
    	if(o instanceof NonemptyListNode){
    		NonemptyListNode node = (NonemptyListNode)o;
    		if(item().equals(node.item())){
    			return next().equals(node.next());
    		}
    		else{
    			return false;
    		}
    	}
    	else if(o instanceof EmptyListNode){
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
    
    public Object get(int pos){
    	return null;
    }
    
    public String toString(){
    	return "()";
    }
    
    public boolean equals(Object o){
    	if(o instanceof EmptyListNode){
    		return true;
    	}
    	return false;
    }

}
