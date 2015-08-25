import java.util.ArrayList;

//import ArrayBST.Node;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int nextIndex;

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		Node toBeInserted = new Node(item, priority);

		if (contents.size() == 0) {
			contents.add(null);
			contents.add(toBeInserted);
			nextIndex = 2;
		} else {

			contents.add(toBeInserted);
			bubbleUp(nextIndex);
			nextIndex++;
		}
		/**
		 * if priority is greater than root's priority, stay the same. if
		 * priority is less than the root's priority, bubbleUp
		 */

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
		// bubbleDown(), swap with smaller value
		if (contents.size() == 0) {
			return null;
		} else {
			Node temp = contents.get(1);
			swap(1, contents.size() - 1);
			contents.remove(contents.size() - 1);
			bubbleDown(1);
			return temp;
		}

	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (Node n : contents) {
			if (n != null && n.myItem.equals(item)) {
				if (n.myPriority > priority) { // bubble up
					n.myPriority = priority;
					int position = contents.indexOf(n);
					bubbleUp(position);
				} else if (n.myPriority < priority) { // bubble down
					n.myPriority = priority;
					int position = contents.indexOf(n);
					bubbleDown(position);
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
		int leftPosition = (2 * i);
		return leftPosition;

	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		int rightPosition = (2 * i) + 1;
		// if (rightPosition < contents.size()) {
		return rightPosition;
		// } else {
		// return 0;
		// }
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		int parentPosition = i / 2;
		return parentPosition;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		int leftIndex = getLeftOf(index);
		setNode(leftIndex, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		int rightIndex = getRightOf(index);
		setNode(rightIndex, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	// private void bubbleUp(int index) {
	// while (index > 1) {
	// Node toBeBubbledUp = contents.get(index);
	// Node tempNode = contents.get(getParentOf(index));
	// if (toBeBubbledUp.myPriority < tempNode.myPriority) {
	// setNode(getParentOf(index), toBeBubbledUp);
	// setNode(index, tempNode);
	//
	// index = getParentOf(index);
	// } else {
	// return;
	// }
	// }
	// }

	public void bubbleUp(int index) {
		if (getParentOf(index) >= 1
				&& contents.get(index).myPriority < contents
						.get(getParentOf(index)).myPriority) {
			swap(index, getParentOf(index));
			bubbleUp(getParentOf(index));
		} else {
			return;
		}

	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {

		if (getRightOf(index) <= contents.size() - 1
				&& contents.get(index).myPriority > contents
						.get(getRightOf(index)).myPriority
				&& contents.get(getLeftOf(index)).myPriority > contents
						.get(getRightOf(index)).myPriority) {
			swap(index, getRightOf(index));
			bubbleDown(getRightOf(index));
		} else if (getLeftOf(index) <= (contents.size() - 1)
				&& contents.get(index).myPriority > contents
						.get(getLeftOf(index)).myPriority) {
			swap(index, getLeftOf(index));
			bubbleDown(getLeftOf(index));
		} else {
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
		ArrayHeap<String> heap = new ArrayHeap<String>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 10);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", -1);
		// heap.insert("d", 4);
		System.out.println(heap);
		// heap.changePriority("g", 0.002);
		// System.out.println(heap);
		// heap.removeMin();
		// System.out.println(heap.contents);
		// System.out.println(heap);
		// heap.changePriority("i", 1);
		while (heap.contents.size() > 1) {
			System.out.println(heap.removeMin());
		}

		// System.out.println(heap.removeMin());

	}
}
