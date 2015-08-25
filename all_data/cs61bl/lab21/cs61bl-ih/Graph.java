import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph implements Iterable<Integer> {

	private LinkedList<Edge>[] myAdjLists;
	private int myVertexCount;
	@SuppressWarnings("unused")
	private int myStartVertex;

	// Initialize a graph with the given number of vertices and no edges.
	@SuppressWarnings("unchecked")
	public Graph(int numVertices) {
		myAdjLists = new LinkedList[numVertices];
		myStartVertex = 0;
		for (int k = 0; k < numVertices; k++) {
			myAdjLists[k] = new LinkedList<Edge>();
		}
		myVertexCount = numVertices;
	}

	// Change the vertex the iterator will start DFS from
	public void setStartVertex(int v) {
		if (v < myVertexCount && v >= 0) {
			myStartVertex = v;
		} else {
			throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
		}
	}

	// Add to the graph a directed edge from vertex v1 to vertex v2.
	public void addEdge(int v1, int v2) {
		addEdge(v1, v2, null);
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2.
	public void addUndirectedEdge(int v1, int v2) {
		addUndirectedEdge(v1, v2, null);
	}

	// Add to the graph a directed edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addEdge(int v1, int v2, Object edgeInfo) {
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
		myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		for (Edge e : myAdjLists[from]) {
			if (e.to().equals(to)) {
				return true;
			}
		} // Or we can use an iterator.
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		List<Integer> toReturn = new ArrayList<Integer>();
		Iterator<Edge> iter = myAdjLists[vertex].iterator();
		while (iter.hasNext()) {
			toReturn.add(iter.next().myTo);
		}
		return toReturn;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int i = 0; i < myVertexCount; i++) {
			for (Edge e : myAdjLists[i]) {
				if (e.to().equals(vertex)) {
					count++;
					break;
				}
			} // Or we can use an iterator.
		}
		return count;
	}

	public Iterator<Integer> iterator() {
		return new TopologicalIterator();
	}

	// A class that iterates through the vertices of this graph, starting with a
	// given vertex.
	// Does not necessarily iterate through all vertices in the graph: if the
	// iteration starts
	// at a vertex v, and there is no path from v to a vertex w, then the
	// iteration will not
	// include w
	private class DFSIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private HashSet<Integer> visited;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			fringe.push(start);
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer toReturn = fringe.pop();
			for (Edge i : myAdjLists[toReturn]) {
				if (!visited.contains(i.myTo)) {
					fringe.push(i.myTo);
				}
			}
			visited.add(toReturn);
			return toReturn;
		}

		// ignore this method
		public void remove() {
			throw new UnsupportedOperationException("vertex removal not implemented");
		}

	}

	// Return the collected result of iterating through this graph's
	// vertices as an ArrayList.
	public ArrayList<Integer> visitAll(int startVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);

		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	// Returns true iff there exists a path from STARVETEX to
	// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
	// in this graph. If STARVERTEX == STOPVERTEX, returns true.
	public boolean pathExists(int startVertex, int stopVertex) {
		Iterator<Integer> iter = new DFSIterator(startVertex);
		HashSet<Integer> visited = new HashSet<Integer>();
		while (iter.hasNext()) {
			visited.add(iter.next());
		}
		return visited.contains(stopVertex);
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		toReturn.add(stopVertex);
		if (!pathExists(startVertex, stopVertex)) {
			return result;
		}

		Iterator<Integer> iter = new DFSIterator(startVertex);
		while (iter.hasNext()) {
			result.add(iter.next());
			if (result.contains(stopVertex)) {
				break;
			}
		}

		Integer terminal = stopVertex;
		while (!terminal.equals(startVertex)) {
			Iterator<Integer> iter2 = result.iterator();

			outerloop: while (iter2.hasNext()) {
				Integer temp = iter2.next();
				Iterator<Edge> iter3 = myAdjLists[temp].iterator();
				while (iter3.hasNext()) {
					if ((iter3.next()).to().equals(terminal)) {
						toReturn.add(0, temp);
						terminal = temp;
						break outerloop;
					}
				}
			}

		}
		return toReturn;
	}

	/**
	 * new in lab21
	 */
	
	public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
		DijkstraIterator iter = new DijkstraIterator(startVertex);
		ArrayList<Integer> toBeReturned = new ArrayList<Integer>();
		while (iter.hasNext()){
			Integer temp = iter.next();
			if (temp == endVertex){
				while(iter.shortestPath[temp] != null){
					toBeReturned.add(0, temp);
					if (iter.shortestPath[temp] != startVertex){
						temp = iter.shortestPath[temp];
					} else { break; }
				}
				toBeReturned.add(0, startVertex);
			}
		}
		return toBeReturned;
	}
	
	private class DijkstraIterator implements Iterator<Integer> {

		private ArrayHeap<Integer> fringe;
		private Integer [] shortestPath;
		
		public DijkstraIterator(int startVertex) {
			fringe = new ArrayHeap<Integer> ();
			fringe.insert(startVertex, 0);
			for (int i =0 ; i < myAdjLists.length; i++){
				if (i != startVertex){
					fringe.insert(i, Integer.MAX_VALUE);
				}
			}
			shortestPath = new Integer[myAdjLists.length];
			for (int k = 0; k < myAdjLists.length; k++) {
				shortestPath[k] = null;
			}
		}

		public boolean hasNext() {
			return fringe.peek() != null;
		}

		public Integer next() {
			Integer removed = fringe.peek().myItem;
			Integer removedPriority = fringe.removeMin().myPriority;
			for (Edge e: myAdjLists[removed]){
				for (ArrayHeap<Integer>.Node n: fringe.contents){
					if (n != null && e != null){
						if (n.myItem.equals(e.myTo)){
							if (n.myPriority > removedPriority + (Integer)e.myEdgeInfo){
								fringe.changePriority(n.myItem, removedPriority + (Integer)e.myEdgeInfo);
								shortestPath[n.myItem] = removed;
							}
						}
					}
				}
			}
			return removed;
		}

		public void remove() {
			throw new UnsupportedOperationException("vertex removal not implemented");
		}

	}

	/**
	 *  modify form ArrayHeap.java
	 */
	
	private class ArrayHeap<T> {
		private ArrayList<Node> contents = new ArrayList<Node>();
		private int numbers = 0;

		
		@SuppressWarnings("unused")
		public void Heapsort (){
			while (numbers > 0){
				System.out.println(removeMin());
			}
		}
		
		/**
		 * Inserts an item with the given priority value. This is enqueue, or offer.
		 */
		public void insert(T item, int priority) {
			numbers++;
			setNode(numbers, new Node(item, priority));
			bubbleUp(numbers);
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
			Node minNode = peek();
			swap(1, numbers);
			setNode(numbers, null);
			bubbleDown(1);
			numbers--;
			return minNode;
		}

		/**
		 * Change the node in this heap with the given item to have the given
		 * priority. For this method only, you can assume the heap will not have two
		 * nodes with the same item. Check for item equality with .equals(), not ==
		 */
		public void changePriority(T item, int priority) {
			changePriorityHelper(1, item, priority);
		}

		public void changePriorityHelper(int n, T item, int priority) {
			if (contents.get(n) != null){
				if (contents.get(n).myItem.equals(item)){
					if (contents.get(n).myPriority > priority){
						contents.get(n).myPriority = priority;
						bubbleUp(n);
						changePriorityHelper(1, item, priority);
					} else if (contents.get(n).myPriority < priority){
						contents.get(n).myPriority = priority;
						bubbleDown(n);
						changePriorityHelper(1, item, priority);
					}
				} else {
					changePriorityHelper(getLeftOf(n), item, priority);
					changePriorityHelper(getRightOf(n), item, priority);
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
			if(i >= contents.size()){
				System.out.println("index out of bounds");
				return 0;
			}
			else if(2*i >= contents.size()){
				return 0;
			}
			else if(contents.get(2*i) != null){ 
				return 2*i;
			}
			else {
				return 0;
			}
		}

		/**
		 * Returns the index of the node to the right of the node at i.
		 */
		private int getRightOf(int i) {
			if(i >= contents.size()){
				System.out.println("index out of bounds");
				return 0;
			}
			else if(2*i+1 >= contents.size()){
				return 0;
			}
			else if(contents.get(2*i+1) != null){ 
				return 2*i+1;
			}
			else {
				return 0;
			}
		}

		/**
		 * Returns the index of the node that is the parent of the node at i.
		 */
		private int getParentOf(int i) {
			if(i >= contents.size()){
				System.out.println("index out of bounds");
				return 0;
			}
			else if(contents.get((i - i % 2) / 2) != null){
				return (i - i % 2) / 2;
			}
			else {
				return 0;
			}
		}

		/**
		 * Adds the given node as a left child of the node at the given index.
		 */
		@SuppressWarnings("unused")
		private void setLeft(int index, Node n) {
			if(getNode(index) == null){
				System.out.println("can't have a child without a parent");
				return;
			}
			else{
				setNode(index*2, n);
			}
		}

		/**
		 * Adds the given node as the right child of the node at the given index.
		 */
		@SuppressWarnings("unused")
		private void setRight(int index, Node n) {
			if(getNode(index) == null){
				System.out.println("can't have a child without a parent");
				return;
			}
			else{
				setNode(index*2+1, n);
			}
		}

		/**
		 * Bubbles up the node currently at the given index.
		 */
		private void bubbleUp(int index) {
			if (contents.get(index) != null && contents.get(getParentOf(index))!= null) {
				if (contents.get(getParentOf(index)).myPriority > contents.get(index).myPriority) {
					swap(getParentOf(index), index);
					if (getParentOf(index) > 1) {
						bubbleUp(getParentOf(index));
					}
				}
			}
		}

		/**
		 * Bubbles down the node currently at the given index.
		 */
		private void bubbleDown(int index) {
			if (contents.get(index) != null) {
				if (contents.get(getLeftOf(index)) != null
						&& contents.get(getRightOf(index)) != null) {
					if (contents.get(getLeftOf(index)).myPriority < contents
							.get(index).myPriority
							&& contents.get(getLeftOf(index)).myPriority <= contents
									.get(getRightOf(index)).myPriority) {
						swap(getLeftOf(index), index);
						bubbleDown(getLeftOf(index));
					} else if (contents.get(getRightOf(index)).myPriority < contents
							.get(index).myPriority
							&& contents.get(getLeftOf(index)).myPriority > contents
									.get(getRightOf(index)).myPriority) {
						swap(getRightOf(index), index);
						bubbleDown(getRightOf(index));
					}
				} else if (contents.get(getLeftOf(index)) != null) {
					if (contents.get(getLeftOf(index)).myPriority < contents
							.get(index).myPriority) {
						swap(getLeftOf(index), index);
					}
				}
			}

		}

		/**
		 * Returns the index of the node with smaller priority. Precondition: Not
		 * both of the nodes are null.
		 */
		@SuppressWarnings("unused")
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
			private int myPriority;

			private Node(T item, int priority) {
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

	}
	
	public ArrayList<Integer> topologicalSort() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new TopologicalIterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	private class TopologicalIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private int[] currentInDegree;
		private ArrayList<Integer> visited;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			visited = new ArrayList<Integer>();
			currentInDegree = new int[myVertexCount];
			for (int i = 0; i < myVertexCount; i++) {
				currentInDegree[i] = inDegree(i);
				if (currentInDegree[i] == 0) {
					fringe.push(i);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer toReturn = fringe.pop();
			visited.add(toReturn);
			Iterator<Edge> iter = myAdjLists[toReturn].iterator();
			while (iter.hasNext()) {
				Integer temp = iter.next().myTo;
				currentInDegree[temp] -= 1;
			}
			for (int i = 0; i < myVertexCount; i++) {
				if (currentInDegree[i] == 0 && !visited.contains(i) && !fringe.contains(i)) {
					fringe.push(i);
				}
			}
			return toReturn;
		}

		public void remove() {
			throw new UnsupportedOperationException("vertex removal not implemented");
		}

	}

	private class Edge {

		private Integer myFrom;
		private Integer myTo;
		private Object myEdgeInfo;

		public Edge(int from, int to, Object info) {
			myFrom = new Integer(from);
			myTo = new Integer(to);
			myEdgeInfo = info;
		}

		public Integer to() {
			return myTo;
		}

		@SuppressWarnings("unused")
		public Object info() {
			return myEdgeInfo;
		}

		public String toString() {
			return "(" + myFrom + "," + myTo + ",dist=" + myEdgeInfo + ")";
		}

	}

	public static void main(String[] args) {
		ArrayList<Integer> result;

		Graph g1 = new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(0, 4);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		g1.addEdge(2, 3);
		g1.addEdge(4, 3);
		System.out.println("Traversal starting at 0");
		result = g1.visitAll(0);
		Iterator<Integer> iter;
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Traversal starting at 2");
		result = g1.visitAll(2);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Traversal starting at 3");
		result = g1.visitAll(3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Traversal starting at 4");
		result = g1.visitAll(4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 0 to 3");
		result = g1.path(0, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 0 to 4");
		result = g1.path(0, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 1 to 3");
		result = g1.path(1, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 1 to 4");
		result = g1.path(1, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 4 to 0");
		result = g1.path(4, 0);
		if (result.size() != 0) {
			System.out.println("*** should be no path!");
		}

		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(0, 4);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(4, 3);
		System.out.println();
		System.out.println();
		System.out.println("Topological sort");
		result = g2.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		
		Graph g3 = new Graph(5);
		g3.addEdge(0, 1, 8);
		g3.addEdge(0, 2, 13);
		g3.addEdge(1, 2, 1);
		g3.addEdge(2, 3, 1);
		g3.addEdge(1, 3, 8);
		g3.addEdge(3, 4, 3);
		g3.addEdge(1, 4, 100);
		System.out.println();
		System.out.println();
		System.out.println("shortestPath from 0 to 4");
		ArrayList<Integer> ShortestPath = g3.shortestPath (0, 4);
		for (Integer i: ShortestPath) {
			System.out.println(i);
		}
		
	}
}
