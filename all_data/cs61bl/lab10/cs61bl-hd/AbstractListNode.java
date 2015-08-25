import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public void setItem(Comparable item);
    abstract public void setNext(AbstractListNode list);
    
    public AbstractListNode add (Comparable c){
    	NonemptyListNode result = new NonemptyListNode(c);
    	for (int k = 1; k <= this.size();k++){
    		NonemptyListNode l2 = new NonemptyListNode(this.get(this.size()-k), result);
    		result = l2;
    	}
    	return result;
    }
    
    public AbstractListNode append(AbstractListNode list) {
        AbstractListNode result = new EmptyListNode();
        for(int i = 0 ; i < this.size(); i++) {
        	result = result.add(this.get(i));
        }
        for(int i = 0; i < list.size(); i++) {
        	result = result.add(list.get(i));
        }
    	return result;
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode result = new EmptyListNode();
    	for(int i = this.size() - 1 ; i >= 0; i--) {
    		result = result.add(this.get(i));
    	}
    	return result;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if(this.isEmpty()) return list2;
    	AbstractListNode lastNonempty = this;
    	for(int i = 0; i < this.size() - 1 ; i++) {
    		lastNonempty = lastNonempty.next();
    	}
    	lastNonempty.setNext(list2);
    	return this;
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b ) {
        // Fill this out
    	AbstractListNode current;
    	AbstractListNode start;
    	if (min(a.get(0),b.get(0))==a.get(0)){
    		start = a;
    		current = a;
    		a = a.next();
    	} else {
    		start = b;
    		current = b;
    		b = b.next();
    	}
    	while(!(a.isEmpty() || b.isEmpty())){ 
    		
    		if (min(a.get(0),b.get(0))==b.get(0)){
    			current.setNext(b);
    			b = b.next();
    			current = current.next();
    		} else {
    			current.setNext(a);
    			a = a.next();
    			current = current.next();
    		}
    	}
    	if (a.isEmpty()){
    		current.setNext(b);
    	}
    	if (b.isEmpty()){
    		current.setNext(a);
    	}
    	return start;
    }
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return this.next().smallestHelper(item());
    	}
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	  if (this.isEmpty()){
    		  return smallestSoFar;
    	  } else {
    		  return this.next().smallestHelper(min(this.item(),smallestSoFar));
    	  }
    	}

    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	
    public int size(){
    	if (this.isEmpty()){
    		return 0;
    	}
    	return 1+this.next().size();
    }
    
    public Comparable get(int k){
    	if(k<0){
    		throw new IllegalArgumentException("Input positive number");
    	}
    	if(k==0){
    		return this.item();
    	}
    	if(this.next().isEmpty()){
    		throw new IllegalArgumentException("Out of bounds");
    	}
    	return this.next().get(k-1);
    }
    
    public String toString() {
    	String str = "( ";
    	AbstractListNode current = this;
    	while (!current.isEmpty()) {
    		str += current.item().toString() + " ";
    		current = current.next();
    	}
    	str += ")";
    	return str;
    }
    
    public boolean equals(Comparable R) {
    	if(R instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) R;
    		if (this.isEmpty() && node.isEmpty()) return true;
        	if (this.isEmpty() || node.isEmpty()) return false;
        	if (!this.item().equals(node.item())) return false;
        	return this.next().equals(node.next());
    	}
    	return false;
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
    
    public boolean isEmpty() {
        return false;
    }
   
    public void setItem(Comparable item) {
    	myItem = item;
    }
    
    public void setNext(AbstractListNode node) {
    	myNext = node;
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
    
    public void setItem(Comparable item) {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public void setNext(AbstractListNode list) {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }

}
