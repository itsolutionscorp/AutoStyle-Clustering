import java.util.ArrayList;
import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    
    // Every other list-processing method goes here.

    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        // Fill this out
    	AbstractListNode previous = null;
    	AbstractListNode head = a;
    	AbstractListNode curr_b = b;
    	AbstractListNode bnext = curr_b.next();

    	while (!curr_b.isEmpty()) {
    		if (!a.isEmpty() && a.item().compareTo(b.item()) < 0) { 
    			previous = a;
    			a = a.next();
    		} else {
    			bnext = curr_b.next();
    			((NonemptyListNode) previous).setMyNext(curr_b);
    			((NonemptyListNode) previous.next()).setMyNext(a);
    			previous = previous.next();
    			curr_b = bnext;
    		}
    	}
    	return head;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	AbstractListNode current = this; 
    	if (!this.isEmpty()) {
    		while (!current.next().isEmpty()) {
    			current = current.next();
    		}
    		((NonemptyListNode) current).setMyNext(list2); 
    		return current;
    	} else {
    		return list2;
    	}
    }
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
	  while (next() != null) {
		  smallestSoFar = min(smallestSoFar, next().item());
	  }
	  return smallestSoFar;
	}

	public static Comparable min(Comparable c1, Comparable c2) {
	  if (c1.compareTo(c2) < 0) {
	    return c1;
	  } else {
	    return c2;
	  }
	}
	
	public String toString(){
		AbstractListNode s = this;
    	String words = "( ";
    	while(s.isEmpty() == false) {
    		words += s.item().toString() + " ";
    		s = s.next();
    	}
    	return words + ")";
    }

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public AbstractListNode reverse() {
        ArrayList<Comparable> l = new ArrayList<Comparable>();
        AbstractListNode current = this; 
        while (!current.isEmpty()) {
        	l.add(current.item());
        	current = current.next();
        }
        int c = l.size();
        NonemptyListNode n = new NonemptyListNode(l.get(c-1));
        for (int i=c-2; i>=0; i--) {
        	n = (NonemptyListNode) n.add(l.get(i));
        }
        return n;
    }
    
	public AbstractListNode append(AbstractListNode list) {
		NonemptyListNode l = new NonemptyListNode(this.item());
	    AbstractListNode head = l;
	    AbstractListNode current = this;
	    while (!current.next().isEmpty()) {
	    	l.setMyNext(new NonemptyListNode(next().item()));
	    	current = current.next();
        	l = (NonemptyListNode) l.myNext;
	    } 
	    while (!list.isEmpty()) {
	    	l.setMyNext(new NonemptyListNode(list.item()));
	    	list = list.next();
	    	l = (NonemptyListNode) l.myNext;
	    }
	    return head;
	}
    
    public AbstractListNode add(Comparable c){
        NonemptyListNode l = new NonemptyListNode(item());
        AbstractListNode head = l;
        AbstractListNode current = this;
        while (!current.next().isEmpty()) {
        	l.setMyNext(new NonemptyListNode(next().item()));
        	current = current.next();
        	l = (NonemptyListNode) l.myNext;
        }
        l.myNext = new NonemptyListNode(c);
        return head;
    }
    
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
        return getMyNext();
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size(AbstractListNode s){
    	int total = 0;
    	 if (s.isEmpty()){
    		 return total;
    		 }
    	return 1 + size(s.next());	 
    	 }
    
    

    public boolean equals(Comparable obj){
    	
    	AbstractListNode node = (AbstractListNode) obj;
    	AbstractListNode x = this;
    	AbstractListNode y = node;
    	if (this.isEmpty() && node.isEmpty()){
    		return true;
    	}
    	while(!x.isEmpty()){
    		if(x.item().equals(node.item())){
    			x = x.next();
    			node = node.next();
    		}else{
    			return false;
    		}
    	}return true;
    }

	public AbstractListNode getMyNext() {
		return myNext;
	}

	public void setMyNext(AbstractListNode myNext) {
		this.myNext = myNext;
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
	

	public  String toString(){
		return "( )";
	}
	
    public boolean equals(){
    	throw new IllegalArgumentException ("nothing there");
    	}

	@Override
	public AbstractListNode add(Comparable c) {
		// TODO Auto-generated method stub
        NonemptyListNode l = new NonemptyListNode(c);
        return l;
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		// TODO Auto-generated method stub
		NonemptyListNode l = new NonemptyListNode(list.item());
		AbstractListNode head = l;
		while (!list.next().isEmpty()) {
			l.setMyNext(new NonemptyListNode(list.next().item()));
			list = list.next();
			l = (NonemptyListNode) l.getMyNext();
		}
		return head;
	}

	@Override
	public AbstractListNode reverse() {
		// TODO Auto-generated method stub
		return this;
	}
}

