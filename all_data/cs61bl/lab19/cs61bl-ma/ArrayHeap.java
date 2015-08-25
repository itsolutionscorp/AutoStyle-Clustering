import java.util.ArrayList;
import java.util.LinkedList;

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
		if (getNode(1) == null) {
			setNode(1, new Node(item, priority));
		} else {
			int[] lastNode = findLastNode();
			for (int i : lastNode) {
				System.out.print(i + " ");
			}
			System.out.println();
			if (getNode(getLeftOf(lastNode[0])) == null) {
				setLeft(lastNode[0], new Node(item, priority));
				bubbleUp(getLeftOf(lastNode[0]));
			} else {
				setRight(lastNode[0], new Node(item, priority));
				bubbleUp(getRightOf(lastNode[0]));
			}
		}
	}
	
	public int[] findLastNode() {
		LinkedList<Integer> nodeQueue = new LinkedList<Integer>();
		nodeQueue.add(1);
		return findLastNodeHelper(nodeQueue, 0);
	}
	
	public int[] findLastNodeHelper(LinkedList<Integer> nodeQueue, int last) {
		int n = nodeQueue.poll();
		if (getNode(getRightOf(n)) == null || getNode(getRightOf(n)) == null) {
			int[] nodes = {n, last};
			return nodes;
		} else {
			nodeQueue.add(getLeftOf(n));
			nodeQueue.add(getRightOf(n));
			return findLastNodeHelper(nodeQueue, n);
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
		Node min = getNode(1);
		int[] lastNode = findLastNode();
		if (getNode(getLeftOf(lastNode[0])) == null) {
			swap(1, getRightOf(lastNode[1]));
			setNode(getRightOf(lastNode[1]), null);
		} else {
			swap(1, getLeftOf(lastNode[0]));
			setNode(getLeftOf(lastNode[0]), null);
		}
		bubbleDown(1);
		return min;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		int matchingNode = findMatchingNode(item, 1); 
		if (matchingNode == 0) {
			System.out.println("There is no node with that item."); 
		} else {
			Node newNode = new Node(item, priority); 
			setNode(matchingNode, newNode);
			if (matchingNode == 1) {
				bubbleDown(1);
			} else if (min(matchingNode, this.getParentOf(matchingNode)) == matchingNode) {
				bubbleUp(matchingNode);
			} else {
				bubbleDown(matchingNode);
			}
		}
	
	}
	
	public int findMatchingNode(T item, int index) {
		if (getNode(index) == null) {
			return 0;
		} else if (getNode(index).item().equals(item)) {
			return index;
		} else {
			int left = findMatchingNode(item, getLeftOf(index));
			int right = findMatchingNode(item, getRightOf(index));
			if (left != 0 || right != 0) {
				if (left != 0) {
					return left;
				} else {
					return right;
				}
			} else {
				return 0; 
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
	
		return 2*i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return 2*i+1;
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
		while(getParentOf(index) != 0 && min(index, getParentOf(index)) == index) {
			swap(index, getParentOf(index));
			index = getParentOf(index);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		while( (getNode(getLeftOf(index)) != null || getNode(getRightOf(index)) != null) &&
				!(min(index, getLeftOf(index)) == index && min(index, getRightOf(index)) == index) ) {
			int smallerNodeIndex;
			if (min(getLeftOf(index), getRightOf(index)) == getLeftOf(index)) {
				smallerNodeIndex = getLeftOf(index);
			} else {
				smallerNodeIndex = getRightOf(index);
			}
			swap(index, smallerNodeIndex); 
			index = smallerNodeIndex;
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
		heap.removeMin();
		System.out.println(heap);
		heap.changePriority("b", 20.0);
		System.out.println(heap);
		heap.changePriority("e", 1.0);
		System.out.println(heap);
		heap.changePriority("g", 2.0);
		System.out.println(heap);

	}

}
