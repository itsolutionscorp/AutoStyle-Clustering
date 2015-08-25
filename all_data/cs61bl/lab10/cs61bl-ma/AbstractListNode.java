import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size(); 
    abstract public Comparable get(int pos); 
    abstract public String toString(); 
    abstract public boolean equals(Comparable o);
    abstract AbstractListNode add (Comparable c);
    abstract void setItem(Comparable o);
    abstract void setNext(AbstractListNode n); 

    // Every other list-processing method goes here.
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  if (next().isEmpty()) {
    		  return smallestSoFar;
    	  } else {
    		  return min(next().smallest(), smallestSoFar);
    	  }
      	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	
    	public AbstractListNode append(AbstractListNode list) {
        	AbstractListNode last = new EmptyListNode();
        	for (int i = list.size() - 1; i>=0; i--) {
        		AbstractListNode n = new NonemptyListNode(list.get(i), last);
        		last = n; 
        	}
        	for (int i = size() - 1; i >= 0; i--) {
        		AbstractListNode n = new NonemptyListNode(get(i), last);
        		last = n; 
        	}
        	return last;
        }
    	
    	public AbstractListNode reverse() {
        	AbstractListNode last = new EmptyListNode();
        	for (int i = 0; i < size(); i++) {
        		AbstractListNode n = new NonemptyListNode(get(i), last);
        		last = n;
        	}
        	return last;
        }
    	
    	public AbstractListNode appendInPlace(AbstractListNode list2) {
    		if (isEmpty()) {
    			return list2; 
    		}
	    	AbstractListNode n = this;
		    for (int i = 0; i < size() - 1; i++ ) {
		    	n = n.next();
		    }
		    n.setNext(list2);
		    return this; 
    	}
    	
    	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    		
    		if (a.isEmpty()) {
    			return b; 
    		}
    		if (b.isEmpty()) {
    			return a; 
    		}
    		if (a.item().compareTo(b.item()) < 0) {
    			return merge(b,a);
    		} else {
				AbstractListNode n = b; 
				AbstractListNode temp;
				int size = b.size();
				for (int i = 0; i < size; i++) {
					if (n.next().isEmpty()) {
						n.setNext(a);
						break;
					} else if (a.item().compareTo(n.next().item()) <= 0) {
						temp = n.next();
						n.setNext(a);
						a.setNext(merge(a.next(), temp));
						break;
					}
					n = n.next();
				}
				return b;
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
    public int size() {
    	return 1 + myNext.size(); 
    }
    
    public void setItem(Comparable o) {
    	myItem = o; 
    }
    
    public void setNext(AbstractListNode n) {
    	myNext = n; 
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return myItem; 
    	} else {
    		return myNext.get(pos-1); 
    	}
    }
    
    public String toString() {
    	String s = "( " + myItem.toString();
    	AbstractListNode temp = myNext;
    	while (!temp.isEmpty()) {
    		s = s + " " + temp.item().toString(); 
    		temp = temp.next();
    	}
    	return s + " )";
    }
    
    public boolean equals(Comparable o) {
    	if (o instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) o;
    		if (node.isEmpty()) {
    			return false;
    		}
    		else if (node.item().equals(myItem)) {
    			return myNext.equals(node.next()); 
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    public AbstractListNode add(Comparable c) {
    	AbstractListNode empty = new EmptyListNode();
    	AbstractListNode last = new NonemptyListNode(c, empty);
    	for (int i = size() - 1; i >= 0; i--) {
    		AbstractListNode n = new NonemptyListNode(get(i), last);
    		last = n;
    	}
    	return last;
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
    
    public void setItem(Comparable o) {
    	throw new IllegalStateException("Cannot set item for empty node.");
    }
    
    public void setNext(AbstractListNode n) {
    	throw new IllegalStateException("Cannot set next pointer for empty node.");
    }
    
    public Comparable get(int pos) throws IllegalArgumentException {
		throw new IllegalArgumentException("Index out of range"); 
    }
    public String toString() {
    	return "()";
    }
    public boolean equals(Comparable o) {
    	if (o instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) o; 
    		if (node.isEmpty()) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }

	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c, new EmptyListNode());
	}	
}

