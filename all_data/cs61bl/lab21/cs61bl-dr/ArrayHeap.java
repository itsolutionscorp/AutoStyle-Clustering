import java.util.ArrayList;
import java.util.Comparator;

/**
 * A Generic heap class. Unlike Java's cost queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a cost value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int lastNodeIndex = 0;
	private int insertIndex = 1;
	NodeComparator comparer = new NodeComparator();

	/**
	 * Inserts an vertex with the given cost value. This is enqueue, or offer.
	 */
	public void add(T vertex, double cost) {	
		setNode(insertIndex, new Node(vertex, cost));
		bubbleUp(insertIndex);
		insertIndex++;	
		lastNodeIndex++;
	}
	
	public Node remove(T vertex){	
		Node node = findNode(vertex);
		int index = findNodeIndex(vertex, 1);
		setNode(index, null);		
		swap(index, lastNodeIndex);
		bubbleDown(index);			
		lastNodeIndex--;
		insertIndex--;	
		return node;
	}
	
	public boolean isEmpty(){
		return insertIndex == 1;
	}

	/**
	 * Returns the Node with the smallest cost value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {		
		return getNode(1);
	}

	/**
	 * Returns the Node with the smallest cost value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		Node smallest = getNode(1);		
		setNode(1, null);		
		swap(1, lastNodeIndex);
		bubbleDown(1);			
		lastNodeIndex--;
		insertIndex--;
		return smallest;		
	}
	
	
			

	/**
	 * Change the node in this heap with the given vertex to have the given
	 * cost. For this method only, you can assume the heap will not have two
	 * nodes with the same vertex. Check for vertex equality with .equals(), not ==
	 */
	public void changeCost(T vertex, double cost) {
		Node found = findNode(vertex);
		found.setCost(cost);			
	}
	
	/**
	 * 
	 * @param vertex
	 * 		Item of a Node.
	 * @return
	 * 		The Node with this vertex.
	 */
	public Node findNode(T vertex){
		return findNodeHelper(vertex, 1);
	}
	
	private Node findNodeHelper(T vertex, int index){
		Node node = getNode(index);
		if(node != null){
			if(node.vertex().equals(vertex)) return node;
			else{
				Node left = findNodeHelper(vertex, getLeftOf(index));
				Node right = findNodeHelper(vertex, getRightOf(index));
				if (left != null) return left;
				return right;				
			}
		}
		return null;		
	}
	public int findNodeIndex(T vertex, int index){
		Node node = getNode(index);
		if(node != null){
			if(node.vertex().equals(vertex)) return index;
			else{
				int left = findNodeIndex(vertex, getLeftOf(index));
				int right = findNodeIndex(vertex, getRightOf(index));
				if (left != -1) return left;
				return right;				
			}
		}
		return -1;
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
		int parentIndex = getParentOf(index);
		Node parent = getNode(parentIndex);		
		if(parent != null && comparer.compare(parent, getNode(index)) > 0){
			swap(index, parentIndex);
			bubbleUp(parentIndex);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {	
		int nextIndex = min(getLeftOf(index), getRightOf(index));
		Node next = getNode(nextIndex);
		if(next != null && comparer.compare(next, getNode(index)) < 0){
			swap(nextIndex, index);
			bubbleDown(nextIndex);
		}											
	}

	/**
	 * Returns the index of the node with smaller cost. Precondition: Not
	 * both of the nodes are null.
	 */
	private int min(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if (comparer.compare(node1, node2) < 0){
			return index1;
		} else {
			return index2;
		}
	}
	
	public class Node{
		private T myVertex;
		private double myCost;
		private Node myPrev;

		private Node(T vertex, double cost) {
			myVertex = vertex;
			myCost = cost;
			myPrev = null;
		}

		public T vertex() {
			return myVertex;
		}

		public double cost() {
			return myCost;
		}
		
		public Node getPrev(){
			return myPrev;
		}
		
		public void setCost(double cost){
			myCost = cost;
		}
		
		public void setPrev(Node prev){
			myPrev = prev;
		}

		@Override
		public String toString() {
			return vertex().toString() + ", " + cost();
		}
		
	}
	class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node a, Node b){
			return (int) (a.cost() - b.cost());		
		}
				
		
	}
	

}
