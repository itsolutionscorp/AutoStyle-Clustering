import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object lst);
    abstract public AbstractListNode add (Comparable c);
    abstract public void setItem(Comparable c);
    abstract public void setNext(AbstractListNode lst);
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return next().smallestHelper(item());
    }

   	public Comparable smallestHelper(Comparable smallestSoFar) {
   	  if(isEmpty()) {
   		  return smallestSoFar;
   	  }
       	  return AbstractListNode.min(smallest(), smallestSoFar);
    }

    public static Comparable min(Comparable c1, Comparable c2) {
      if (c1.compareTo(c2) < 0) {
    	    return c1;
      } else {
    	    return c2;
      }
    }
    
    public AbstractListNode append(AbstractListNode list) {
        if (isEmpty()) {
        	return list;
        } else {
        	return new NonemptyListNode(item(), next().append(list));
        }
    }
    public AbstractListNode reverse() {
        if (isEmpty()) {
        	return this;
        } else {
        	return next().reverse().add(item());
        }
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
        if (isEmpty()) {
        	return list2;
        } else {
        	if(next().isEmpty()) {
        		setNext(list2);
        	} else {
        		next().appendInPlace(list2);
        	}
        	return this;
        }

    }
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if(a.isEmpty()) {
        	return b;
        } else if (b.isEmpty()) {
        	return a;
        } else {
        	if ((int)a.item()<(int)b.item()) {
        		a.setNext(merge(a.next(), b));
        		return a;
        	}else {
        		b.setNext(merge(a, b.next()));
        		return b;
        	}
        	
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
    
    @Override
    public int size() {
    	return 1+myNext.size();
    	
    }

	@Override
	 public Comparable get(int pos){
    	if (pos<0) {
    		throw new IllegalArgumentException("The position selected is out of bounds");
    	}
    	if (pos ==0){
    		return myItem;
    	}else{
    		if (myNext instanceof EmptyListNode) {
    			throw new IllegalArgumentException("The position selected is out of bounds");
    		}
    	    return myNext.get(pos-1);
    	}
    }
    public String toString () {
    	String r=new String();
        r="( "+myItem.toString();
   	    AbstractListNode other = myNext;
   	    while(other.toString()!="( )") {
   		    r = r +" "+other.item().toString();
   		    other = other.next();
   	    }
   	    return r+" )";
   }
    
    @Override
    public boolean equals(Object lst){
    	AbstractListNode list = (AbstractListNode)lst;
    	if (list.size()==0) {
    		return false;
    	}
    	if (this.item()!=list.item()){
    		return false;
    	}else{
    		return next().equals(list.next());
    	}
    }

	@Override
	public AbstractListNode add(Comparable c) {
		AbstractListNode n = new NonemptyListNode(item(), next().add(c));
		return n;
	}

	@Override
	public void setItem(Comparable c) {
		myItem = c;
	}

	@Override
	public void setNext(AbstractListNode lst) {
		myNext = lst;
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
    @Override
    public int size() {
    	return 0;
    }

	@Override
	public Comparable get(int pos) {
		throw new IllegalArgumentException("The position selected is out of bounds");
    }
	
	@Override
	public String toString(){
		return "( )";
	}

	@Override
	public void setItem(Comparable c) {
	}

	@Override
	public void setNext(AbstractListNode lst) {
	}

	@Override
	public boolean equals(Object lst) {
		if (((AbstractListNode)lst).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

} 


