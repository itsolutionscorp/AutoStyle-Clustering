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
		// TODO Complete this method!
		if (item == null) {
			return;
		}
		if (getLowestIndex() == 0) {
			setNode(0, null);
		}
		int lowest = getLowestIndex() + 1;
		setNode(lowest, new Node(item, priority));
		bubbleUp(lowest);
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		return getNode(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method!
		Node ret = getNode(1);
		if (ret == null) {
			return null;
		}
		int lowest = getLowestIndex();
		swap(1, lowest);
		setNode(lowest, null);
		bubbleDown(1);
		return ret;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		for (Node n : contents) {
			if ((n != null) && (n.item().equals(item))) {
				n.setPriority(priority);
				int changedIndex = contents.indexOf(n);
				bubbleUp(changedIndex);
				bubbleDown(changedIndex);
				break;
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
		// TODO Complete this method!
		return i * 2;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		return i * 2 + 1;
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
		setNode(index * 2, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		// TODO Complete this method!
		setNode(index * 2 + 1, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		int i = index;
		while ((getNode(getParentOf(i)) != null)
				&& (getNode(getParentOf(i)).priority() > getNode(i).priority())) {
			swap(i, getParentOf(i));
			i = getParentOf(i);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		int i = index;
		while (i != 0 && getNode(getLeftOf(i)) != null) {
			int min = min(getLeftOf(i), getRightOf(i));
			if (getNode(i).priority() > getNode(min).priority()) {
				swap(i, min);
				i = min;
			}
			else {
				return;
			}
		}

	}

	private int getLowestIndex() {
		int ret = 0;
		for (Node n : contents) {
			if (n != null) {
				ret++;
			}
		}
		return ret;
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
		heap.insert(null, 9000);
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
//		while (heap.peek() != null) {
//			System.out.println(heap.removeMin());
//		}
		
		heap.changePriority("a", 9000);
		System.out.println(heap);
		heap.changePriority("a", 2.5);
		System.out.println(heap);
//		heap.removeMin(); // should remove (a, 1.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (b, 2.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (c, 3.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (c, 3.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (d, 4.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (d, 4.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (e, 5.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (g, 7.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (h, 8.0)
//		System.out.println(heap);
//		heap.removeMin(); // should remove (i, 9.0)
//		System.out.println(heap); // should print nothing
//		heap.removeMin(); // should return nothing
		
	}

}
