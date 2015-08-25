import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	abstract public AbstractListNode get(int i);

	abstract public String toString();

	abstract public boolean equals(Comparable arg);
	abstract public AbstractListNode add(Comparable arg);
	abstract public AbstractListNode append(AbstractListNode arg);
	abstract public AbstractListNode reverse();
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} else {
			AbstractListNode big = (a.size() >= b.size()) ? a : b;
			AbstractListNode small = (a.size() >= b.size()) ? b : a;
			for (int i = 0; i < big.size(); i++) {
				AbstractListNode temp;
				if (((NonemptyListNode) big.get(i)).next().isEmpty()) {
					if ((Integer) ((NonemptyListNode) small.get(0)).item() > (Integer) ((NonemptyListNode) big.get(i)).item()) {
						((NonemptyListNode) big.get(i)).setNext(small);
					} else {
						((NonemptyListNode) small).setNext(big.get(i));
						((NonemptyListNode) big.get(i - 1)).setNext(((NonemptyListNode) small.get(0)));
					}
					return big;
				} else if ((Integer) ((NonemptyListNode) small.get(0)).item() < (Integer) ((NonemptyListNode) big.get(i)).item()) {
					temp = small.next();
					((NonemptyListNode) small).setNext(big.get(i));
					((NonemptyListNode) big.get(i - 1)).setNext(((NonemptyListNode) small.get(0)));
					small = temp;
				} 
			}
			return big;
		}	
		
	}
	
	
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
			return smallestHelper(min(smallestSoFar, next().item()));
		}
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		}	 else {
			return c2;
		}
	}

}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;
	private int length = 1;

	public NonemptyListNode(Comparable item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode(Comparable item) {
		this(item, new EmptyListNode());
	}

	public Comparable item() {
		return myItem;
	}
	
	public void getItem(Comparable arg) {
		myItem = arg;
	}

	public AbstractListNode next() {
		return myNext;
	}
	
	public void setNext(AbstractListNode arg) {
		myNext = arg;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		if (this.isEmpty() == false) {
			return this.length + next().size();
		}
		return this.length;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		NonemptyListNode temp = this;
		while (!temp.isEmpty()) {
			if (temp.size() == 1) {
				temp.myNext = list2;
			} else {
				temp = (NonemptyListNode) temp.next();
			}
		}
		return temp;
	}
	

	public AbstractListNode add(Comparable arg) {
		NonemptyListNode aln = new NonemptyListNode(myItem);
		AbstractListNode pointer = aln;
		NonemptyListNode thiss = this;

		aln.myNext = thiss.myNext;
		NonemptyListNode temp = aln;
		while (!temp.isEmpty()) {
			if (temp.size() == 1) {
				temp.myNext = new NonemptyListNode(arg);
				return pointer;
			} else {
				temp = (NonemptyListNode) temp.next();
			}
		}
		return pointer;
	}
	
	public AbstractListNode append(AbstractListNode arg) {
		NonemptyListNode aln = new NonemptyListNode(myItem);
		AbstractListNode pointer = aln;
		NonemptyListNode thiss = this;

		aln.myNext = thiss.myNext;
		NonemptyListNode temp = aln;
		while (!temp.isEmpty()) {
			if (temp.size() == 1) {
				temp.myNext = arg;
				return pointer;
			} else {
				temp = (NonemptyListNode) temp.next();
			}
		}
		return pointer;
	}
	
	public AbstractListNode reverse () {
		NonemptyListNode aln = new NonemptyListNode(myItem);
		AbstractListNode pointer = aln;
		NonemptyListNode thiss = this;
		
		NonemptyListNode temp = aln;
		for (int i = size()- 1; i >= 0; i -= 1) {
			if (i == 0) {
				temp.myNext = new EmptyListNode();
			} else {
				temp.myItem = (Comparable) this.get(i).myItem;
				temp.myNext = new NonemptyListNode( (Comparable) thiss.get(i - 1).myItem);
				temp = (NonemptyListNode) temp.next();
			}
		}
		return pointer;
	}
	
	public NonemptyListNode get(int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException(
					"Position is out of range421421414321");
		}
		if (pos == 0) {
			return this;
		} else {
			if (next().isEmpty()) {
				throw new IllegalArgumentException(
						"Position is out of rangeGGGGg");
			} else {
				return (NonemptyListNode) next().get(pos - 1);
			}
		}
	}

	public String toString() {
		String ret = "( ";
		for (int i = 0; i < size(); i++) {
			ret += get(i).myItem + " ";
		}
		ret += ")";
		return ret;
	}

	public boolean equals(Comparable arg) {
		if (arg instanceof NonemptyListNode) {
			NonemptyListNode listnode = (NonemptyListNode) arg;
			return this.toString().equals(listnode.toString());
		}
		return false;
	}

}

class EmptyListNode extends AbstractListNode {

	private int length = 0;

	public EmptyListNode() {

	}
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}
	
	public boolean equals(Comparable arg) {
		if (arg instanceof EmptyListNode) {
			EmptyListNode listnode = (EmptyListNode) arg;
			return this.toString().equals(listnode.toString());
		}
		return false;
	}
	
	public AbstractListNode reverse () {
		return this;
	}

	public int size() {
		return length;
	}

	public String toString() {
		return "( )";
	}
	
	public AbstractListNode add(Comparable arg) {
		NonemptyListNode aln = new NonemptyListNode(arg);
		AbstractListNode pointer = aln;
		return pointer;
	}
	
	public AbstractListNode append(AbstractListNode arg) {
		return arg;
	}

	public EmptyListNode get(int pos) {
		throw new IllegalArgumentException("There is not item at this position");
	}

	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

}
