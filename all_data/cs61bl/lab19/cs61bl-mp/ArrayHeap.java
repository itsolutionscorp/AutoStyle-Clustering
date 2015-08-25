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
		Node mynode = new Node(item, priority); 
		setNode(i, mynode);
		bubbleUp(i);
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
		Node original = peek();
		int i = 1;
		while (getNode(i) != null) {
			i++;
		} 
		swap(1, i - 1);
		setNode(i - 1, null);
		bubbleDown(1);
		return original;
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
			if (getNode(i).item().equals(item)) {
				if (priority >= getNode(i).priority()) {
					getNode(i).changePriority(priority);
					bubbleDown(i);
				} else {
					getNode(i).changePriority(priority);
					bubbleUp(i);
				}
			} i++;
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
		// TODO Complete this method!
		return (i / 2);
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
		// TODO Complete this method!
		if (index == 1) {
			return;
		} else if (getNode(index).priority() < getNode(getParentOf(index)).priority()) {
			swap(index, getParentOf(index));
			bubbleUp(getParentOf(index));
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		Node node = getNode(index);
		Node left = getNode(getLeftOf(index));
		Node right = getNode(getRightOf(index));
		if (left == null && right == null) {
			return;
		}
		int minLeft = min(index, getLeftOf(index));
		int minRight = min(index, getRightOf(index));
		int minChildren = min(getLeftOf(index), getRightOf(index));
		if (index == minLeft && index == minRight) {
			return;
		}
		else {
			swap(index, minChildren);
			bubbleDown(minChildren);
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

		public void changePriority(double newpriority) {
			myPriority = newpriority;
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
		heap.removeMin();
		System.out.println(heap);
		System.out.println("===============================");
		ArrayHeap<String> heap2 = new ArrayHeap<String>();
		heap2.insert("c", 10);
		heap2.insert("i", 9);
		heap2.insert("g", 200);
		heap2.insert("d", 4);
		heap2.insert("a", 5);
		heap2.insert("h", 5);
		heap2.insert("e", 50);
		heap2.insert("b", 2);
		System.out.println(heap2);
		heap2.changePriority("d", 6);
		System.out.println(heap2);
		heap2.removeMin();
		System.out.println(heap2);
		
		System.out.println("===============================");
		ArrayHeap<String> heap3 = new ArrayHeap<String>();
		heap3.insert("c", 10);
		heap3.insert("i", 9);
		heap3.insert("g", 200);
		heap3.insert("d", 4);
		heap3.insert("a", 5);
		heap3.insert("h", 5);
		heap3.insert("e", 50);
		heap3.insert("b", 2);
		System.out.println(heap3);
		while (heap3.getNode(1) != null) {
			System.out.println(heap3.removeMin().item());
		} 
	}

}
