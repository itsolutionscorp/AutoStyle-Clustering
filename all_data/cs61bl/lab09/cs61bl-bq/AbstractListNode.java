abstract public class AbstractListNode {

	abstract public Object item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	public AbstractListNode get(int pos) {

		if (pos < 0) {
			throw new IllegalArgumentException(
					"Position can't be less than zero");
		}
		AbstractListNode n = this;
		while (pos > 0) {
			if (n.next().isEmpty()) {
				throw new IllegalArgumentException("Position out of bounds.");
			}
			n = n.next();
			pos--;
		}
		return n;
	}
	
	abstract public String toString();
	abstract public boolean equals();
		

	// Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

	private Object myItem;
	private AbstractListNode myNext;

	public NonemptyListNode(Object item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode(Object item) {
		this(item, new EmptyListNode());
	}

	public Object item() {
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

	public String toString() {
	
			
			String theString = new String();
			theString += "( ";
			for( int i = 0; i < this.size(); i++ ) {
				theString  += this.get(i).item().toString() + " ";
			}
			theString += "() )";
			return theString;
			
			
		}

	public boolean equals(NonemptyListNode blah) {
		if (this.size() != blah.size()) {
			return false;
			
		}
		else {
			for (int i = 0; i< this.size(); i++ ) {
				
				if(!this.get(i).item().equals(blah.get(i).item())) {
					return false;
				}	
			}
		}
		return true;
	}
}



class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Object item() {
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

	public int size() {

		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(AbstractListNode eh) {
		return eh.isEmpty();
	}
	
	

}
