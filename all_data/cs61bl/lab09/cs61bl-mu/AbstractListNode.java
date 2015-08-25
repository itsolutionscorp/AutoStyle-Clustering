abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int a);
    abstract public String toString();
    abstract public boolean equals(Object w);

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
    	AbstractListNode node= this;
    	int i = 0;
    	while(node.isEmpty() == false){
    		node = node.next();
    		i++;
    	}
    	return i;
    }
    
    
    public Object get(int a){
    	AbstractListNode node = this;
    	int i = 0;
    	if(a <0){
    		throw new IllegalArgumentException("Wrong argument");
    	}
    	while(a > i){
    		if(node.next()!=null){
    		node = (NonemptyListNode)node.next();
    		}else{
    			throw new IllegalArgumentException("Argument out of bounds");
    		}
    		i++;
    	}
    	return node.item();
    }
    
    public String toString(){
    	AbstractListNode node = this;
    	String a = "(";
    	Object it;
    	while(node.isEmpty() == false){
    		
    		it = node.item();
    		node = node.next();
    		a = a+" "+it;
    	}
    	a = a+" )";
    	return a;
    }
    
   public boolean equals(Object w){
	   if(w instanceof AbstractListNode){
		   AbstractListNode nodes = (AbstractListNode) w;
		   AbstractListNode node = this;
		   if( node.size() == nodes.size()){
			   while(node.next().isEmpty() == false){
				   if(node.item() != nodes.item()){
					   return false;
				   }
				   node = node.next();
				   nodes = nodes.next();
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
    public Object get(int a){
    	return null;
    }
    
    public String toString(){
    	String a = "()";
    	
    	return a;
    }
    
    public boolean equals(Object w){
    	if(w instanceof AbstractListNode){
 		   AbstractListNode nodes = (AbstractListNode) w;
 		   if(nodes.isEmpty() == true){
 			   return true;
 		   }
 		   
    }
 return false;   	
}
}

