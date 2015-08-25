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
		if (contents.isEmpty()){
			setNode(1,new Node (item, priority));
		}
		int lastIndex = 1;
		for (int index = 0; index < contents.size(); index++){
			if (contents.get(index) != null){
				lastIndex = index;
			}
		}
		setNode(lastIndex + 1, new Node(item, priority));
		bubbleUp(lastIndex);
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		if (contents.isEmpty()){
			return null;
		}
		return contents.get(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		if (getNode(1) == null){
			System.out.println("hello");
			return null;
		}else{
			Node last = contents.get(1);
			int lastIndex = 1;
			for (int index = 0; index < contents.size(); index++){
				if (contents.get(index) != null){
					last = getNode(index);
					lastIndex = index;
				}
			}
			Node current = contents.get(1);
			swap(1, lastIndex);
			setNode(lastIndex, null);
			bubbleDown(1);
			return current;
		}
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (int index = 1; index < contents.size(); index++){
			if (getNode(index).item().equals(item)){
				setNode(index, new Node(item, priority));
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
		return 2*i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return (2*i) + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		return i / 2;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		setNode(2*index, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode((2*index)+1, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		if (index == 1){
			
		}else{
			int parentIndex = getParentOf(index);
			Node parent = getNode(getParentOf(index));
			Node current = getNode(index);
			if (parent.priority() > current.priority()){
				setNode(index, parent);
				setNode(getParentOf(index), current);
				bubbleUp(parentIndex);
			}
			
		}	
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		int leftIndex = getLeftOf(index);
		int rightIndex = getRightOf(index);
		Node rightChild = getNode(getRightOf(index));
		Node leftChild = getNode(getLeftOf(index));
		Node current = getNode(index);
		if (leftIndex > contents.size() - 1 || rightIndex > contents.size() -1){
			
		}else if (getNode(leftIndex) == null || getNode(rightIndex) == null){
			if (getNode(leftIndex)!=null && getNode(rightIndex) == null){
				if (current.priority()>leftChild.priority()){
					swap(index, leftIndex);
				}
			}else if (getNode(leftIndex)==null && getNode(rightIndex) != null){
				if (current.priority() > rightChild.priority()){
					swap(index, rightIndex);
				}
			}
		}else{
			if (rightChild.priority() < leftChild.priority()){
				if (current.priority() > rightChild.priority()){
					setNode(index, rightChild);
					setNode(getRightOf(index), current);
					bubbleDown(rightIndex);
					
				}
			}else{
				if (current.priority() > leftChild.priority()){
					setNode(index, leftChild);
					setNode(getLeftOf(index), current);
					bubbleDown(leftIndex);
				}
			}
		
		}
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
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());
		System.out.println(heap.removeMin().priority());

		//while (heap.removeMin() != null){
		//	System.out.println(heap.removeMin().priority());
			
		//}
		
	}

}

