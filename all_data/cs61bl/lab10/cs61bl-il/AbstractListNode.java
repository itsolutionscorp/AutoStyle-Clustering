import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object o);
    
    public static Comparable min(Comparable c1, Comparable c2) {
		  if (c1.compareTo(c2) < 0) {
		    return c1;
		  } else {
		    return c2;
		  }
		}
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (!this.next().isEmpty()){
    		return min(smallestSoFar, this.next().smallest());
    	}
  	  return this.item();
  	}
    public Comparable smallest() {
		  if (isEmpty()) {
		    throw new NoSuchElementException("Can't find smallest in empty list.");
		  }
		  return smallestHelper(this.item()) ;
		}
    public AbstractListNode add (Comparable c){  	
        return null;
    }
    public AbstractListNode append(AbstractListNode list) { 	
        return null;
    }
    public AbstractListNode reverse() {
    	return null;
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return null;
    }
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if(a.isEmpty()) {
    		return b;
    	} else if (b.isEmpty()) {
    		return a;
    	}
    	
    	NonemptyListNode temp = (NonemptyListNode) a;
    	AbstractListNode p = temp;
    	
    	boolean first = true;
    	
        while(!a.isEmpty() && !b.isEmpty()) {
        	if (first) {
	        	if (a.item().compareTo(b.item()) < 0) {
	        			temp = (NonemptyListNode) a; 
	        			a = a.next();
	        		
	        		} else {
	        			temp = (NonemptyListNode) b;
	        			b = b.next();		
	        		}
	        	first = false;
	        	p = temp;
	        	
        	} else {
        		if (a.item().compareTo(b.item()) < 0) {
        			temp.setNext(a);                         
        			a = a.next();
    
        		} else {
        			temp.setNext(b);
        			b = b.next();
        		}
        		temp = (NonemptyListNode) temp.next();	
        		
        	}     	
        	} 
        if (a.isEmpty()) {
        	temp.setNext(b);
        } else {
        	temp.setNext(a);
        }
        return p;
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
    public void setNext(AbstractListNode a) {
    	this.myNext = a;
    }
    public void setItem(Comparable b) {
    	this.myItem = b;
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
		int s=1;
		NonemptyListNode n = this;
		while(n.myNext instanceof NonemptyListNode){
			n = (NonemptyListNode) n.myNext;
			s++;
		}
		return s;
	}

	@Override
	public Comparable get(int pos) {
		if (pos==0){
    		return myItem;    		
    	}else{
    		AbstractListNode current=this;
    		Comparable item=null;
    		int i=0;  		
    		while( i < pos){
    			if(pos<0 || this.next()==null){
    				throw new IllegalArgumentException("Invalid position.");
    			}
    			current = current.next();
    			item = current.item();
    			i++;
    		}
    		return item;
    	}		

	}

	@Override
	public String toString() {
		String s = "( " + this.myItem;
		
		NonemptyListNode n = this;
		while(n.myNext instanceof NonemptyListNode){
			n = (NonemptyListNode) n.myNext;
			s = s + " " + n.myItem;
		}
		return s + " )";
	}

	@Override
	public boolean equals(Object o) {
		
		if (o instanceof NonemptyListNode){
			
			NonemptyListNode n = (NonemptyListNode) o;
			NonemptyListNode t = this;
			if (t.myItem==n.myItem){
				while(t.myNext instanceof NonemptyListNode && n.myNext instanceof NonemptyListNode){
					t = (NonemptyListNode) t.myNext;
					n = (NonemptyListNode) n.myNext;
					t.myNext.equals(n);
				}
				return true;
			}				
		}
		return false;
	}
	@Override
	public AbstractListNode add (Comparable c){
		NonemptyListNode copyhead = new NonemptyListNode(myItem);////needed?////
		NonemptyListNode copy = copyhead;
		NonemptyListNode n = this;
		while(!n.next().isEmpty()){
			copy.myNext = new NonemptyListNode(n.next().item());
			n = (NonemptyListNode) n.next();
			copy = (NonemptyListNode) copy.next();
		}
		
    	copy.myNext = new NonemptyListNode(c);
    	
    	return copyhead;
		
	}

	public AbstractListNode append(AbstractListNode list) {
		NonemptyListNode copyhead = new NonemptyListNode(myItem);////needed?////
		NonemptyListNode copy = copyhead;
		NonemptyListNode n = this;
		while(!n.next().isEmpty()){
			copy.myNext = new NonemptyListNode(n.next().item());
			n = (NonemptyListNode) n.next();
			copy = (NonemptyListNode) copy.next();
		}
		
    	copy.myNext = list;
    	
    	return copyhead;
    	/////////////////////////////////this is probably too slow////////////////
		/*if (list instanceof EmptyListNode){
			return this;
		}
		
		AbstractListNode listcopy = list;
		AbstractListNode result = this.add(listcopy.item());
		while(!listcopy.next().isEmpty()){
			listcopy = listcopy.next();
			result = result.add(listcopy.item());
		}
		return result;*/
	}
	public AbstractListNode reverse() {
		NonemptyListNode counter = this;
		NonemptyListNode rev = new NonemptyListNode(counter.get(counter.size()-1));
		NonemptyListNode revHead = rev;
		
		for (int i = counter.size() - 2; i >= 0; i --) {
			rev.myNext = new NonemptyListNode(counter.get(i));
			rev = (NonemptyListNode) rev.next();
		}
		
		return revHead;
		
	}
	public AbstractListNode appendInPlace(AbstractListNode list2){
		NonemptyListNode iter = this;
		while(!iter.next().isEmpty()){
			iter = (NonemptyListNode) iter.next();
		}
		iter.myNext = list2;
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

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparable get(int pos) {
		this.item();
		return null;
	}

	@Override
	public String toString() {
	
		return "( )";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof EmptyListNode){
			return true;
			}
		return false;
	}
	@Override
	public AbstractListNode add (Comparable c){
		NonemptyListNode copy = new NonemptyListNode(c);
		return copy;
	}
	public AbstractListNode append(AbstractListNode list) {
		return list;
	}
	public AbstractListNode reverse() {
		return this;
	}
	public AbstractListNode appendInPlace(AbstractListNode list2){
		return list2;
	}

}
