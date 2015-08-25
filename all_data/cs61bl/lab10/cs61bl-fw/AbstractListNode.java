import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();
	abstract public int size();
	abstract public String toString();
	abstract public boolean equals(Object o);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	abstract public void setItem(Comparable item);
	abstract public void setNext(AbstractListNode next);
	// Every other list-processing method goes here.
	public AbstractListNode add(Comparable c) {
		if (isEmpty()) {
			return new NonemptyListNode(c, new EmptyListNode());
		} 
		return new NonemptyListNode(this.item(), next().add(c));
	}
	
	public Comparable get(int pos) {
		// TODO Auto-generated method stub
		AbstractListNode cur = this;

		if (pos < 0) {
			throw new IllegalArgumentException("Position does not exist!");
		}

		while (!cur.isEmpty() && pos > 0) {
			cur = cur.next();
			pos--;
		}

		if (cur == null || pos > 0) {
			throw new IllegalArgumentException("Position does not exist!");
		}

		return cur.item();
	}
	
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}
		return smallestHelper(item()) ;
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		AbstractListNode cur = this;
		Comparable minItem = smallestSoFar;
		while (!cur.isEmpty()) {
			minItem = min(cur.item(), minItem);
			cur = cur.next();
		}
		return minItem;
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}
	
	public AbstractListNode reverse() {
	    if (isEmpty()) {
	    	return new EmptyListNode();
	    } 
	    AbstractListNode cur = this;
	    AbstractListNode reverseNode = new EmptyListNode();
	    while (!cur.isEmpty()) {
	    	reverseNode = new NonemptyListNode(cur.item(), reverseNode);
	    	cur = cur.next();
	    }
	    return reverseNode;
	}
	
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
	    // Fill this out
		
		if (a.isEmpty()) {
			return b;
		}
		
		if (b.isEmpty()) {
			return a;
		}
		
		AbstractListNode sortNode = null;
		AbstractListNode tempNode = null;
		
		if (a.item() == min(a.item(), b.item())) {
			sortNode = a; 
		} else {
			sortNode = b;
			b = a;
			a = sortNode;
		}
		
	
		while (!a.next().isEmpty() && !b.isEmpty()) {
			if (b.item() == min(a.next().item(), b.item())) {
				tempNode = a.next();
				a.setNext(b);
				b = tempNode;
			}
			a = a.next();
		}
		
		if (a.next().isEmpty()) {
			a.setNext(b);
		}
		
		return sortNode;
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
		AbstractListNode cur = this;
		int size = 0;
		
		while (!cur.isEmpty()) {
			size++;
			cur = cur.next();
		}
		return size;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		AbstractListNode cur = this;
		
		String result = "( ";
		while (!cur.isEmpty()) {
			result = result + cur.item() + " ";
			cur = cur.next();
		}
		
		result += ")";
		
		return result;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof NonemptyListNode) {
			AbstractListNode cur = (AbstractListNode)o;
			AbstractListNode current = this;
			while (!current.isEmpty()) {
				if (current.item() != cur.item()) {
					return false;
				}
				current = current.next();
				cur = cur.next();
			}
			if (cur.isEmpty())
				return true;
			return false;
		} else {
			return false;
		}
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			return new NonemptyListNode(myItem, myNext);
		} 
		return new NonemptyListNode(myItem, myNext.append(list));
	}
	
	public void setItem(Comparable item) {
		this.myItem = item;
	}
	
	public void setNext(AbstractListNode next){
		this.myNext = next;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		// TODO Auto-generated method stub
		AbstractListNode cur = this;
		while (!cur.next().isEmpty()) {
			cur = cur.next();
		}
		cur.setNext(list2);
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( )";
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return o instanceof EmptyListNode;
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		// TODO Auto-generated method stub
		return list2;
	}

	@Override
	public void setItem(Comparable item) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException("Empty list has no item!");
	}

	@Override
	public void setNext(AbstractListNode next) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException("Empty list has no next pointer!");
		
	}
}

