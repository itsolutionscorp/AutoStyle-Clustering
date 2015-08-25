import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int nextIndex = 1;
	private HashMap<T, Integer> getIndex = new HashMap<T, Integer>();
	
	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		Node toInsert = new Node(item, priority);
		
		setNode(nextIndex, toInsert);
		bubbleUp(nextIndex);
		getIndex.put(item, nextIndex);
		nextIndex++;
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
		Node toRemove = getNode(1);
		int lastIndex = nextIndex - 1;
		
		System.out.println("removing " + toRemove);
		
		swap(1, lastIndex);
		T item = contents.get(lastIndex).myItem;
		getIndex.remove(item);
		contents.remove(lastIndex);
		
		bubbleDown(1);
		nextIndex -= 1;
		return toRemove;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		for (int i = 1; i < nextIndex; i++) {
			Node currentNode = contents.get(i);
			T currentItem = currentNode.item();
			
			System.out.println("currentItem " + currentItem);
			
			if (currentItem.equals(item)) {
				currentNode.myPriority = priority;
				if (i == 1) {
					bubbleDown(i);
				} else {
					bubbleUp(i);
				}
				System.out.println("chaning priority: "+ currentNode);
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

	public Double getPriority(T item){
		int index = getIndex.get(item);
		return getNode(index).priority();
	}
	
	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}
	
	public Boolean contains(T item) {
		return getIndex.containsKey(item);
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
		System.out.println(index + " " + n);
		contents.set(index, n);
	}

	/**
	 * Swap the nodes at the two indices.
	 */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		getIndex.put(node1.myItem, index2);

		this.contents.set(index2, node1);
		getIndex.put(node2.myItem, index1);

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
		return (2 * i) + 1;
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
		int leftChildIndex = getLeftOf(index);
		setNode(leftChildIndex, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		// TODO Complete this method!
		int rightChildIndex = getRightOf(index);
		setNode(rightChildIndex, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!
		int parentIndex = getParentOf(index);

		if (index == 1) {
			return;
		}
		if (min(index, parentIndex) == index) {
			swap(index, parentIndex);
			bubbleUp(parentIndex);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		int leftChildIndex = getLeftOf(index);
		int rightChildIndex = getRightOf(index);
		Node leftChildNode = getNode(leftChildIndex);
		Node rightChildNode = getNode(rightChildIndex);
		
		if (leftChildNode == null && rightChildNode == null) {
			return;
		}
		if (leftChildNode != null && rightChildNode != null) {
			int nextIndex = min(leftChildIndex, rightChildIndex);
			
			if (min(index, nextIndex) == nextIndex) {
				swap(index, nextIndex);
				bubbleDown(nextIndex);
			}
		}
		if (leftChildNode == null) {
			if (min(index, rightChildIndex) == rightChildIndex) {
				swap(index, rightChildIndex);
				bubbleDown(rightChildIndex);
			}
		}
		if (rightChildNode == null) {
			if (min(index, leftChildIndex) == leftChildIndex) {
				swap(index, leftChildIndex);
				bubbleDown(leftChildIndex);
			}
		}
		return;
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
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
		System.out.println(heap.removeMin().myPriority);
	}
}
