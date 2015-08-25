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
		int i = 1;
		while (getNode(i) != null) {
			i++;
		}
		Node newNode = new Node(item, priority);
		setNode(i, newNode);
		bubbleUp(i);
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		if (contents.size() < 2) return null;
		return contents.get(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method!
		Node result = contents.get(1);
		int maxIndex = contents.size() - 1;
		//maxIndex = 8
		while (contents.get(maxIndex) == null) {
			maxIndex = maxIndex - 1;
		}
		Node lastNode = contents.remove(maxIndex);
		if (contents.size() <= 2) return lastNode;
		contents.set(1, lastNode);
		bubbleDown(1);
		return result;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		int i = 1;
		while (getNode(i) != null) {
			if (getNode(i).item().equals(item)) break;
			i++;
		}
		if (i>=contents.size()) {
			System.err.println("No element");
			return;
		}
		Node newPriority = new Node(item, priority);
		setNode(i, newPriority);
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
		setNode(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		// TODO Complete this method!
		setNode(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		int current = index;
		
		while(!(getNode(current) == null || getNode(getParentOf(current)) == null)&&
			getNode(getParentOf(current)).priority() >
									getNode(current).priority()) {
			swap(current, getParentOf(current));
			current = getParentOf(current);
			
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		int current = index;
		if (getRightOf(current) >= contents.size() || getLeftOf(current) >= contents.size()) return;
		if (contents.get(getRightOf(current)) == null && contents.get(getLeftOf(current)) == null)
			return;
		int minIndex = min(getRightOf(current), getLeftOf(current));
		while (contents.get(minIndex).priority() < contents.get(current).priority()) {
			swap(current, minIndex);
			current = minIndex;
			if (getRightOf(current) >= contents.size() || getLeftOf(current) >= contents.size()) return;
			if (contents.get(getRightOf(current)) == null && contents.get(getLeftOf(current)) == null)
				return;
			minIndex = min(getRightOf(current), getLeftOf(current));
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
		ArrayList<String> removal = new ArrayList<String>();
		
		ArrayHeap<String> heap2 = new ArrayHeap<String>();
		System.out.println("peek for 2 " + heap2.peek());
		while (heap.peek() != null) {
			removal.add(heap.removeMin().item());
		}
		System.out.println(removal);
		System.out.println("peek result " + heap.peek());
		System.out.println(heap);
		System.out.println(removal.size());
		removal.remove(0);
		System.out.println(removal.size());
		
	}

}
