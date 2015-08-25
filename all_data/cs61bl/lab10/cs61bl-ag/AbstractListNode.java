import java.util.NoSuchElementException;

abstract public class AbstractListNode{
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int index);
    public Comparable smallest() {
		  if (isEmpty()) {
		    throw new NoSuchElementException("Can't find smallest in empty list.");
		  }
		  return min(item(),smallestHelper(next().item()));
		}

		public Comparable smallestHelper(Comparable smallestSoFar) {
			if(next().isEmpty()){
				return item();
			}
			else
				return min(item(),smallestHelper(next().item()));
		}

		public static Comparable min(Comparable c1, Comparable c2) {
		  if (c1.compareTo(c2) < 0) {
		    return c1;
		  } else {
		    return c2;
		  }
		}
		
		public static AbstractListNode merge(AbstractListNode a,AbstractListNode b){
			if(a.isEmpty())
				return b;
			else if(b.isEmpty())
				return a;
			else if(a.item().compareTo(b.item())<0){
				a.setNext(merge(a.next(),b));
				return a;
			}
			else{
				b.setNext(merge(a,b.next()));
				return b;
			}
		}
    //abstract public boolean equals(AbstractListNode lst);
    // Every other list-processing method goes here.
		public abstract AbstractListNode add(Comparable c);
		public abstract AbstractListNode append(AbstractListNode list);
		public abstract AbstractListNode reverse();
		public abstract AbstractListNode appendInPlace(AbstractListNode list2);
		public abstract void setNext(AbstractListNode l);
		public abstract void setItem(Object O);

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
        return (Comparable) myItem;
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
		return 1+myNext.size();
	}

	@Override
	public Comparable get(int index){
    	if(index==0){
    		return myItem;
    	}    		
    	else
    		return next().get(--index);
    }
	
	public String toString() {
		AbstractListNode x = this;
		String res = "( ";
		while(!x.isEmpty()){
			res+=x.item().toString()+" ";
			x=x.next();
		}
		return res + ")";
		
	}
	
	public boolean equals(Object lst) {
		try{
			AbstractListNode node = (AbstractListNode)lst;
			if (!(myItem.equals(node.item()))) {
				return false;
			}
			return myNext.equals(node.next());
			}
		catch(ClassCastException e){
			return false;
		}
	}
	

		@Override
		public AbstractListNode add(Comparable c) {
			return new NonemptyListNode(item(),myNext.add(c));
		}
		
		public AbstractListNode append(AbstractListNode list){
			if(list.isEmpty())
				return this;
			return new NonemptyListNode(item(),myNext.append(list));
		}

		@Override
		public AbstractListNode reverse() {
			AbstractListNode p = new EmptyListNode();
			AbstractListNode x = this;
			while(!x.isEmpty()){
				p=new NonemptyListNode(x.item(),p);
				x=x.next();		
			}
			return p;
		}

		@Override
		public AbstractListNode appendInPlace(AbstractListNode list2) {
			if(list2.isEmpty())
				return this;
			myNext=this.next().appendInPlace(list2);
			return this;
		}

		@Override
		public void setNext(AbstractListNode l) {
			myNext=l;
		}

		@Override
		public void setItem(Object O) {
			myItem=(Comparable)O;	
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
	public Comparable get(int index) {
		throw new IllegalArgumentException("There's nothing here.");
	}

	public String toString() {
		return "( )";
	}
	
	public boolean equals(Object lst) {
		try{
			AbstractListNode node = (AbstractListNode)lst;
			if (node.isEmpty()) {
				return true;
			}
			return false;
		}
		catch(ClassCastException e){
			return false;
		}
	}


	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		return list;
	}

	@Override
	public AbstractListNode reverse() {
		// TODO Auto-generated method stub
		return new EmptyListNode();
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}

	@Override
	public void setNext(AbstractListNode l) {
		throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
		
	}

	@Override
	public void setItem(Object O) {
		throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
		
	}
}
