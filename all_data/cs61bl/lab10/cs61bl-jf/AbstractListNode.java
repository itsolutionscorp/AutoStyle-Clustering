import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object object);
    abstract void setNext(AbstractListNode n);
    abstract void setItem(Comparable n);
   
    
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
    	}
    	return smallestHelper(this.item());
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	AbstractListNode s = this;
    	if (s.isEmpty()) {
    		return smallestSoFar;
    	} else {
    		return s.next().smallestHelper(min(smallestSoFar,s.item()));
    	}
    }
    
    public AbstractListNode add(Comparable c) {
        if (this.isEmpty()) {
        	return new NonemptyListNode(c);
        } else {
        	return new NonemptyListNode(this.item(), this.next().add(c));
        }
    }
     
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return this;
    	} else if (this.size() == 1) {
    		return new NonemptyListNode(this.item(), list);
    	} else {
    		return new NonemptyListNode(this.item(), this.next().append(list));
    	}
    }
    public AbstractListNode reverse() {
    	return reversehelper(this);
    }
    
    public AbstractListNode reversehelper(AbstractListNode s) {
    	AbstractListNode t;
    	AbstractListNode r = new NonemptyListNode(s.item());
    	while (s.size() > 1) {
    		t = new NonemptyListNode(s.next().item());
    		r = t.append(r);
    		s = s.next();
    	}
    	return r;
    }
    
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (this.isEmpty()) {
    		return list2;
    	}
    	if (list2.isEmpty()) {
    		return this;
    	}
    	if (this.size() == 1) {
    		setNext(list2);
    		return this;
    	} else {
    		return this.next().appendInPlace(list2);
    	}
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()) {
        	return b;
        }
        if (b.isEmpty()) {
        	return a;
        }
        if ((Integer) a.item() < (Integer) b.item()) {
        	a.setNext(merge(a.next(),b));
        	return a;
        } else {
        	b.setNext(merge(a,b.next()));
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		AbstractListNode node = this;
		while (!node.isEmpty()) {
			node = node.next();
			size++;
		}
		return size;
	}

	@Override
	public Comparable get(int pos) {
		// TODO Auto-generated method stub
		if (pos < 0 || pos > this.size()-1)
    		throw new IllegalArgumentException("Out of range");
    	AbstractListNode findit = this;
    	while (pos > 0) {
    		findit = findit.next();
    		pos--;
    	}
    	return findit.item();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String parenthesis = "( ";
		AbstractListNode cur = this;
		while (!cur.isEmpty()) {
			parenthesis =parenthesis + cur.item() + " ";
			cur = cur.next();
		}
		parenthesis += ")";
		return parenthesis;
	}

	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		AbstractListNode cur0 = this;
		AbstractListNode cur1 = (AbstractListNode) object;
		if (object == null)
			return false;
		if (cur0.size() != cur1.size())
			return false;
		while (!cur0.isEmpty() && !cur1.isEmpty()) {
			if (cur0.item() != cur1.item())
				return false;
			cur0 = cur0.next();
			cur1 = cur1.next();
		}
		return true;
	}
	
	public void setNext(AbstractListNode n) {
		myNext = n;
	}
	
	public void setItem(Comparable n) {
		myItem = n;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparable get(int pos) {
		// TODO Auto-generated method stub
		return item();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( )";
	}

	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		if (object == null )
			return false;
	    AbstractListNode cur = (AbstractListNode) object;
		return cur.isEmpty();
	}
	
	public void setNext(AbstractListNode n) {
	}
	
	public void setItem(Comparable n) {
	}
	
}
