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
        // add the item to the end of the arraylist
		Node toInsert = new Node(item, priority);
		if (this.getNode(1) == null) {
			setNode(1, toInsert);
			return;
		}
		setNode(contents.size() - 1, toInsert);
		bubbleUp(contents.size() - 2);
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		if (getNode(1) != null) return getNode(1);
		else return null;
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method!
		Node temp = getNode(contents.size() - 2);
		swap(1, contents.size() - 2);
		setNode(contents.size() - 2, null);
		bubbleDown(1);
		return temp;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		int i;
		for (i = 1; i < contents.size() - 1; i++) {
			if (getNode(i).myItem.equals(item)) {
				getNode(i).myPriority = priority;
				break;
			}
		}
		swap(1, i);
		bubbleDown(1);
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
		return 2 * i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		return 2 * i + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		// TODO Complete this method!
		return i / 2;
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
	private void setRight(int index, Node n) {
		// TODO Complete this method!
		setRight(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		if (index > 1){
		    double myPri = getNode(index).priority();
		    double parentPri = getNode(getParentOf(index)).priority();
		    if (myPri < parentPri) {
			    swap(index, getParentOf(index));
			    bubbleUp(getParentOf(index));
		    }
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		System.out.println(index + " " + getNode(index));
		if (getNode(index) != null && getNode(getLeftOf(index)) != null&&
				getNode(getRightOf(index)) != null){
		double myPri = getNode(index).priority();
		double leftPri = getNode(getLeftOf(index)).priority();
		double rightPri = getNode(getRightOf(index)).priority();
		if (myPri > leftPri && myPri > rightPri) {
			if (leftPri < rightPri) {
				swap(index, getLeftOf(index));
				bubbleDown(getLeftOf(index));
			}
			else {
				swap (index, getRightOf(index));
				bubbleDown(getRightOf(index));
			}
		} else if (myPri > leftPri && myPri <= rightPri) {
			swap (index, getLeftOf(index));
			bubbleDown(getLeftOf(index));
		} else if (myPri <= leftPri && myPri > rightPri) {
			swap (index, getRightOf(index));
			bubbleDown(getRightOf(index));
		} else return;
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
		for (int i = 1; i < 11; i++) System.out.println((heap.getNode(i)));
		System.out.println(heap);
		System.out.println(heap.removeMin());
		System.out.println();
		for (int i = 1; i < 11; i++) System.out.println((heap.getNode(i)));
		System.out.println(heap);
		heap.changePriority("g", 8);
		System.out.println();
		for (int i = 1; i < 11; i++) System.out.println((heap.getNode(i)));
        System.out.println(heap);

	}

}
