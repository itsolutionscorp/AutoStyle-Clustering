import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int heapSize = 0;
	private static final int ROOT_INDEX = 1;

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		insertNode(new Node(item, priority));
		bubbleUp(finalNodeIndex());
	}

	public void insertNode(Node n) {
		setNode(finalNodeIndex() + 1, n);
		heapSize++;
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		return getNode(ROOT_INDEX);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		Node toReturn = peek();
		pullLastNode();
		bubbleDown(ROOT_INDEX);
		return toReturn;
	}

	public void pullLastNode() {
		setNode(ROOT_INDEX, getNode(finalNodeIndex()));
		setNode(finalNodeIndex(), null);
		heapSize--;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (int i = ROOT_INDEX; i < finalNodeIndex(); i++) {
			if (getNode(i).myItem.equals(item)) {
				boolean up = true;
				if (priority > getNode(i).myPriority) {
					up = false;
				}
				getNode(i).myPriority = priority;
				if (up) {
					bubbleUp(i);
				} else {
					bubbleDown(i);
				}
				return;
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
		return i * 2;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return i * 2 + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		return i / 2;
	}

	/**
	 * Returns the index of node in the bottom-right of the tree.
	 */
	private int finalNodeIndex() {
		return heapSize;
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
		Node current = getNode(index);
		int parentIndex = getParentOf(index);
		if (getNode(parentIndex) == null) {
			return;
		}
		if (current.myPriority < contents.get(parentIndex).myPriority) {
			setNode(index, contents.get(parentIndex));
			setNode(parentIndex, current);
			bubbleUp(parentIndex);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		Node current = getNode(index);
		int leftIndex = getLeftOf(index);
		int rightIndex = getRightOf(index);
		if (getNode(leftIndex) == null && getNode(rightIndex) == null) {
			return;
		}
		int minChild = min(rightIndex, leftIndex);
		if (current.myPriority > getNode(minChild).myPriority) {
			setNode(index, getNode(minChild));
			setNode(minChild, current);
			bubbleDown(minChild);
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

	private ArrayList<T> sort() {
		ArrayList<T> items = new ArrayList<T>();
		ArrayList<Double> priorities = new ArrayList<Double>();
		int staticSize = heapSize;
		for (int count = 0; count < staticSize; count++) {
			items.add(peek().myItem);
			priorities.add(removeMin().myPriority);
		}
		for (int count = 0; count < staticSize; count++) {
			insert(items.get(count), priorities.get(count));
		}
		return items;
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
		System.out.println(heap.sort());
		System.out.println(heap);
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		System.out.println(heap);
		heap.insert("q", 6.5);
		heap.insert("y", 4.5);
		heap.insert("j", 7.5);
		heap.insert("k", 2.5);
		heap.insert("l", 3.5);
		heap.insert("m", 5.5);
		heap.insert("n", 1.5);
		heap.insert("z", 8.5);
		heap.insert("y", 4.5);
		heap.insert("z", 3.5);
		System.out.println(heap);
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		System.out.println(heap);
		heap.changePriority("d", 6);
		heap.changePriority("y", 3);
		heap.changePriority("m", 7.5);
		heap.changePriority("q", 9);
		heap.changePriority("e", 2);
		System.out.println(heap);

	}

}
