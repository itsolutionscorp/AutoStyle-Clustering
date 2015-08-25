import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();

	abstract public AbstractListNode next();
	
	abstract public void setNext(AbstractListNode n);

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract int size();

	abstract Object get(int index);

	abstract String toString(boolean parenthesis);

	public abstract boolean equals(Object arg);
	
	public Comparable smallest() {
		  if (isEmpty()) {
		    throw new NoSuchElementException("Can't find smallest in empty list.");
		  }
		  return smallestHelper(next(), item()) ;
		}

		public Comparable smallestHelper(AbstractListNode list, Comparable smallestSoFar) {
			if (list.isEmpty()) {
				return smallestSoFar;
			} else {
				return smallestHelper(list.next(), min(smallestSoFar, list.item()));
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

	public AbstractListNode addHelper(Comparable c, AbstractListNode head) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract AbstractListNode append(AbstractListNode list);
	
	public abstract AbstractListNode reverse();
	
	public abstract AbstractListNode appendInPlace(AbstractListNode list2);
	
	public static AbstractListNode merge(AbstractListNode l1, AbstractListNode l2) {
		if (l1.isEmpty())
			return l2;
		if (l2.isEmpty())
			return l1;
		return min(l1.item(),l2.item()) == l1.item()? new NonemptyListNode(l1.item(), merge(l1.next(), l2)) : new NonemptyListNode(l2.item(), merge(l1, l2.next()));
	}
}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

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

	public AbstractListNode next() {
		return myNext;
	}
	
	public void setNext(AbstractListNode n) {
		myNext = n;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 1 + myNext.size();
	}

	public Object get(int index) {
		if (index == 0)
			return myItem;
		return myNext.get(index - 1);
	}
	public String toString() {
		return toString(true);
	}
	public String toString(boolean parenthesis) {
		if (parenthesis) {
			return "( " + myItem.toString() + " " + myNext.toString(false);
		}
		return myItem.toString() + " " + myNext.toString(false);
	}

	public boolean equals(Object arg) {
    	try {
    		return size() == ((AbstractListNode)arg).size() && myItem.equals(((AbstractListNode) arg).get(0)) && myNext.equals(((AbstractListNode) arg).next());
    	}
    	catch (ClassCastException e) {
    		return false;
    	}
    	
    }

	@Override
	public AbstractListNode add(Comparable c) {
		AbstractListNode head = new NonemptyListNode(this.item(), null);
		AbstractListNode currentNode = this.next();
		AbstractListNode current = head;
		while(!currentNode.isEmpty()) {
			current.setNext(new NonemptyListNode(currentNode.item(),null));
			current = current.next();
			currentNode = currentNode.next();
		}
		current.setNext(new NonemptyListNode(c,null));
		current = current.next();
		current.setNext(new EmptyListNode());
		return head;
	}
	
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode head = new NonemptyListNode(myItem, null);
		AbstractListNode currentNode = this.next();
		AbstractListNode current = head;
		while(!currentNode.isEmpty()) {
			current.setNext(new NonemptyListNode(currentNode.item(),null));
			current = current.next();
			currentNode = currentNode.next();
		}
		
		while(!list.isEmpty()) {
			current.setNext(new NonemptyListNode(list.item(),null));
			list = list.next();
			current = current.next();
		}
		current.setNext(new EmptyListNode());
		return head;
	}
	
	public AbstractListNode reverse() {
		AbstractListNode current = new EmptyListNode();
		AbstractListNode currentNode = this;
		while(!currentNode.isEmpty())
		{
			current = new NonemptyListNode(currentNode.item(), current);
			currentNode = currentNode.next();
		}
		return current;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		AbstractListNode head = this;
		while (!head.next().isEmpty())
			head = head.next();
		head.setNext(list2);
		return this;
	}

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {
		
	}

	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	public Object get(int index) throws IllegalArgumentException {
		throw new IllegalArgumentException();
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
	public String toString(boolean parenthesis) {
		return ")";
	}
	
	public boolean equals(Object arg) {
		return ((AbstractListNode)arg).isEmpty();
	}

	@Override
	public void setNext(AbstractListNode n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c, new EmptyListNode());
	}
	
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode head = new NonemptyListNode(list.item(), null);
		AbstractListNode current = head;
		while(!list.isEmpty()) {
			current.setNext(new NonemptyListNode(list.item(),null));
			list = list.next();
			current = current.next();
		}
		head.setNext(new EmptyListNode());
		return head;
	}
	
	public AbstractListNode reverse() {
		return new EmptyListNode();
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}
	
	
}
