abstract public class AbstractListNode {

	abstract public Object item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();
	abstract public Object get(int pos);
	abstract public String toString();
	abstract public String toStringHelp();
	abstract public boolean equals(Object other);
}

class NonemptyListNode extends AbstractListNode {

	private Object myItem;
	private AbstractListNode myNext;

	public NonemptyListNode (Object item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode (Object item) {
		this (item, new EmptyListNode());
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
		if (this.isEmpty())
			return 0;
		else {
			return 1 + this.next().size();
		}
	}

	public Object get(int pos) {
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
		if (other.getClass() == this.getClass()) {
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

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Object item() {
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

	public Object get(int pos) {
		return null;
	}
	
	public String toStringHelp() {
		return "";
	}
	
	public String toString() {
		return "";
	}
	
	public boolean equals(Object other) {
		if (other.getClass() == this.getClass())
			return true;
		return false;
	}

}

