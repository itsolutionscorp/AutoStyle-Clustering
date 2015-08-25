import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();

    // Every other list-processing method goes here.
    
    abstract public Comparable get(int posn);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode node);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode node);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setNext(AbstractListNode next);
    abstract public void setItem(Comparable item);
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if(a.isEmpty()) return b;
    	else if (b.isEmpty()) return a;
    	else if(a.item().compareTo(b.item()) <= 0){
    		a.setNext(merge(a.next(), b));    
    		return a;
    	}
    	else{
    		b.setNext(b.append(merge(b.next(), a)));  
    		return b;
    	}
    }
    
    

    
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  }
  	  return smallestHelper(item());
  	}

  	public Comparable smallestHelper(Comparable smallestSoFar) {
  	  Comparable smallest = AbstractListNode.min(smallestSoFar, item());
  	  while(!next().isEmpty()){
  		  smallest = next().smallestHelper(smallest);
  	  }
  	  return smallest;
  	}

  	public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
  	

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    private Comparable small;
    
    
    	
    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }
    

    public Comparable item() {
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
    public Comparable get(int posn){    				
		if (posn == 0)
			return item();  			
		else return next().get(posn - 1);
	} 
    public String toString(){
    	return "( " + toString(this) + ")";
    }
    public String toString(AbstractListNode n){  
    	if (n.isEmpty()) return "";
    	else return n.item() + " " + toString(n.next());
    }
    public boolean equals(AbstractListNode node){
    	if(size() == node.size() && item().equals(node.item()))    		
    		return next().equals(node.next());        	        	    	
    	else 
    		return false;
    }
    public AbstractListNode add (Comparable c){
    	return new NonemptyListNode(item(), next().add(c));
    }
    public AbstractListNode append(AbstractListNode node){
		return new NonemptyListNode(item(), next().append(node));
	}
    public AbstractListNode reverse(){
    	 int size = size();
    	 AbstractListNode n = new EmptyListNode();
    	 for(int i = size - 1; i >= 0; i--){
    		 n = n.add(this.get(i));
    	 }
    	 return n;  
    	
    }
    public void setNext(AbstractListNode next){
    	myNext = next;
    }
    public void setItem(Comparable item){
    	myItem = item;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	setNext(next().appendInPlace(list2));      				
    	return this;
    }




}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
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
    
	public Comparable get(int posn){
		throw new IllegalArgumentException("Cannot retrieve elements from an EmptyListNode.");
	}
	public String toString(){
		return "( )";
	}
	public boolean equals(AbstractListNode node){
		if(node.size() == 0) return true;
		else return false;
    }
	public AbstractListNode add (Comparable c){
    	return new NonemptyListNode(c, new EmptyListNode());
    }
	public AbstractListNode append(AbstractListNode node){
		if(node.isEmpty()) return node;					
		else return new NonemptyListNode(node.item(), node.next());
	}
	public AbstractListNode reverse(){
	   	 return new EmptyListNode();   	
   }
    public void setNext(AbstractListNode next){
    	throw new NoSuchElementException("Empty list has no next.");
    }
    public void setItem(Comparable item){
    	throw new NoSuchElementException("Empty list has no item.");
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
    }

	

}
