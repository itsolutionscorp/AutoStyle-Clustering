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
			if (n != null && n.item().equals(item)) {
				// skip the 0th one
				return true;
			}
		}
		return false;
	}
	
	public double getPriority(T item) {
		for (Node n : contents) {
			if (n != null && n.item().equals(item)) {
				// skip the 0th one
				return n.myPriority;
			}
		}
		// not there
		return -1;
	}
	
	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		if (contents.isEmpty()) {
			// edge case: beginning! set index 0 to null
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
		return getNode(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		Node rtn = getNode(1);
		// assassinate the president. and replace it with the last guy
		setNode(1, contents.remove(contents.size() - 1));
		int pos = 1;
		Node current = getNode(pos);
		Node left = getNode(getLeftOf(pos));
		Node right = getNode(getRightOf(pos));
		boolean left_null = (left == null);
		boolean right_null = (right == null);
		boolean smaller_than_left = false;
		boolean smaller_than_right = false;
		boolean need_fix = false;

		if (left_null && right_null) {
			need_fix = false;
		} else if (!left_null && right_null) {
			// left is fine, right is null
			// check left
			smaller_than_left = (current.priority() < left.priority());
			if (smaller_than_left) {
				need_fix = false;
			} else {
				need_fix = true;
			}
		} else if (left_null && !right_null) {
			// left is null, right is fine
			// check left
			smaller_than_right = (current.priority() < right.priority());
			if (smaller_than_right) {
				need_fix = false;
			} else {
				need_fix = true;
			}
		} else {
			// both there
			smaller_than_left = (current.priority() < left.priority());
			smaller_than_right = (current.priority() < right.priority());
			if (smaller_than_left && smaller_than_right) {
				need_fix = false;
			} else if (!smaller_than_left && smaller_than_right) {
				need_fix = true;
			} else if (smaller_than_left && !smaller_than_right) {
				need_fix = true;
			} else {
				// greater than both
				need_fix = true;
			}
		}

		while (!left_null && !right_null && need_fix) {
			int pos_save = min(getLeftOf(pos), getRightOf(pos));
			bubbleDown(pos);
			pos = pos_save;
			// update pos
			// update need_fix
			current = getNode(pos);
			left = getNode(getLeftOf(pos));
			right = getNode(getRightOf(pos));
			left_null = (left == null);
			right_null = (right == null);
			smaller_than_left = false;
			smaller_than_right = false;
			need_fix = false;

			if (left_null && right_null) {
				need_fix = false;
			} else if (!left_null && right_null) {
				// left is fine, right is null
				// check left
				smaller_than_left = (current.priority() < left.priority());
				if (smaller_than_left) {
					need_fix = false;
				} else {
					need_fix = true;
				}
			} else if (left_null && !right_null) {
				// left is null, right is fine
				// check left
				smaller_than_right = (current.priority() < right.priority());
				if (smaller_than_right) {
					need_fix = false;
				} else {
					need_fix = true;
				}
			} else {
				// both there
				smaller_than_left = (current.priority() < left.priority());
				smaller_than_right = (current.priority() < right.priority());
				if (smaller_than_left && smaller_than_right) {
					need_fix = false;
				} else if (!smaller_than_left && smaller_than_right) {
					need_fix = true;
				} else if (smaller_than_left && !smaller_than_right) {
					need_fix = true;
				} else {
					// greater than both
					need_fix = true;
				}
			}						
		}

		return rtn;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (Node n : contents) {
			if (n != null && n.item().equals(item)) {
				// skip the 0th one
				n.myPriority = priority;
			}
		}
		// if it is not in the heap, we do nothing
		ArrayList<Node> temp = new ArrayList<Node>();
		temp.addAll(contents);
		contents = new ArrayList<Node>();
		for (Node n : temp) {
			if (n != null) {
				insert(n.item(), n.priority()); 
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
		return 2 * i + 1;
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
		swap(index, getParentOf(index));
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		swap(index, min(getLeftOf(index), getRightOf(index)));
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

		System.out.println("***************************************");
		heap.changePriority("c", -1000);
		System.out.println(heap);

		System.out.println("***************************************");
		heap.changePriority("d", -2000);
		System.out.println(heap);

		System.out.println("***************************************");
		System.out.println("heap.removeMin(): " + heap.removeMin());
		System.out.println(heap);


		System.out.println("***************print numbers out from smallest to largest************************");
		ArrayHeap<Integer> heap2 = new ArrayHeap<Integer>();
		heap2.insert(1, 1);
		heap2.insert(2, 2);
		heap2.insert(3, 3);
		heap2.insert(4, 4);
		heap2.insert(5, 5);
		heap2.insert(6, 6);
		heap2.insert(7, 7);
		heap2.insert(8, 8);
		heap2.insert(9, 9);
		heap2.insert(10, 10);
		System.out.println(heap2);
		for (int i = 0; i < 10; i++) {
			System.out.println("i: " + heap2.removeMin());
		}



	}

}
