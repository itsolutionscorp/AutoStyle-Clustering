abstract public class AbstractListNode {

	abstract public Object item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();

	public Object get(int position) {
		if (position == 0) {
			return item();
		} else {
			return next().get(position - 1);
		}
	}
	
	public String toString(){
		String result = "( ";
		AbstractListNode pointer = this;
		while(!pointer.isEmpty()){
			result += pointer.item() + " ";
			pointer = pointer.next();
		}
		return result + ")";	
		 
	}
	
	public boolean equals(Object list){
		if(this.toString().equals(list.toString())){
			return true;
		}
		return false;
	}
	
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
		return 1 + this.next().size();
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

}
