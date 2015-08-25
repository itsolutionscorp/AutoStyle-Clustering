import java.util.NoSuchElementException;

abstract public class AbstractListNode{
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
   
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public String toStringHelper();
    abstract public boolean equals(Comparable a);
    abstract public AbstractListNode add(Comparable a);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public void setNext(AbstractListNode a);
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	NonemptyListNode result = null;
    	if (a.isEmpty()) return b;
    	else if (b.isEmpty()) return a;
    	if (a.item().compareTo(b.item()) < 0) {
    		result = (NonemptyListNode) a;
    		result.setNext(merge(a.next(),b));
    	} else {
    		result = (NonemptyListNode) b;
    		result.setNext(merge(a, b.next()));
    	}
    	return result;
    }
   
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (this.isEmpty()) return list2;
    	else if (this.next().isEmpty()) {
    		this.setNext(list2);
    		return this;
    	}
    	else return this.next().appendInPlace(list2);
    }
    
    
    public AbstractListNode reverse() {
    	AbstractListNode previousNode = new EmptyListNode();
    	AbstractListNode currentNode = this;
    	while(!currentNode.isEmpty()) {
    		NonemptyListNode nextNode = new NonemptyListNode(currentNode.item(), previousNode);
    		currentNode = currentNode.next();
    		previousNode = nextNode;
    	}
    	return previousNode;
    }

    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return this.smallestHelper(this.item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		if(this.isEmpty()){
    			return smallestSoFar;
    		}else{
    			return this.next().smallestHelper(min(smallestSoFar, this.item()));
    		}
    		
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
    
    public void setNext(AbstractListNode next){
    	this.myNext = next;
    }
    
    public void setItem(Comparable c) {
    	this.myItem = c;
    }
    
    public boolean isEmpty() {  
        return false;
    }
    
    public int size() {
    	return 1 + this.next().size();
    }
    public Comparable get(int pos) {
    	if (pos == 0) return this.item();
    	else return this.next().get(pos-1);
    }
    
    public String toStringHelper() {
    	return (this.item() + " " + this.next().toStringHelper());
    }
    
    public String toString() {
    	return "( " +this.toStringHelper();
    }

    public boolean equals(Comparable a) {
    	if (this.getClass() == a.getClass()){
    		NonemptyListNode a2 = (NonemptyListNode) a;
    		if(this.size() == a2.size()){
    			if(this.item() == a2.item()){
    				return this.next().equals(a2.next());
    			}else{
    				return false;
    			}  		
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
    }
    
    public AbstractListNode add (Comparable c){
    	return new NonemptyListNode(this.item(), this.next().add(c)); 		
    }
    
    
    public AbstractListNode append(AbstractListNode list){
    	return new NonemptyListNode(this.item(), this.next().append(list));
    }
   
  
}

class EmptyListNode extends AbstractListNode{
    
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

    public int size() {
    	return 0;
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) return null;
    	else throw new IllegalArgumentException("List does not go that long");
    }
    public String toStringHelper() {
    	return (")");
    }
    
    public String toString() {
    	return ("()");
    }
    
    public boolean equals(Comparable a) {
    	if(this.getClass() == a.getClass()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public AbstractListNode add(Comparable a){
    	NonemptyListNode n = new NonemptyListNode (a, this);
    	return n;
    }
    
    public AbstractListNode append(AbstractListNode list){
    	return list;
    }


	public void setNext(AbstractListNode a) {
		// having it here since I need one but it will not do anything
	}

}
