import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public void setNext(AbstractListNode next);
    abstract public void setItem(Comparable c);

    	
    	
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
    
    public Comparable get(int index){
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
    	if(isEmpty()) return "( )";
    	AbstractListNode n = this;
    	String s="";
    	while(!n.isEmpty()){
    		if (!n.next().isEmpty()){
    			s +=String.valueOf(n.item())+" ";
    		} else{
    			s +=String.valueOf(n.item());
    		}
    		n=n.next();
    	} 
    	return "( "+s+" )";
    	
    }
    
    @Override
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
	
    public AbstractListNode add(Comparable c){  
    	if (isEmpty()){
    		return new NonemptyListNode(c, new EmptyListNode());
    	}
    	return new NonemptyListNode(item(), next().add(c));
    }
    
    
    public AbstractListNode append(AbstractListNode list){
		AbstractListNode n = this;
		AbstractListNode iter = list;

		while (!iter.isEmpty()){
    		n= n.add(iter.item());
    		
    		iter = iter.next();	
    	}
		return n;
    }
    public AbstractListNode reverse() {
    	if(isEmpty()) return this;
    	AbstractListNode result = new NonemptyListNode(item(), new EmptyListNode());
    	AbstractListNode c = this;
    	while(!c.next().isEmpty()) {
    		c = c.next();
    		result = new NonemptyListNode(c.item(), new EmptyListNode()).append(result);
    	}
    	return result;
    }
    
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
        if(isEmpty()) return list2;
        AbstractListNode n = this;
        while(!n.isEmpty()) {
        		System.out.println(n);
                if(n.next().isEmpty()) {
                        ((NonemptyListNode) n).setNext(list2);
                        return this;
                }

                n = n.next();
        }
        return this;
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b){
    	//if (a.isEmpty() && b.isEmpty()) return b;
    	if (a.isEmpty()) return b;
    	if (b.isEmpty()) return a;
    	
    	if (a.item().compareTo(b.item())<0){
    		a.setNext(merge(a.next(),b));
    	    return a;
    	}else {
    		b.setNext(merge(a,b.next()));
    		return b;
    	}
    }

    //------------------------------------
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item()) ;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		if(isEmpty()) return smallestSoFar;
    		Comparable i = min(smallestSoFar, item());
    		return this.next().smallestHelper(i);
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
    
    public boolean isEmpty() {
        return false;
    }
    
    public void setNext(AbstractListNode n) {
    	myNext = n;
    }
    
    public void setItem(Comparable c) {
    	myItem = c;
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
    
    public void setNext(AbstractListNode n) {
    	throw new IllegalArgumentException ("There is no next value stored in an EmptyListNode.");

    }
    
    public void setItem(Comparable c) {
    	throw new IllegalArgumentException ("There is no item stored in an EmptyListNode.");

    }
    
	public AbstractListNode add(Comparable i) {

		NonemptyListNode temp = new NonemptyListNode(i);
    	temp.setNext(this); 
    	return temp; 
    }
}


