import java.awt.print.Printable;
import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int emptyIndex = 1;
	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		setNode(emptyIndex, new Node(item, priority));
		bubbleUp(emptyIndex);
		emptyIndex++;
	}


	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		return this.getNode(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method
		int lastIndex = emptyIndex -1;
		Node toBeRemoved = getNode(1);
		if(lastIndex == 1){
			setNode(1, null);
			return toBeRemoved;
		}
		swap(1, lastIndex);
		setNode(lastIndex, null);
		bubbleDown(1);
		emptyIndex--;
		return toBeRemoved;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		int index = 0;
		for(Node n : contents){
			if (index == 0){
				index++;
				continue;
			}
			if(n.item().equals(item)){
				double diff = priority-n.myPriority;
				n.myPriority = priority;
				if(diff > 0){
					bubbleDown(index);
				}else{
					bubbleUp(index);
				}
				return;
			}
			index++;
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
		// TODO Complete this method!
		return 2*i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		return 2*i+1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		// TODO Complete this method!
		return i/2;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		// TODO Complete this method!
		setNode(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int inde, Node n) {
		// TODO Complete this method!
		setNode(getRightOf(inde), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		if (getNode(index) == null || index == 1){
			return;
		}
		int parentIndex = getParentOf(index);
		if (min(index, parentIndex) == index){
			swap(index,parentIndex);
			bubbleUp(parentIndex);
		}else{
			return;
		}
 	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		if (getNode(index) == null){
			return;
		}
		int leftIndex = getLeftOf(index);
		int rightIndex = getRightOf(index);
		if (min(rightIndex, leftIndex) == rightIndex){
			bubbleDownHelper(rightIndex);
			bubbleDown(rightIndex);
		}else{
			bubbleDownHelper(leftIndex);
			bubbleDown(leftIndex);
		
		}
	}
	
	private void bubbleDownHelper(int sonIndex){
		if (getNode(sonIndex) == null){
			return;
		}
		int myIndex = getParentOf(sonIndex);
		if (min(sonIndex, myIndex) == sonIndex){
			swap(myIndex,sonIndex);
		}else{
			return;
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
		ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
		heap.insert(3, 3);
		heap.insert(9, 9);
		heap.insert(7, 7);
		heap.insert(4, 4);
		heap.insert(1, 1);
		heap.insert(8, 8);
		heap.insert(5, 5);
		heap.insert(2, 2);
		heap.insert(3, 3);
		heap.insert(4, 4);
		
		for(int i =0 ; i<10 ;i++){
		System.out.println(heap.removeMin().myItem);
	    
		}

		System.out.println(heap);
	}

}