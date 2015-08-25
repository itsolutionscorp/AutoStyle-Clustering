import java.util.ArrayList;
import java.util.Collections;



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
		Node newNode = new Node(item, priority);
		if (contentSize() == 0) {
			setNode(1, newNode);
			return;
		}
		int index = 0;
		for(int i = 1; i < contentSize(); i++) {
			if(getNode(i) != null) {
				if(getNode(getLeftOf(i)) == null) {
					setNode(getLeftOf(i), newNode);
					index = getLeftOf(i);
					break;
				}
				else if (getNode(getRightOf(i)) == null) {
					setNode(getRightOf(i), newNode);
					index = getRightOf(i);
					break;
		
				}
			}
		}
		bubbleUp(index);
	}
	
	public int contentSize() {
		return contents.size();
	}
//	 public void insert(int value) {
//	        Node root = this.getNode(1);
//	        if (root != null) {
//				insertInSubtree(1, value);
//	        } else {
//				Node newNode = new Node(value);
//	            this.setNode(1, newNode);
//	        }
//	    }

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
	public T removeMin() {
		Node temp = getNode(1);
		//int lastIndex = contentNullFreeSize();
		int index = 1;
		while(getNode(index) != null) {
			index++;
		}
		setNode(1, getNode(index-1));
		setNode(index-1,null);
		bubbleDown(1);
		return (T) temp;
	}
	public boolean isEmpty() {
		if (contents.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (Node a: contents) {
			if(a.item() == item) {
				a.setPriority(priority);
				return;
			}
			else {
				System.out.println("No node contains the given Item.");
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
		return 2*i +1;
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
		setNode(2*index,n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(2*index+1,n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		
		if(getNode(getParentOf(index)) == null) {
			return;
		}
		int tempParent = getParentOf(index);
		while ((min(index, tempParent) == index)) {
			swap(index, tempParent);
			index = tempParent;
			if (getNode(getParentOf(index)) == null) {
				return;
			}
			tempParent = getParentOf(index);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		int tempLeft = getLeftOf(index);
		int tempRight = getRightOf(index);
//		System.out.println(getNode(tempLeft).myItem + "This is Left");
//		System.out.println(getNode(tempRight).myItem + "This is Right");
		
		if (getNode(tempLeft) == null && getNode(tempRight) == null) {
			return;
		}
		int tempLowest = min(min(tempLeft,tempRight),index);
		
		if (index == tempLowest) {
			return;
		}
		System.out.println(contents);
		swap(index,tempLowest);
		bubbleDown(tempLowest);
//		int tempLeft = getLeftOf(index);
//		int tempRight = getRightOf(index);
//		int tempLowest = min(min(tempLeft,tempRight),index);
//		while ((min(index, tempLowest) != index)) {
//			swap(index, tempLowest);
//			index = tempLowest;
//			if (getNode(getLeftOf(index)) != null) {
//				if (getNode(getRightOf(index)) != null) {
//				tempLeft = getLeftOf(index);
//				tempRight = getRightOf(index);
//				tempLowest = min(min(tempLeft,tempRight),index);
//				}
//				else {
//					tempLower = getLeftOf(index);
//				}
//			}
//			else if (getNode(getRightOf(index)) != null) {
//				tempLower = getRightOf(index);
//			}
//			else {
//				return;
//			}
//		}
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
		
		public void setPriority(double newPriority) {
			myPriority = newPriority;
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
		System.out.println(heap.removeMin());
		System.out.println(heap);
		System.out.println(heap.removeMin());
		System.out.println(heap);
		System.out.println(heap.removeMin());
		
	}

}
