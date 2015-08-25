import java.util.ArrayList;
import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size() {
    		if (this.isEmpty()) {
    			return 0;
    		} else {
    			return 1 + next().size();
    		}
    }
    
    public Comparable get (int i) {
		if (i < 0) {
			throw new IllegalArgumentException();
		}
		if (i == 0) {
			return item();
		}
		
		if (next().isEmpty()){
			if (i == 0) {
				return item();
			}
			throw new IllegalArgumentException();
		
		} else {
			return next().get(i - 1);
		}
}
    
    public String toString() {
    		String init = "( ";
    		if(isEmpty()){
    			return "( )";
    		}
    		else{
    			AbstractListNode nextItem=this;
    			while (nextItem.isEmpty() == false) {
    				init = init + nextItem.item() + " ";
    				nextItem=nextItem.next();
    			}
    			return init + ")";
    		}
    }
    
    public boolean equals(AbstractListNode l) {
    		if (size() != l.size()) {
    			return false;
    		
    		} else if (isEmpty() && l.isEmpty()) {
    			return true;
    		} else {
    			if (item().equals(l.item())) {
    				return next().equals(l.next());
    			} else {
    				return false;
    			}
    		}
    }
    
    public Comparable smallest() {
    	  	if (isEmpty()) {
    		  throw new NoSuchElementException("Can't find smallest in empty list.");
    	  	}
    	  	return smallestHelper(item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		if (isEmpty()) {
    			return smallestSoFar;
    		} else {
    			return next().smallestHelper(min(item(), smallestSoFar));
    		}
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    		if (c1.compareTo(c2) < 0) {
    			return c1;
    		} else {
    			return c2;
    		}
    	}
    	
    	public abstract AbstractListNode add (Comparable c);
    	public abstract AbstractListNode append (AbstractListNode list);
    	public abstract AbstractListNode reverse();
    	public abstract AbstractListNode setNext(AbstractListNode a);
    public abstract AbstractListNode copy(); 
    public abstract AbstractListNode appendInPlace (AbstractListNode list2);
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()) {
        		return b;
        } else if (b.isEmpty()) {
        		return a;
        } else {
        		if (a.item().compareTo(b.item()) < 0) {
        			AbstractListNode ref = a.next();

        			return a.setNext(merge(b, ref));
        		} else {
        			AbstractListNode ref = b.next();

        			return b.setNext(merge(a, ref));
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
    
    public NonemptyListNode copy() {
			return new NonemptyListNode(item(), next().copy());
		}

    
	public NonemptyListNode add (Comparable c){
		NonemptyListNode result = copy();
		NonemptyListNode ref = result;
		while (ref.next().isEmpty() == false){
			ref = (NonemptyListNode)ref.next();
		}
		ref.myNext = new NonemptyListNode(c, new EmptyListNode());
		return result;
		
    }
	
	public AbstractListNode append(AbstractListNode list) {
		NonemptyListNode result = copy();
		NonemptyListNode ref = result;
		while (ref.next().isEmpty() == false){
			ref = (NonemptyListNode)ref.next();
		}
        ref.myNext = list;  
        return result;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
	    if (list2.isEmpty() == false) {
	    		NonemptyListNode ref = this;
	    		while (ref.next().isEmpty() == false) {
	    			ref = (NonemptyListNode) ref.next();
	    		}
	    		ref.myNext = list2;
	    		return this; // SHOULD I RETURN THIS
	    } else {
	    		return this;
	    }
	}
	
	public static AbstractListNode helper(ArrayList<Comparable> q) {
		if (q.size() == 0) {
			return new EmptyListNode();
		}
		Comparable temp = q.remove(0);
		return new NonemptyListNode(temp, helper(q));	
	}
	
	public NonemptyListNode reverse() {
		NonemptyListNode ref = this;
		if (ref.next().isEmpty()){
			return new NonemptyListNode(item(), next());
		} else {
			ArrayList<Comparable> copout = new ArrayList<Comparable>();
			
			while (ref.next().isEmpty() == false) {
				copout.add(ref.item());
				ref = (NonemptyListNode)ref.next();
			}
			
			copout.add(ref.item());
			
			for(int i = 0, j = copout.size() - 1; i < j; i++) {
		        copout.add(i, copout.remove(j));
		    }
			
			return (NonemptyListNode) helper(copout);
		}
	}
	public AbstractListNode setNext(AbstractListNode a){
		myNext=a;
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
    
	public AbstractListNode add (Comparable c){
        return new NonemptyListNode(c, new EmptyListNode());
    }
	
	public AbstractListNode append (AbstractListNode list) {
		return list;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2){
	    return list2;
	}
	
    public AbstractListNode copy() {
		return new EmptyListNode();
	}
    
	public EmptyListNode reverse() {
		return new EmptyListNode();
	}
	public AbstractListNode setNext(AbstractListNode a){
		throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
	}
}


