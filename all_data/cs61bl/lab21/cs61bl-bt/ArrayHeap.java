import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	public ArrayList<Node> contents = new ArrayList<Node>();
	public int numOfNodes;

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority, Integer pre) {
		
		if (contents.isEmpty()){
			setNode(1, new Node(item, priority, pre));
		}
		
		else {
			for (int i = 1; i < contents.size() - 1; i++) {
				if (getNode(i).item() == item)
					return;
			}
			int availIndex = contents.size() - 1;
			setNode(availIndex, new Node(item, priority, pre));
			bubbleUp(availIndex);
		}
		/*
		if (contents.isEmpty()){
			setNode(1, new Node(item, priority, pre));
			numOfNodes ++;
		}
		else {
			numOfNodes++;
			int i = numOfNodes-1;
			
			while (i > 0 && contents.get((i-1)/2).priority() > priority){
				setNode(i, contents.get((i-1)/2));
				i = (i-2)/2;
				
			}
			
			//set A[i] = key ???
			contents.set(i, new Node(item, priority, pre));
		}
		else {
		setNode(contents.size()-1, new Node(item, priority, pre));
		for (int i = 1; i < contents.size() - 2; i++) {
			 if ((Integer) contents.get(i).item() > (Integer) contents.get(i+1).item()) {
			 swap(i, i + 1);
			 }
			}
		}*/
		
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {

		return contents.get(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		
		Node tempMin = getNode(1);
		
		int furthest = getRightOf(1);
		if (getRightOf(furthest) < contents.size()-2){
			System.out.println("Furthest:"+furthest);
			furthest = getRightOf(furthest);
		}
		
		if (getLeftOf(furthest) < contents.size()-2){
			furthest = getLeftOf(furthest);
		}
		System.out.println(furthest);
		Node tempMax = contents.get(furthest);
		System.out.println("tempMax"+tempMax);
		
		//set new root node
		setNode(1, tempMax);
		
		bubbleDown(1);
		
		contents.remove(furthest);
		contents.trimToSize();
		
		bubbleDown(1);
		
		//bubbleDown(1);
		//Node tempMin = getNode(contents.size());
		//contents.remove(getNode(contents.size()));
		//return tempMin;
		return tempMin;
		
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		for (Node n : this.contents) {
			if (n == null)
				break;
			if (n.item().equals(item))
				n.myPriority = priority;
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

	Node getNode(int index) {
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
		this.setNode(index1, node2);
		this.setNode(index2, node1);
	}

	/**
	 * Returns the index of the node to the left of the node at i.
	 */
	private int getLeftOf(int i) {
		return i * 2;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return i * 2 + 1;
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
		setNode(index * 2, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(index * 2 + 1, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		while (index > 1) {
			if (getNode(index).priority() < getNode(getParentOf(index))
					.priority())
				swap(index, getParentOf(index));
			index--;
		}

	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		swap(index, contents.size() - 1);
		while (index < contents.size() - 2) {
			int less = min(getLeftOf(index), getRightOf(index));

			swap(less, index);
			index = less;
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
		double myPriority;
		private Integer myPredecessor;

		private Node(T item, double priority, Integer pre) {
			myItem = item;
			myPriority = priority;
			myPredecessor = pre;
		}
		
		public void setPriority(double priority) {
			myPriority = priority;
		}
		
		public void setPredecessor(Integer pre) {
			myPredecessor = pre;
		}

		public T item() {
			return myItem;
		}

		public double priority() {
			return myPriority;
		}
		
		public int predecssor(){
			return myPredecessor;
		}

		@Override
		public String toString() {
			return item().toString() + ", " + priority();
		}
	}

	public static void main(String[] args) {
		/*
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

		heap.changePriority("c", 0);
		System.out.println(heap);

		heap.removeMin();
		System.out.println(heap);
		*/

	}

	public double get(Integer root) {
		// TODO Auto-generated method stub
		return 0;
	}

}
