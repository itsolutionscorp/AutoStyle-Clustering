abstract public class AbstractListNode {

	abstract public Object item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();
	abstract public int size();

	// Every other list-processing method goes here.
	
	public static void main(String[] args) {
		NonemptyListNode.main(new String[10]);
	}
	public void getRidOfOddElements() {
		// TODO Auto-generated method stub
		
	}
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

	public int size(){
		// there are two ways to do this
		// iterative solution <-> static
		// recursive solution <-> non-static
		// add 1
		int s = this.next().size();
		return 1 + s; 
	}
	
	public static int size(AbstractListNode a) {
		int s = 0;
		AbstractListNode curr;
		for(curr = a; curr != null; curr = curr.next()){
			s++;
		}
		return s;
		
	}
	
	
	public String toString(){
		return this.myItem.toString() + " " + this.myNext.toString();
	}
	
	public static void main(String[] args) {
		NonemptyListNode lst = new NonemptyListNode(5, new NonemptyListNode(6, new NonemptyListNode(7, null)));
		System.out.println(lst.toString());
	}

	
	public void getRidOfOddElements() {
		if (this.myNext.isEmpty()) {
			return;
		}
		this.myNext = this.next().next();
		this.myNext.getRidOfOddElements();
	}
	
	public static void getRidOfOddElements(NonemptyListNode a) {
		AbstractListNode curr;
		for(curr = a; !curr.next().isEmpty(); curr = curr.next()){
			curr=  curr.next().next();
		}	
	}
	public boolean equals(AbstractListNode a){
		if (a==null){
			return false;
		}
		return this.item().equals(a.item()) && this.next().equals(a.next());
	}

}

class EmptyListNode extends AbstractListNode {

	public boolean equals(AbstractListNode a){
		if (a.next()!=null){
			return false;
		}
		if (this.item().equals(a.item())){
			return true;
		}else {
			return false;
		}
	}
	public void getRidOfOddElements() {
		return;
	}
	
	public EmptyListNode() {

	}
	
	public int size() {
		return 0;
	}

	public String toString() {
		return ""; 
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

}
