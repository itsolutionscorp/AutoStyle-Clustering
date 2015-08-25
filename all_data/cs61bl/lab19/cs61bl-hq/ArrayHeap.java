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
		if (contents.isEmpty()) {
			Node placeholder = new Node(null, 0.0);
			contents.add(placeholder);
		}
		Node toInsert = new Node(item, priority);
		contents.add(toInsert);
		int insertIndex = contents.lastIndexOf(toInsert);
		int parentIndex = getParentOf(insertIndex);
		if (insertIndex == 1) {
			return;
		}
		while (min(insertIndex, parentIndex) == insertIndex) {
			bubbleUp(insertIndex);
			insertIndex = parentIndex;
			parentIndex = getParentOf(insertIndex);
		}
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		int count = 0;
		Node encountered = getNode(count);
		double smallestValue = encountered.myPriority;
		while(count < contents.size()) {
			if (getNode(count) != null && getNode(count).myPriority < smallestValue) {
				encountered = getNode(count);
			}
			count++;
		}
		return encountered;
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		int count = 0;
		Node toBubble = getNode(count);
		while(count < contents.size()) {
			if (getNode(count) != null) {
				toBubble = getNode(count);
			}
			count++;
		}
		int replaceIndex = contents.indexOf(peek());
		swap(count, replaceIndex);
		setNode(count, null);
		return peek();
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		Node foundNode = null;
		for (Node n : contents) {
			if (n != null && n.myItem != null && n.myItem.equals(item)) {
				foundNode = n;
				n.myPriority = priority;
				break;
			}
		}
		int foundIndex = contents.indexOf(foundNode);
		int parentIndex = getParentOf(foundIndex);
		Node parentOfChanged = getNode(parentIndex);
		int leftIndex = getLeftOf(foundIndex);
		Node leftOfChanged = getNode(leftIndex);
		int rightIndex = getRightOf(foundIndex);
		Node rightOfChanged = getNode(rightIndex);
		if (parentOfChanged != null) {
			while (min(parentIndex, foundIndex) == foundIndex) {
				bubbleUp(foundIndex);
			}
		}
		if (leftOfChanged != null || rightOfChanged != null) {
			if (min(foundIndex, leftIndex) == leftIndex || min(foundIndex, rightIndex) == rightIndex) {
				bubbleDown(foundIndex);
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
		return (i * 2) + 1;
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
		int parentIndex = getParentOf(index);
		swap(index, parentIndex);
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		int childIndex;
		if (min(getLeftOf(index), getRightOf(index)) == getLeftOf(index)) {
			childIndex = getLeftOf(index);
		} else {
			childIndex = getRightOf(index);
		}
		swap(index, childIndex);
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
		heap.changePriority("c", 10);
		heap.changePriority("d", 11);
		System.out.println(heap);
	}

}
