import java.util.ArrayList;



/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int indexCounter = 0;

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		
		 Node root = this.getNode(1);
	        if (root != null) {
	        	Node newNode = new Node(item, priority);
				this.setNode(indexCounter, newNode);
	        } else {
				Node newNode = new Node(item, priority);
	            this.setNode(1, newNode);
	        }
	        indexCounter++;
		
		
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		double smallest = contents.get(0).priority();
		Node smallestNode = contents.get(0);
		for (Node treeNode: contents){
			if (treeNode.priority() < smallest){
				smallest = treeNode.priority();
				smallestNode = treeNode;
			}
		}
		return smallestNode;
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		double smallest = contents.get(0).priority();
		Node smallestNode = contents.get(0);
		int index = 0;
		int counter = 0;
		for (Node treeNode: contents){
			if (treeNode.priority() < smallest){
				smallest = treeNode.priority();
				smallestNode = treeNode;
				index = counter;
			}
			counter ++;
		}
		
		contents.remove(index);
		
		return smallestNode;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (Node treenode: contents){
			if (treenode.item().equals(item)){
				treenode.myPriority = priority;
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
		if (i*2 >= contents.size()){
			return 0;
		}
		else {
			return (i*2);
		}
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		if ((i*2)+1 >= contents.size()){
			return 0;
		}
		else {
			return ((2*i)+1);
		}
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		if (i/2 >= contents.size() || i/2 <= contents.size()){
			return 0;
		}
		else {
			return i/2;
		}
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		if (index*2 >= contents.size()){
			setNode(index*2, n);
		}
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		if ((index*2)+1 >= contents.size()){
			setNode((index*2)+1, n);
		}
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		int parentI = getParentOf(index);
		if (index == 0){
			return;
		}
		Node parent = getNode(parentI);
		if (getNode(index).priority() >= parent.priority()){
			swap(index, parentI);
		}
	}
	

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		Node myNode = getNode(index);
		
		if (getNode(getLeftOf(index))==null||getNode(getRightOf(index)) == null){
			return;
		}
		
		if (getNode(getLeftOf(index)).priority() < getNode(index).priority()){
			swap(getLeftOf(index), index);
		}
		
		if (getNode(getRightOf(index)).priority() < getNode(index).priority()){
			swap(getRightOf(index), index);
		}
		
		if (getNode(getRightOf(getRightOf(index))) == null && getNode(getLeftOf(getLeftOf(index))) == null){
			if (min(getRightOf(getRightOf(index)), getLeftOf(getLeftOf(index))) == getRightOf(getRightOf(index))){
				swap (getRightOf(getRightOf(index)), getLeftOf(getLeftOf(index)));
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
		
		
	}

}
