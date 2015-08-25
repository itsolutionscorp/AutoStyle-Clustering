import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
	abstract public void setNext(AbstractListNode list2);
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append (AbstractListNode list);
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public String toString();
    abstract public boolean equals(Comparable o);
    abstract public void setItem(Comparable item);
    
    public Comparable get(int i) throws IllegalArgumentException{
    	int k = 0;
    	Comparable temp1 = this.item();
    	AbstractListNode temp2 = this.next();
    	while (k < i) {
    		if (temp2 == null) {
    			throw new IllegalArgumentException();
    		} 
    		temp1 = temp2.item();
    		temp2 = temp2.next();
    		k++;
    	} return temp1;
    }
    
    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    }
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  } else { 
    		  Comparable smallest;
    		  smallest = this.item(); 
    		  AbstractListNode temp = this.next(); 
    		  while (!temp.next().isEmpty()) {
    			  smallest = min(smallest, temp.item());
    			  temp = temp.next();
    		  } return smallest;
    	}
    }

    public static AbstractListNode merge (AbstractListNode a, AbstractListNode b) { 
    	if (a.isEmpty()) {
    		return (AbstractListNode) b;
    	} if (b.isEmpty()) {
    		return (AbstractListNode) a; 
    	} else {
    		if (a.item().compareTo(b.item()) < 0) {
    			a.setNext(merge(a.next(), b));
    			return (AbstractListNode) a;
    		}
    		if (a.item().compareTo(b.item()) >= 0) {
    			b.setNext(merge(b.next(), a));
    			return (AbstractListNode) b;
    		}
    	}
    	return a;  
    }
    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    
    public void setItem(Comparable item) {
    	this.myItem = item;
    }
    
    public void setNext(AbstractListNode list2) {
    	this.myNext = list2;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
        if (this.isEmpty()) {
        	return list2;
        }
        for (AbstractListNode cur = this; !cur.isEmpty(); cur = cur.next()) {
        	if (cur.next().isEmpty()) {
        		cur.setNext(list2);
        		return (AbstractListNode) this;
        	}
        } return this;
    }
    
    public AbstractListNode add (Comparable c){
    	NonemptyListNode cnode = new NonemptyListNode(c, new EmptyListNode());
    	if (this.size()== 0) {
    	   return cnode;
    	}
        AbstractListNode copy = new EmptyListNode();
        int i = this.size()-1;
        while (i>= 0) {
        	copy = new NonemptyListNode(this.get(i), cnode);
        	cnode = (NonemptyListNode) copy;
        	i--;
        } return copy;
     }
    
    public AbstractListNode append(AbstractListNode list) {
        AbstractListNode temp = this;
        if (list.isEmpty()) {
        	return this;
        }
        while (!list.isEmpty()) {
        	temp = temp.add(list.item());
        	list = list.next();
        } return temp;
    }
    
    public AbstractListNode reverse() {
    	if (this.size() == 1 || this.size() == 0) {
    		return this;
    	}
    	int i = this.size() - 1;
    	AbstractListNode temp = new EmptyListNode();
    	while (i >= 0) {
    		temp = temp.add(this.get(i));
           	i--;
           } return temp;
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
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    public int size() {
    	return 1 + next().size();
    }

    public String toString() {
    	String par = "";
    	Comparable first = this.item();
    	NonemptyListNode thiss;
    	if (this.next().isEmpty()) {
    		return "( " + first + " )";
    	} else { 
    		thiss = (NonemptyListNode) this.next();
    		while (!(thiss.next().isEmpty())) {
        		par = par + " " + thiss.item();
        		thiss = (NonemptyListNode)thiss.next();
    		} return "( " + first + par + " " + thiss.item() + " )";
    	}
    }
    
    public boolean equals(Comparable o) {
    	return (this.item() == ((AbstractListNode) o).item() && this.size() == ((AbstractListNode) o).size() 
    			&& this.next().equals(((AbstractListNode) o).next()));
    }
}



class EmptyListNode extends AbstractListNode {
    
	public AbstractListNode appendInPlace(AbstractListNode list2){
		return list2;
	}
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
    
    public String toString() {
    	return "( )";
    }
    public boolean equals(Comparable o) {
    	if (((AbstractListNode) o).isEmpty()) {
    		return true;
    	}
    	return false;
    }
    public AbstractListNode add (Comparable c){
    	NonemptyListNode cnode = new NonemptyListNode(c, new EmptyListNode());
    	if (this.size()== 0) {
     	   return cnode;
        }
        AbstractListNode copy = new EmptyListNode();
        int i = this.size() - 1;
        while (i>= 0) {
        	copy = new NonemptyListNode(this.get(i), cnode);
        	cnode = (NonemptyListNode) copy;
        	i--;
        } return copy;
     }
    
    public AbstractListNode append(AbstractListNode list) {
        return list;
    }
    
    public AbstractListNode reverse() {
    	return this;
    }
}

