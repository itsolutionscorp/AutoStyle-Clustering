import java.util.ArrayList;
import java.util.Iterator;


/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	
	public void print() {
		for (Node n : contents) {
			if (n == null) {
				System.out.println("NULL");
			}
			else {
				System.out.println(n);
			}
		}
	}
	
	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		//insert item into bottom row
		Node n = new Node(item, priority);
		if(contents.isEmpty()) {
			setNode(1, n);
		}
		else {
			setNode(contents.size(), n);
		}
		//bubble up until its larger than its parent or it become new root
		bubbleUp(contents.size() - 1);
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		return getNode(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		Node returnNode = peek();
		if (contents.size() == 2) {
			contents.remove(1);
		}
		else if (contents.size() > 2){
			int lastIndex = contents.size() - 1;
			swap(1, lastIndex);
			contents.remove(lastIndex);
			bubbleDown(1);
		}
		return returnNode;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		Iterator<Node> iter = contents.iterator();
		int counter = 0;
		while(iter.hasNext()) {
			Node current = iter.next();
			if(current != null && item.equals(current.item())) {
				current.setPriority(priority);
				bubbleDown(counter);
				bubbleUp(counter);
				break;
			}
			counter++;
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
		while (index >= contents.size()) {
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
		return 2*i + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		return i/2;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		setNode(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		if (index == 1) {
			return;
		}
		int parent = getParentOf(index);
		if (parent == min(index, parent)) {
			return;
		}
		else {
			swap(index, parent);
			bubbleUp(parent);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		int left = getLeftOf(index);
		int right = getRightOf(index);
		if(left == min(index, left) || right == min(index, right)) {
			int left_or_right = min(left, right);
			swap(index, left_or_right);
			bubbleDown(left_or_right);
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
		}
		else if (node1.myPriority < node2.myPriority) {
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
		
		public void setItem(T i) {
			myItem = i;
		}
		
		public void setPriority(double p) {
			myPriority = p;
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
		System.out.println("-------------------");
		
		heap.removeMin();
		System.out.println(heap);
		System.out.println("-------------------");
		heap.removeMin();
		System.out.println(heap);
		System.out.println("-------------------");
		heap.removeMin();
		System.out.println(heap);
		System.out.println("-------------------");
		
		heap.insert("b", 2);
		System.out.println(heap);
		System.out.println("-------------------");
		heap.changePriority("b", 10);
		System.out.println(heap);
		System.out.println("-------------------");
		heap.changePriority("b", 0);
		System.out.println(heap);
		
		
		// Heapsort
		ArrayHeap<Integer> heap2 = new ArrayHeap<Integer>();
		heap2.insert(3, 3);
		heap2.insert(9, 9);
		heap2.insert(7, 7);
		heap2.insert(4, 4);
		heap2.insert(1, 1);
		heap2.insert(8, 8);
		heap2.insert(5, 5);
		heap2.insert(2, 2);
		heap2.insert(3, 3);
		heap2.insert(4, 4);
		int[] l = new int[10];
		for (int i = 0; i < 10; i++) {
			l[i] = heap2.removeMin().item();
		}
		System.out.println(heap2); // should print an empty line since heap2 is now empty
		for (int i : l) {
			System.out.print(i + " "); // should print "1 2 3 3 4 4 5 7 8 9"
		}
	}

}
