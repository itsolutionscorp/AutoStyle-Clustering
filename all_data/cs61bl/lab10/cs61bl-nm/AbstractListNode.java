import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();
	abstract public int size();
	abstract public AbstractListNode get(int k);
	abstract public String toString();
	abstract public boolean equals(AbstractListNode node);

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
		return smallestHelper(item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		Comparable smallest = smallestSoFar;
		AbstractListNode curNode = next();
		while (!curNode.isEmpty()) {
			smallest = min(smallest, curNode.item());
		}
		return smallest;
	}

	public AbstractListNode add (Comparable c){
		if (isEmpty()) {
			return new NonemptyListNode(c);
		}
		return new NonemptyListNode(item(), next().add(c));
	}

	public AbstractListNode append (AbstractListNode l2) {
		AbstractListNode dummy = this;
		for (int i=0; i<l2.size(); i++) {
			dummy = dummy.add(l2.get(i).item());
		}
		return dummy;
	}

	public AbstractListNode reverse() {
		AbstractListNode dummy = new EmptyListNode();
		for (int i=this.size()-1; i>=0; i--) {
			dummy = dummy.append(new NonemptyListNode(this.get(i).item(), new EmptyListNode()));
		}
		return dummy;
	}

	public AbstractListNode appendInPlace(AbstractListNode list2){
		if(this.isEmpty())
			return list2;
		else{
			return new NonemptyListNode(this.item(), this.next().appendInPlace(list2));
		}
	}

	public abstract void setNext(AbstractListNode a);
	public abstract void setItem(Comparable i);

	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} else {
			if( (Integer) a.item() > (Integer) b.item() ) {
				AbstractListNode temp = a;
				a = b;
				b = temp;
				a.setNext(merge(b, a.next()));
			} else {
				a.setNext(merge(a.next(), b));
			}
		}
		return a;
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

	public void setNext(AbstractListNode a) {
		myNext = a;
	}
	public void setItem(Comparable i) {
		myItem = i;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 1 + myNext.size();
	}

	public AbstractListNode get(int index) {
		AbstractListNode rtnList = this;
		int i = 0;
		while (i < index) {
			rtnList = rtnList.next();
			i++;
		}
		return rtnList;
	}

	public String toString(){
		String rtnString = "( ";
		AbstractListNode temp = this;
		while(!temp.isEmpty()){
			rtnString = rtnString + temp.item() + " ";
			temp = temp.next();
		}
		return rtnString + ")";
	}

	public boolean equals (AbstractListNode node){
		if(this.size()!=node.size())
			return false;
		AbstractListNode lst1 = this;
		AbstractListNode lst2 = node;
		while( !lst1.isEmpty() ){
			if(!lst1.item().equals(lst2.item()))
				return false;
			lst1 = lst1.next();
			lst2 = lst2.next();
		}
		return true;
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

	public void setNext(AbstractListNode a) {
	}

	public void setItem(Comparable i) {

	}

	public int size() {
		return 0;
	}

	public AbstractListNode get(int index) {

		throw new IllegalArgumentException();
	}

	public String toString(){
		return "( )";
	}

	public boolean equals(AbstractListNode other){
		if(other.isEmpty()){
			return true;
		}
		return false;
	}


}
