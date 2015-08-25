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
	 * Swap the item you just added with its parent until it is larger than its parent, or until it is the new root. 
	 */
	public void insert(T item, double priority) {
		Node root = getNode(1);
		Node toBeAdded = new Node(item, priority);
		if (root != null) {
			setNode(contents.size()-1, toBeAdded);
			int curIndex = contents.size()-2;
			int parentIndex = getParentOf(curIndex);
			while (min(curIndex, parentIndex) != parentIndex && curIndex != 1 ) {
				bubbleUp(curIndex);
				curIndex = parentIndex;
				parentIndex = getParentOf(curIndex);
			}
		} else {
			setNode(1, toBeAdded);
		}
		
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		return contents.get(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 * Bubble down the new root until it is smaller than both its children. If you reach a point where you can either bubble down through the left or right child, you must choose the smaller of the two.
	 */
	public Node removeMin() {
		// TODO Complete this method!
		Node root = contents.get(1);
		swap (contents.size()-2, 1);
		contents.remove(contents.size()-2);
		int curIndex = 1;
		if (contents.size() <= 3) {
			return root;
		}
		while ((min(curIndex, getLeftOf(curIndex)) == getLeftOf(curIndex) || min(curIndex, getRightOf(curIndex)) == getRightOf(curIndex)) && getRightOf(curIndex)<contents.size()) {
			int nextIndex = min(getLeftOf(curIndex), getRightOf(curIndex));
			bubbleDown(curIndex);
			curIndex = nextIndex;
		}
		return root;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		for (int i=1; i<contents.size()-1; i++) {
			Node node = contents.get(i);
			if (node.myItem.equals(item)) {
				double oldPriority = node.myPriority;
				node.myPriority = priority;
				if (oldPriority < priority) {
					while (min(i, getLeftOf(i)) == getLeftOf(i) || min(i, getRightOf(i)) == getRightOf(i)) {
						int nextIndex = min(getLeftOf(i), getRightOf(i));
						bubbleDown(i);
						i = nextIndex;
					}
				} else {
					int parentIndex = getParentOf(i);
					while (min(i, parentIndex) != parentIndex && i != 1 ) {
						bubbleUp(i);
						i = parentIndex;
						parentIndex = getParentOf(i);
					}
				}
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
		return 2*i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		return 2*i + 1;
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
		contents.add(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		// TODO Complete this method!
		contents.add(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		swap(index, getParentOf(index));
	}

	/**
	 * Bubbles down the node currently at the given index.
	 * */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		if (getRightOf(index) < contents.size() && getLeftOf(index) < contents.size()) {
			swap(min(getLeftOf(index), getRightOf(index)), index);
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
		heap.removeMin();
		heap.changePriority("d", 10);
		System.out.println(heap);
		
		while (heap.peek() != null) {
			System.out.println(heap.removeMin().priority());
		}
		
	}

}
