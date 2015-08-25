import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		if (contents.size() == 0){
			setNode(1, new Node(item,priority));
			return;
		}
		for (int i = 0; i < contents.size(); i++){
			if(getNode(getLeftOf(i))==null){
				setLeft(i, new Node(item, priority));
				bubbleUp(i);
				break;
			}
			else if(getNode(getRightOf(i))==null){
				setRight(i, new Node(item, priority));
				bubbleUp(i);
				break;
			}
			
		}
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		return getNode(0);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		Node rv = peek();
		this.contents.set(0, null);
		swap(0, contents.size());
		bubbleDown(0);	
		return rv;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		for (int i = 0; i < contents.size(); i++){
			Node temp = getNode(i);
			if (temp != null){
				if (temp.item().equals(item)){

					if (temp.priority() < priority){
						temp.myPriority =  priority;
						bubbleDown(i);
					}
					else if (temp.priority() > priority){
						temp.myPriority =  priority;
						bubbleUp(i);
					}
				}
			}
		}
	}

	/**
	 * Prints out the heap sideways.
	 */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
		contents.set(index, n);
	}

	/**
	 * Swap the nodes at the two indices.
	 */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/**
	 * Returns the index of the node to the left of the node at i.
	 */
	private int getLeftOf(int i) {
		if (contents.size()>(2*i)){
			if (contents.get(2*i)!=null) {
				return (2*i);
			}
		}
		return 0;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		if (contents.size()>(2*i+1)){
			if (contents.get(2*i+1)!=null) {
				return (2*i+1);
			}
		}
		return 0;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		if (contents.size()>(i/2)){
			if (contents.get(i/2)!=null) {
				return (i/2);
			}
		}
		return 0;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		setNode(index*2, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(index*2+1, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		while(getNode(index).priority()<(getNode(getParentOf(index)).priority())){
			swap(index, getParentOf(index));
			index = getParentOf(index);
			if (index == 0){
				break;
			}
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		int rtn;
		if (getLeftOf(index) != 0 && getRightOf(index) != 0) {
			if (min(index, getLeftOf(index))!=index &&  min(index, getRightOf(index))!=index) {
				return;
			}
			if (min(getLeftOf(index), getRightOf(index))==getLeftOf(index)) {
				if (min(index, getLeftOf(index))!=index) {
					rtn = getLeftOf(index);
					swap(index, getLeftOf(index));
					index = rtn;
					bubbleDown(index);
				} 
			} else if (min(index, getRightOf(index))!=index) {
				rtn = getRightOf(index);
				swap(index, getRightOf(index));
				index = rtn;
				bubbleDown(index);
			}
		}
		if (getLeftOf(index) != 0 && min(index, getLeftOf(index))==index){
			return;
		} else {
			rtn = getLeftOf(index);
			swap(index, getLeftOf(index));
			index = rtn;
			bubbleDown(index);
		}
		if (getRightOf(index) != 0 && min(index, getRightOf(index))==index){
			return;
		} else {
			rtn = getRightOf(index);
			swap(index, getRightOf(index));
			index = rtn;
			bubbleDown(index);
		}
		return;
		
	}

	/**
	 * Returns the index of the node with smaller priority. Precondition: Not
	 * both of the nodes are null.
	 */
	private int min(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if (node1.myPriority < node2.myPriority) {
			return index1;
		} else {
			return index2;
		}
	}

	public class Node {
		private T myItem;
		private double myPriority;

		private Node(T item, double priority) {
			myItem = item;
			myPriority = priority;
		}

		public T item() {
			return myItem;
		}

		public double priority() {
			return myPriority;
		}

		@Override
		public String toString() {
			return item().toString() + ", " + priority();
		}
	}

	public static void main(String[] args) {
		ArrayHeap<String> heap = new ArrayHeap<String>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 1);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);
		System.out.println(heap);
	}

}
