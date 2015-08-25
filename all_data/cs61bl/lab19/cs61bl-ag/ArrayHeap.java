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
		Node newNode = new Node(item,priority);
		if (contents.isEmpty()) {
			setNode(contents.size() + 1, newNode);
		}
		else {
			setNode(contents.size() - 1, newNode);
		}
		int parentIndex = getParentOf(contents.indexOf(newNode));
		if (getNode(parentIndex) != null) {
			bubbleUp(contents.indexOf(newNode));
		}
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
		Node toReturn = getNode(1);
		swap(1, contents.size() - 2);
		Node root = getNode(1);
		contents.remove(contents.size()-2);
		int leftIndex = getLeftOf(1);
		int rightIndex = getRightOf(1);
		if(getNode(leftIndex)!=null || getNode(rightIndex)!=null){
			bubbleDown(1);
		}
		return toReturn;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		int changeIndex = -1;
		for (Node n : contents) {
			if (n != null) {
				if (n.myItem.equals(item)) {
					changeIndex = contents.indexOf(n);
				}
			}
		}
		Node toChange = getNode(changeIndex);
		toChange.myPriority = priority;
		int parentIndex = getParentOf(changeIndex);
		int leftIndex = getLeftOf(changeIndex);
		int rightIndex = getRightOf(changeIndex);
		if(getNode(parentIndex) != null) {
			if(toChange.priority() < getNode(parentIndex).priority()) {
				bubbleUp(changeIndex);
			}
		}
		if(getNode(leftIndex) != null || getNode(rightIndex) != null) {
			bubbleDown(changeIndex);
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
		return i*2;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return i*2+1;
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
		setNode(index*2,n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(index*2+1,n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		Node nautilus = getNode(index);
		int parentIndex = getParentOf(index);
		Node parent = getNode(parentIndex);
		if (nautilus != null && parent != null && nautilus.priority() < parent.priority()) {
			swap(index, parentIndex);
			bubbleUp(parentIndex);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		Node nautilus = getNode(index);
		int leftIndex = getLeftOf(index);
		int rightIndex = getRightOf(index);
		Node left = getNode(leftIndex);
		Node right = getNode(rightIndex);
		if (left!=null && right!=null && nautilus != null) {
			if (nautilus.priority() > left.priority() && nautilus.priority() > right.priority()) {
				if (left.priority() < right.priority()) {
					swap(index, leftIndex);
					bubbleDown(leftIndex);
					return;
				}
				else {
					swap(index, rightIndex);
					bubbleDown(rightIndex);
					return;
				}
			}
		}
		if (left!=null && nautilus != null) {
			if(nautilus.priority() > left.priority()){
				swap(index, leftIndex);
				bubbleDown(leftIndex);
				return;
			}
		}
		if (right!=null && nautilus != null) {
			if(nautilus.priority() > right.priority()){
				swap(index, rightIndex);
				bubbleDown(rightIndex);
				return;
			}
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
		heap.insert("f", 3);
		heap.insert("d", 4);
		System.out.println(heap);
		System.out.println("=======================");
		System.out.println(heap.peek());
		heap.removeMin();
		heap.removeMin();
		System.out.println(heap.peek());
		System.out.println(heap);
		System.out.println("=======================");
		heap.changePriority("h", 9);
		heap.changePriority("i", 10);
		System.out.println(heap);
		System.out.println("=======================");
		System.out.println("HeapSort: ");
		ArrayHeap.Node n = heap.removeMin();
		while(n!=null){
			System.out.println(n);
			n = heap.removeMin();
		}
	}
}
