import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private static int lastSpot = 0;

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		if (lastSpot == 0) {
			setNode(0, null);
		}
		lastSpot ++;
		setNode(lastSpot, new Node(item, priority));
		bubbleUp(lastSpot);
		
		
	}
	
	public int lastNum() {
		return lastSpot;	
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		Node rtn = getNode(1);
		return rtn;

	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method!
		
		if (lastSpot == 0) {
			contents = null;
			return null;
		}
		Node smallest = getNode(1); // grab high priority
		swap(1,lastSpot); // swap front with last
		contents.remove(lastSpot);
		bubbleDown(1);
		lastSpot--;
		return smallest;

	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!
		for (Node k : contents) {
			if (k.item().equals(item)) {
				k.myPriority = priority;

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
		while (index + 1 > contents.size()) {
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
		return contents.indexOf(contents.get(i)) * 2;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		return contents.indexOf(contents.get(i)) * 2 + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		// TODO Complete this method!
		return contents.indexOf(contents.get(i)) / 2;
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
		// TODO Complete this method! //FIX THIS
		if (getNode(getParentOf(index)) != null) {
			if (getNode(getParentOf(index)).priority() > getNode(index)
					.priority()) {
				swap(getParentOf(index), index);
				bubbleUp(getParentOf(index));
			}
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!
		if (index == lastSpot) {
			return;
		} else if (getNode(getLeftOf(index)) == null) {
			return;
			
		} else if (getNode(index).priority() < getNode(getLeftOf(index)).priority() && getNode(getRightOf(index))== null) {
			return;

		} else if (getNode(getLeftOf(index)) != null
				&& getNode(getRightOf(index)) != null
				&& getNode(index).priority() > getNode(getLeftOf(index))
						.priority()
				&& getNode(index).priority() > getNode(getRightOf(index))
						.priority()) {
			if (getNode(getLeftOf(index)).priority() > getNode(
					getRightOf(index)).priority()) {
				swap(getRightOf(index), index);
				bubbleDown(getRightOf(index));
			} else {
				swap(getLeftOf(index), index);
				bubbleDown(getLeftOf(index));
			}

		} else if (getNode(getRightOf(index)) == null) {
			setNode(getRightOf(index), getNode(index));

		} else if (getNode(index).priority() > getNode(getLeftOf(index))
				.priority()) {
			swap(getLeftOf(index), index);
			bubbleDown(getLeftOf(index));

		} else if (getNode(index).priority() > getNode(getRightOf(index))
				.priority()) {
			swap(getRightOf(index), index);
			bubbleDown(getRightOf(index));

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
		while (heap.lastNum() != 0) {
			System.out.println(heap.removeMin());

		}

	}

}

