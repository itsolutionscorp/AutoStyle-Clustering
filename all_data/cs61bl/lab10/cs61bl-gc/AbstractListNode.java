import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();
	abstract public Comparable get(int pos);
	abstract public String toString();
	abstract public String toStringHelp();
	abstract public boolean equals(Object other);
	abstract public AbstractListNode add(Comparable c);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode reverse();
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
		if (a.isEmpty())
	    	return b;
	    if (b.isEmpty())
	    	return a;
	    if ((int) a.item() < (int) b.item()){
			((NonemptyListNode)a).setNext(merge(a.next(), b));
			return a;
	    }
			((NonemptyListNode)b).setNext(merge(a, b.next()));
			return b;
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
		  }
		  return this.smallestHelper(this.get(0));
		}

		public Comparable smallestHelper(Comparable smallestSoFar) {
			Comparable result = smallestSoFar;
			for (int i = 0; i < this.size(); i++) {
				result = min(result, this.get(i));
			}
			return result;
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
		if (this.isEmpty())
			return 0;
		else {
			return 1 + this.next().size();
		}
	}

	public Comparable get(int pos) {
		if (pos == 0)
			return this.item();
		else {
			if (! this.isEmpty())
				return this.next().get(pos - 1);
			else 
				throw new IllegalArgumentException("Index out of range");
		}

	}
	
	public String toString(){
		return "( " +this.toStringHelp();
	}
	
	public String toStringHelp() {
		String result = "";
		if (this.next().isEmpty())
			return this.item().toString() + " )";
		else {
			result += this.item().toString() + " " + this.next().toStringHelp();
		}
		return result;
	}
	
	public boolean equals(Object other) {
		if (other instanceof NonemptyListNode) {
			NonemptyListNode other2 = (NonemptyListNode) other;
			if (this.size() == other2.size()) {
				for (int i = 0; i < this.size(); i++) {
					if (! this.get(i).equals(other2.get(i)))
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public AbstractListNode add(Comparable c) {
		if (this.isEmpty())
			return new NonemptyListNode(c);
		NonemptyListNode result = new NonemptyListNode(c);
		for (int i = this.size() - 1; i >= 0; i--) {
			result = new NonemptyListNode(this.get(i), result);
		}
		return result;
	}
	
	public AbstractListNode append(AbstractListNode list) {
	    if (list.isEmpty()) {
	    	return this;
	    }
	    AbstractListNode result = new NonemptyListNode(this.get(0));
	    for (int i = 1; i < this.size(); i++) {
	    	result = result.add(this.get(i));
	    }
	    for (int i = 0; i < list.size(); i++) {
	    	result = result.add(list.get(i));
	    }
	    return result;
	}

	public AbstractListNode reverse() {
		AbstractListNode result = new EmptyListNode();
		for (int i = this.size() - 1; i >= 0; i--) {
			result = result.add(this.get(i));
		}
		return result;
	}
	
	public void setItem(Comparable c) {
		this.myItem = c;
	}
	
	public void setNext(AbstractListNode list) {
		this.myNext = list;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2){
		if (this.next().isEmpty())
			this.setNext(list2);
		else
			this.myNext.appendInPlace(list2);
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

	public int size() {
		return 0;
	}

	public Comparable get(int pos) {
		return null;
	}
	
	public String toStringHelp() {
		return "( )";
	}
	
	public String toString() {
		return "( )";
	}
	
	public boolean equals(Object other) {
		if (other.getClass() == this.getClass())
			return true;
		return false;
	}
	
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	public AbstractListNode append(AbstractListNode list) {
	    AbstractListNode result = new NonemptyListNode(list.get(0));
	    for (int i = 1; i < list.size(); i++) {
	    	result.add(list.get(i));
	    }
		return result;
	}
	
	public AbstractListNode reverse() {
	    return new EmptyListNode();
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2){
	    return list2;
	}
	
	
}

