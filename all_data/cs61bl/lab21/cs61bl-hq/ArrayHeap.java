import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();

	public boolean isEmpty() {
		return contents.isEmpty();
	}

	public boolean contains(T item) {
		for (Node n : contents) {
			if (n != null) {
				if (n.item().equals(item)) {
					return true;
				}
			}
		}
		return false;
	}

	public double getPriority(T item) {
		for (Node n : contents) {
			if (n != null) {
				if (n.item().equals(item)) {
					return n.priority();
				}
			}
		}
		// not there
		return -1;
	}

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		if (isEmpty()) {
			contents.add(0, null);
			contents.add(1, new Node(item, priority));
		} else {
			contents.add(new Node(item, priority));
			int pos = contents.size() - 1;
			while (pos != 1 && getNode(pos).priority() < getNode(getParentOf(pos)).priority()) {
				bubbleUp(pos);
				pos = getParentOf(pos);
			}
		}
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		int count = 0;
		Node encountered = getNode(count);
		double smallestValue = encountered.priority();
		while (count < contents.size()) {
			if (getNode(count) != null && getNode(count).priority() < smallestValue) {
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
		boolean fix = false;
		Node result = getNode(1);
		Node currentPos = getNode(1);
		Node leftNode = getNode(getLeftOf(1));
		Node rightNode = getNode(getRightOf(1));
		setNode(1, contents.remove(contents.size() - 1));

		if (leftNode == null && rightNode == null) {
			
		} else if (!(leftNode == null) && rightNode == null) {
			if (currentPos.priority() > leftNode.priority()) {
				fix = true;
			}
		} else if (leftNode == null && !(rightNode == null)) {
			if (currentPos.priority() > rightNode.priority()) {
				fix = true;
			}
		} else {
			if (currentPos.priority() > leftNode.priority() && currentPos.priority() > rightNode.priority()) {
				fix = true;
			}
		}
		while (fix) {
			int newPos = min(getLeftOf(1), getRightOf(1));
			bubbleDown(1);
			currentPos = getNode(newPos);
			leftNode = getNode(getLeftOf(newPos));
			rightNode = getNode(getRightOf(newPos));

			if (leftNode == null && rightNode == null) {
				continue;
			} else if (!(leftNode == null) && rightNode == null) {
				if (currentPos.priority() > leftNode.priority()) {
					fix = true;
				}
			} else if (leftNode == null && !(rightNode == null)) {
				if (currentPos.priority() > rightNode.priority()) {
					fix = true;
				}
			} else {
				if (currentPos.priority() > leftNode.priority() && currentPos.priority() > rightNode.priority()) {
					fix = true;
				}
			}
		}

		return result;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (Node n : contents) {
			if (n != null) {
				if (n.item().equals(item)) {
					n.myPriority = priority;
				}
			}
		}
		ArrayList<Node> temp = new ArrayList<Node>();
		temp.addAll(contents);
		contents = new ArrayList<Node>();
		for (Node n : temp) {
			if (n != null) {
				this.insert(n.item(), n.priority());
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
		return 2 * i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return (2 * i) + 1;
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
	@SuppressWarnings("unused")
	private void setLeft(int index, Node n) {
		setNode(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	@SuppressWarnings("unused")
	private void setRight(int index, Node n) {
		setNode(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		swap(index, getParentOf(index));
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
		System.out.println(heap);
	}
}
