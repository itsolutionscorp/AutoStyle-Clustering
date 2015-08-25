import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();
	abstract public int size(); 
    abstract public Comparable get(int pos);
    abstract public String toString();
	abstract public AbstractListNode add(Comparable c);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode copy();
	abstract public AbstractListNode reverse();
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	abstract public boolean equals( AbstractListNode secondList);
    abstract public Comparable smallest();
    abstract public Comparable smallestHelper();
	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}
	
	public static  AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
		if(a.isEmpty()){
			return b;
		}else if(b.isEmpty()){
			return a;
		}else if(a.next() == null){
				if((Integer)b.item() > (Integer)a.item()){//put the letter to the end
					a = a.appendInPlace(b);
					b = null;
				}else{
				 a = b.appendInPlace(a);
				 b=null;
				}
			}//end a only have one element
			else{
				if((Integer)b.item() < (Integer)a.item()){
					a = new NonemptyListNode(b.item()).appendInPlace(a);
					b = b.next();
				}else{
				 a = new NonemptyListNode(a.item()).appendInPlace(AbstractListNode.merge(a.next(),b));
				
				}		
			}
		
		return a;
	}
	
}

/*
 * 
 */

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

	public boolean isEmpty() {
		return false;
	}

	public AbstractListNode add(Comparable c) {
		AbstractListNode newlist = new EmptyListNode();

		if (this.myItem == null) {
			return new NonemptyListNode(c);
		} else if (this.myNext == null) {
			newlist = new NonemptyListNode(this.myItem, new NonemptyListNode(c));
		} else {
			newlist = new NonemptyListNode(this.myItem, this.myNext.add(c));
		}
		return newlist;
	}

	@Override
	public String toString() {//bazz's idea
		AbstractListNode cycleThrough = this;
		String allTheStringElementsInTheList = "( ";
		int end = 0;
		while (end == 0) {

			if (cycleThrough.isEmpty()) {
				end = 1;
			}

			else {

				allTheStringElementsInTheList += cycleThrough.item() + " ";
				cycleThrough = cycleThrough.next();
			}
		}
		allTheStringElementsInTheList += ")";

		return allTheStringElementsInTheList;

	}

	public AbstractListNode copy() {
		// first step is to copy the list
		AbstractListNode newlist = new EmptyListNode();
		if (this.myItem == null) {
			newlist = newlist;
		} else if (this.myNext == null) {
			newlist = newlist.add(this.myItem);
		} else {
			newlist = new NonemptyListNode(this.myItem, this.myNext.copy());
		}
		return newlist;
	}

	
	public AbstractListNode append(AbstractListNode list) {
		//first step is to copy the list
		AbstractListNode newlist = this.copy();
		if(list.isEmpty()){
			return this;
		}else if(this.myItem ==null){
			return list;
		}else if (list.next() ==null){
			newlist = newlist.add(list.item());
		}else {
			newlist = newlist.add(list.item()).append(list.next());
		return newlist;
				}
		return newlist;
	}

	@Override
	public AbstractListNode reverse() {
		AbstractListNode newlist = this.copy();
		if(this.myItem ==null){
			return newlist;
		}else if(this.myNext ==null){
			newlist = new NonemptyListNode(this.myItem);
		}else{
			newlist = newlist.next().reverse().append(new NonemptyListNode (this.item()));
		}
		return newlist;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if(this.isEmpty()){
			return this;
		}else if(this.myItem ==null){
			return this;
		}else if (this.next() ==null){
			this.myNext = list2;
		}else {
			this.myNext = new NonemptyListNode(this.myItem,this.myNext.appendInPlace(list2));
		return this;
				}
		return this;
	}
	
	public boolean equals(AbstractListNode secondList) {
		AbstractListNode firstCycle = this;
		AbstractListNode secondCycle = secondList;
		if (firstCycle.size() != secondCycle.size()) {

			return false;
		}
		for (int i = 0; i < (firstCycle.size() - 1); i++) {
			if (!(this.item().equals(secondList.item()))) {
				return false;
			}
			firstCycle = firstCycle.next();
			secondCycle = secondCycle.next();
		}
		return true;
	}
	
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		} else {
			return smallestHelper(this.item());
		}
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		for (int i = 0; i < this.size() - 1; i++) {
			smallestSoFar = NonemptyListNode.min(smallestSoFar, this.next()
					.item());
		}
		return smallestSoFar;
	}



	public int size() {
		AbstractListNode cycleThrough = this;
		int sizeOfList = 0;
		int end = 0;
		while (end == 0) {
			if (cycleThrough.isEmpty()) {
				end = 1;
			} else {
				sizeOfList++;
				cycleThrough = cycleThrough.next();
			}
		}
		return sizeOfList;
	}

	public Comparable get(int pos) {
		AbstractListNode cycleThrough = this;// TODO: How do I make the variable
												// Cycle through refer to
												// itself?
		for (int i = 0; i < pos; i++) {
			cycleThrough = cycleThrough.next();
		}

		return cycleThrough.item();
	}

	@Override
	public Comparable smallestHelper() {
		// TODO Auto-generated method stub
		return null;
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

	public boolean isEmpty() {
		return true;
	}

	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " non";
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public AbstractListNode copy() {
		return null;
	}

	@Override
	public AbstractListNode reverse() {
		return null;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		
		return list2;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparable get(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(AbstractListNode secondList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comparable smallest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparable smallestHelper() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
