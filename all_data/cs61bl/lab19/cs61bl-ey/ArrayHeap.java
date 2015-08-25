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

		if (contents.size() > 1) {
			// find first null index, insert here.
			int insertionPoint = contents.size() - 1;
			setNode(insertionPoint, new Node(item, priority));
			// if Inserted priority number isn't larger than parent, bubble up

			bubbleUp(insertionPoint);

		} else {
			this.setNode(1, new Node(item, priority));
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
		// removes the root
		Node smallest = getNode(1);
		contents.set(1, getNode(contents.size() - 2));
		contents.remove(contents.size() - 2);
		bubbleDown(1);
		return smallest;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		int magicIndex = 0;
		for(int i = 1; i < contents.size() - 2; i++) {
			if (item.equals(contents.get(i).item())) {
				magicIndex = i;
				break;
			}
		}
		getNode(magicIndex).myPriority = priority;
		bubbleDown(magicIndex);
		bubbleUp(magicIndex);
		
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
	private void setLeft(int index, Node n) {
		setNode(this.getLeftOf(index), n); // copied in from Array BST
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(this.getLeftOf(index), n); // copied from Array BST
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		while (min(index, getParentOf(index)) == index) {
			if (getNode(getParentOf(index)) == null)
				break;
			int temp = getParentOf(index);
			swap(getParentOf(index), index);
			index = temp;
		}

		// Node temp = getNode(getParentOf(index)); // saves the Parent
		// setNode(getParentOf(index),getNode(index)); // move Added node up
		// setNode(index,temp); // pushes parent down

	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {

		
		while(getNode(getRightOf(index)) != null && getNode(index) != null) {
			
			double leftDiff = 0, rightDiff = 0;
			
			if (getNode(getLeftOf(index)) != null){
				leftDiff = getNode(index).priority() - getNode(getLeftOf(index)).priority();
			}
			else leftDiff = getNode(index).priority();

			if (getNode(getRightOf(index)) != null){
				rightDiff = getNode(index).priority() - getNode(getRightOf(index)).priority();
			}
			else rightDiff = getNode(index).priority();
			
			if (leftDiff > rightDiff) {
				int temp = getLeftOf(index);
				swap (temp, index);
				index = temp;
			}
			else if (leftDiff < rightDiff) {
				int temp = getRightOf(index);
				swap (temp, index);
				index = temp;
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
//		heap.insert("c", 3);
//		heap.insert("i", 9);
//		heap.insert("g", 7);
//		heap.insert("d", 4);
//		heap.insert("a", 1);
//		heap.insert("h", 8);
//		heap.insert("e", 5);
//		heap.insert("b", 2);
//		heap.insert("c", 3);
//		heap.insert("d", 4);
		
		
		heap.insert("1", 1);
		heap.insert("2", 2);
		heap.insert("3", 3);
		heap.insert("4", 4);
		heap.insert("5", 5);
		heap.insert("6", 6);
		heap.insert("7", 7);
		heap.insert("8", 8);
		
		

//		System.out.println(heap);
//		System.out.println("_______________________");
//		heap.removeMin();

	
		System.out.println(heap);
		System.out.println("_______________________");
		heap.changePriority("7", 1);
		heap.changePriority("1", 23);
		System.out.println(heap);
	}

}