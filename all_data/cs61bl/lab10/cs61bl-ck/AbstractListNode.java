import java.util.NoSuchElementException;
abstract public class AbstractListNode {
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode obj);
    protected Comparable myItem;
    protected AbstractListNode myNext;
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		AbstractListNode pointer = this;
    		Comparable smallest = smallestSoFar;
    		while (!pointer.isEmpty()) {
    			smallest = min(smallest, pointer.item());
    			pointer = pointer.next();
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

        public AbstractListNode add(Comparable c) {
            AbstractListNode pointer = new NonemptyListNode(c);
             for (int i = this.size() - 1; i >= 0; i -= 1) {
                 pointer = new NonemptyListNode(this.get(i), pointer);
             }
             return pointer;
        }
    	
    	public AbstractListNode append(AbstractListNode list) {
    	    AbstractListNode b = list;
    	    for (int k = this.size() - 1; k >= 0; k -= 1) {
    	    	b = new NonemptyListNode(this.get(k), b);
    	    }
    	    return b;
    	}

        public AbstractListNode reverse() {
            AbstractListNode r = new EmptyListNode();
            for (int k = 0; k < this.size(); k += 1) {
                r = new NonemptyListNode(this.get(k), r);
            }
            return r;
        }
        
        public AbstractListNode appendInPlace(AbstractListNode list2){
           if (this.isEmpty()) {
        	   return list2;
           }
           else {
        	  this.myNext = list2; 
           }
           return this;
        }
        
        
        
        //recursive idea from spring 2015 61a homework07 solution
        public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
            if (a.isEmpty()) {
            	return b;
            }
            if (b.isEmpty()) {
            	return a;
            }
        	if (a.myItem.compareTo(b.myItem) == -1) {
            	a.myNext = merge(a.myNext, b);
            	return a;
        	}
        	else {
            	b.myNext = merge(a, b.myNext);
            	return b;
            	}
            }
        }


class NonemptyListNode extends AbstractListNode {

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
        NonemptyListNode temp = this;
        int count = 0;
        while (temp != null) {
            if (!temp.isEmpty()) {
                count += 1;
            }
            if (temp.myNext instanceof EmptyListNode) {
                break;
            }
            temp = (NonemptyListNode) temp.myNext;
        }
        return count;
    }

    public Comparable get(int pos) {
        NonemptyListNode temp = this;
        while (pos > 0) {
            if (temp == null || temp.myNext instanceof EmptyListNode) {
                throw new IllegalArgumentException("Position out of bounds.");
            }
            pos -= 1;
            temp = (NonemptyListNode) temp.myNext;
        }
        return temp.myItem;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("( ");
        int count = 0;
        while (count < this.size()) {
            sb.append(this.get(count));
            sb.append(" ");
            count += 1;
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(AbstractListNode obj) {
        NonemptyListNode temp = this;
        int count = 0;
        if (temp.size() == obj.size()) {
            while (count < temp.size()) {
                if (temp.get(count) == obj.get(count)) {
                    count += 1;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
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

    public int size() {
        return 0;
    }

    public Comparable get(int pos) {
        return null;
    }

    public String toString() {
        return "( )";
    }

    public boolean equals(AbstractListNode obj) {
        return false;
    }


}
