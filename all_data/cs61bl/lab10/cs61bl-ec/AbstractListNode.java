import java.util.NoSuchElementException; 

abstract public class AbstractListNode {

	abstract public Comparable item();
	// From the API:
	// Note that null is not an instance of any class, 
	// and e.compareTo(null) should throw a NullPointerException
	// even though e.equals(null) returns false.
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.

	abstract public int size();

	abstract public Comparable get(int pos);

	public boolean equals(Object obj) {
		// Note: must be Object obj (to override) 
		//       we accidentally replaced Object with Comparable 
		String s1 = this.toString();
		String s2 = obj.toString();
		return s1.equals(s2); // just compare their string representations!
	}

	abstract public Comparable smallest();
	

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}


	abstract public AbstractListNode add(Comparable c); 
	
	abstract public AbstractListNode append(AbstractListNode list);

	abstract public AbstractListNode reverse();
	
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
		AbstractListNode n1 = a; 
		AbstractListNode n2 = b; 
		if ( b.isEmpty() ) {
			return a; 
		} else if (a.isEmpty()) {
			return b; 
		}
		Comparable a_first_item = ((NonemptyListNode) a).item();
		Comparable b_first_item = ((NonemptyListNode) b).item();
		if (min(a_first_item, b_first_item).equals(a_first_item)) {
			// n2 points to the node with the smaller first item
			// so reset n2 and n1
			n2 = a;
			n1 = b;
		}
		// at this point, n1 and n2 are set properly
		AbstractListNode result = n2;
		Comparable n1_item = ((NonemptyListNode) n1).item();
		Comparable n2_item = ((NonemptyListNode) n2).item();
		AbstractListNode n1_next = ((NonemptyListNode) n1).next();
		AbstractListNode n2_next = ((NonemptyListNode) n2).next();
		AbstractListNode n2_prev = n2;
		AbstractListNode n1_prev = n1;
		boolean just_increment_n2 = false;
		boolean n1_said_we_should_leave = false;
		while (!n2_next.isEmpty() && !n1_said_we_should_leave) {
			boolean n2_is_smaller = min(n1_item, n2_item).equals(n2_item);
			if (!n2_is_smaller) {
				if (just_increment_n2) {
				((NonemptyListNode)n2_prev).setNext(n1);
				n1_next = ((NonemptyListNode) n1).next();
				((NonemptyListNode)n1).setNext(n2);
				n1_prev = n1;
				n1 = n1_next;
				if (!n1.isEmpty()) n1_item = ((NonemptyListNode) n1).item();
				if (n1.isEmpty()) n1_said_we_should_leave = true;
				else n1_item = ((NonemptyListNode) n1).item();
				} else {
					((NonemptyListNode)n1_prev).setNext(n1);
					n1_next = ((NonemptyListNode) n1).next();
					((NonemptyListNode)n1).setNext(n2);
					n1_prev = n1;
					n1 = n1_next;
					if (n1.isEmpty()) n1_said_we_should_leave = true;
					else n1_item = ((NonemptyListNode) n1).item();
				}
				
				
				just_increment_n2 = false;
			} else {
				n2_prev = n2;
				n2 = n2_next;
				n2_next = ((NonemptyListNode) n2).next();
				n2_item = ((NonemptyListNode) n2).item();
				
				just_increment_n2 = true;
			}
						
			
		}
		
		// glue n1 and n2 together by appending n1 to the end of n2
		if (!n1_said_we_should_leave) ((NonemptyListNode)n2).setNext(n1);
		
		return result; // return the list with the smaller first item
		// at this point both are gauranteed to be NonemptyListNode 
		
			
		
		
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



	// Every other list-processing method goes here.

	/* Recursive method!
	 * 
	 */
	public int size() {
		if (this.myNext.isEmpty()) {
			return 1;
		}
		else {
			return this.myNext.size() + 1;
		}
	}



	public Comparable get(int pos) {
		int num_times_travel = 0;
		NonemptyListNode p = this; // p points to the current node
		while (!p.myNext.isEmpty() && num_times_travel != pos) {
			// the next thing is not EmptyListNode and still needs further travel until pos times
			p = (NonemptyListNode) p.myNext;
			++num_times_travel;
		}
		if (num_times_travel != pos) {
			// you require too many travels and it's already the end of list
			throw new IllegalArgumentException("you require too many travels and it's already the end of list!");
		}
		return p.myItem;
	}

	@Override
	public String toString() {
		String result = "( ";
		NonemptyListNode p = this; // p points to the current node
		while(!p.myNext.isEmpty()) {
			// has next 
			if (p.myItem != null) {
				result += p.myItem.toString() + " ";
			}
			else {
				result += "null ";
			}
			p = (NonemptyListNode) p.myNext;
		}
		// at the last nonempty node, the check above failed, so concatenate here
		if (p.myItem != null) {
			result += p.myItem.toString() + " ";
		}
		else {
			result += "null ";
		}
		result += ")";
		return result;
	}
	
	
	
	public Comparable smallest() {
		Comparable result = this.myItem;
		NonemptyListNode p = this;
		while(!p.myNext.isEmpty()) {
			p = (NonemptyListNode) p.myNext;
			Comparable item = p.myItem;
			result = AbstractListNode.min(result, item);
		}
		return result;
	}
	
	
	
	
	

	public AbstractListNode add(Comparable c) {
		NonemptyListNode result = new NonemptyListNode(this);
		NonemptyListNode n = result; 
		while (!n.myNext.isEmpty()) {
			n = (NonemptyListNode) n.myNext;
		}
		n.myNext = new NonemptyListNode(c); 
		return result; 
	}


	// copy constructor 
	public NonemptyListNode(NonemptyListNode other) {
		NonemptyListNode n = other; 
		NonemptyListNode current = this; // new one we are constructing 
		current.myItem = n.myItem;
		// Note: n.myNext may be an empty node, so have to do the above
		while (!n.myNext.isEmpty()) {
			n = (NonemptyListNode) n.myNext; 
			current.myNext = new NonemptyListNode(n.myItem); 
			current = (NonemptyListNode) current.myNext; 
			current.myItem = n.myItem;
		}
		current.myNext = new EmptyListNode(); 
	}
	
	
	
	public AbstractListNode append(AbstractListNode list) {
		NonemptyListNode result = new NonemptyListNode(this);
		AbstractListNode list_copy = null;
		// check the dynamic type of list and instantiate using either 
		// EmptyListNode() or NonemptyListNode(NonemptyListNode other)
		if (list.isEmpty()) {
			list_copy = new EmptyListNode();
			return result; 
		}
		else {
			list_copy = new NonemptyListNode((NonemptyListNode) list);
			// travel to the end of result and "glue" result and list_copy together
			NonemptyListNode n = result;
			while(!n.myNext.isEmpty()) {
				n = (NonemptyListNode) n.myNext; 
			}
			n.myNext = list_copy; // "glue"
			return result; 
		}
	}
	
	
	public AbstractListNode reverse() {
		NonemptyListNode n = new NonemptyListNode(this);
		// append EmptyListNode to the front 
		AbstractListNode next = (NonemptyListNode) n.myNext; 
		// Note: AbstractListNode because at the last NonemptyListNode 
		//       (second to last node), you cannot cast n.myNext to NonEmptyListNode
		n.myNext = new EmptyListNode();
		while (!next.isEmpty()) {
			NonemptyListNode current = n; 
		//	NonemptyListNode temp = n.myNext; 
			n = (NonemptyListNode) next; 
			next = n.myNext; 
			n.myNext = current; 
		}
		return n; 
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		NonemptyListNode travel = this;
		while(!travel.myNext.isEmpty()) {
			travel = (NonemptyListNode) travel.myNext; 
		}
		travel.myNext = list2; 
		// Note: we traveled three nodes but still want to return the original listnode 
		return this;  
	}
	
	
	
	public void setItem(Comparable c) {
		this.myItem = c;
	}
	
	public void setNext(AbstractListNode a) {
		this.myNext = a;
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


	// Every other list-processing method goes here.

	public int size() {
		return 0;
	}


	public Comparable get(int pos) {
		throw new IllegalArgumentException("you're getting Comparable from an empty list!");
	}


	@Override
	public String toString() {
		return "( )";
	}
	
	
	
	
	public Comparable smallest() {
		throw new NoSuchElementException("Can't find smallest in empty list.");
	}

	public AbstractListNode add(Comparable c) {
		NonemptyListNode result = new NonemptyListNode(c, null);
		return result; 
	}
	
	
	public AbstractListNode append(AbstractListNode list) {
		if (list.isEmpty()) {
			AbstractListNode result = new EmptyListNode();
			return result;
		} else {
			AbstractListNode list_copy = new NonemptyListNode((NonemptyListNode) list);
			return list_copy;
		}
	}
	
	public AbstractListNode reverse() {
		EmptyListNode n = new EmptyListNode();
		return n; 
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2; 
	}

}
