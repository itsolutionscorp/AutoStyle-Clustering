import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph implements Iterable<Integer> {

	private LinkedList<Edge>[] myAdjLists;
	private int myVertexCount;
	private int myStartVertex;
	private double [] costMap;

	// Initialize a graph with the given number of vertices and no edges.
	public Graph(int numVertices) {
		myAdjLists = new LinkedList[numVertices];
		myStartVertex = 0;
		for (int k = 0; k < numVertices; k++) {
			myAdjLists[k] = new LinkedList<Edge>();
		}
		myVertexCount = numVertices;
		costMap = new double [myVertexCount];
	}

	// Change the vertex the iterator will start DFS from
	public void setStartVertex(int v) {
		if (v < myVertexCount && v >= 0) {
			myStartVertex = v;
		} else {
			throw new IllegalArgumentException(
					"Cannot set iteration start vertex to " + v + ".");
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
		// your code here
		Edge a1 = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(a1);

	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		// your code here
		Edge a2 = new Edge(v1, v2, edgeInfo);
		Edge a3 = new Edge(v2, v1, edgeInfo);
		myAdjLists[v1].add(a2);
		myAdjLists[v2].add(a3);
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		// your code here
		for (Edge e : myAdjLists[from]) {
			if (e.equals(to)) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		// your code here
		ArrayList<Edge> newList = new ArrayList<Edge>();
		if (myAdjLists[vertex] == null) {
			return null;
		} else {
			for (Edge e : myAdjLists[vertex]) {
				newList.add(e);
			}
		}
		return newList;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (Edge e : myAdjLists[vertex]) {
			count += 1;
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
			// your code here
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			fringe.add(start);
		}

		public boolean hasNext() {
			// your code here
			return !fringe.isEmpty();
		}

		public Integer next() {
			// your code here

			Integer currEdge = fringe.pop();
			if (!visited.contains(currEdge)) {
				visited.add(currEdge);
				for (Edge e : myAdjLists[currEdge]) {
					fringe.push(e.myTo);
				}
			}
			return currEdge;
		}

		// ignore this method
		public void remove() {
			throw new UnsupportedOperationException(
					"vertex removal not implemented");
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
		// your code here
		if (startVertex == stopVertex) {
			return true;
		}

		ArrayList visited = new ArrayList();
		visited = visitAll(startVertex);
		if (visited.contains(stopVertex)) {
			return true;
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {

		ArrayList<Integer> numPath = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		Stack<Integer> visited = new Stack<Integer>();

		if (!pathExists(startVertex, stopVertex)) {
			return numPath;
		} else if (startVertex == stopVertex) {
			numPath.add(startVertex);
			return numPath;
		} else {

			while (iter.hasNext()) {
				int end = iter.next();
				if (end == stopVertex) {
					visited.add(end);
					break;
				}
				visited.add(end);
			}

			numPath.add(visited.pop());

			while (visited != null) {
				int check = visited.pop();
				if (check == startVertex) {
					numPath.add(check);
					break;
				}
				for (Edge e : myAdjLists[check]) {
					if (e.myTo == stopVertex) {
						numPath.add(e.myFrom);
						stopVertex = e.myFrom;
						break;
					}
				}
			}

			Collections.reverse(numPath);
			return numPath;

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
		private HashSet<Integer> visited;
		private HashSet<Integer> nodes;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			nodes = new HashSet<Integer> ();
			// more statements go here
			currentInDegree = new int[myVertexCount];
			for (LinkedList<Edge> e : myAdjLists) {
				for (Edge i : e) {
					nodes.add(i.myFrom);
					currentInDegree[i.myTo]++;
				}
			}
			for (int z = 0; z < myAdjLists.length; z++) {
				if (currentInDegree[z] == 0 && (nodes.contains(z))) {
					fringe.push(z);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			// fill this out
			Integer currEdge = fringe.pop();
			if (!visited.contains(currEdge)) {
				visited.add(currEdge);
				for (Edge e : myAdjLists[currEdge]) {
					if (currentInDegree[e.myTo] > 0) {
						currentInDegree[e.myTo]--;
					}
					if (currentInDegree[e.myTo] == 0
							&& (!visited.contains(e.myTo))) {
						fringe.push(e.myTo);
					}
				}

			}
			return currEdge;

		}

		public void remove() {
			throw new UnsupportedOperationException(
					"vertex removal not implemented");
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

		public Object info() {
			return myEdgeInfo;
		}

		public String toString() {
			return "(" + myFrom + "," + myTo + ",dist=" + myEdgeInfo + ")";
		}

	}
	
	public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
	    //your code here...
		PriorityQueue<Integer> fringe = new PriorityQueue(myVertexCount, new nodeComparator());
		HashMap<Integer, Integer> myPath = new HashMap<Integer, Integer>();
		ArrayList<Integer> tortn = new ArrayList<Integer>();
		ArrayList<Integer> alreadySeen = new ArrayList<Integer>();
		int firstNum = startVertex;
		
		for (int i = 0; i < costMap.length; i ++) {
			if (i == startVertex) {
				costMap[i] = 0;
			} else {
			costMap[i] = Double.POSITIVE_INFINITY;
			}
		}
		
			for (Integer i : visitAll(startVertex)) {
				if (!alreadySeen.contains(i)) {
					fringe.add(i);
					alreadySeen.add(i);
				}
			}
			alreadySeen.clear();
		
		
		while (!fringe.isEmpty()) {
			Integer node = fringe.poll();
			startVertex = node;
			for (Edge e : myAdjLists[startVertex]) {
				if (!alreadySeen.contains(e.myTo) && costMap[startVertex] + (Integer) e.myEdgeInfo < costMap[e.myTo]) {
					costMap[e.myTo] = costMap[startVertex] + (Integer) e.myEdgeInfo;
					myPath.put(e.myTo, startVertex);
					fringe.remove(e.myTo);
					fringe.add(e.myTo);		
				}
				
			}
			if (node == endVertex) {
				tortn.add(endVertex);
				break;
			}	
		}
		
		while (endVertex != firstNum) {	
				tortn.add(myPath.get(endVertex));
				endVertex = myPath.get(endVertex);
		}
		
		Collections.reverse(tortn);
		return tortn;
		
		
	}
	
	private class nodeComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer a, Integer b) {
			if (costMap[a] < costMap[b]) {
				return -1;
			} else if (costMap[a] == costMap[b]) {
				return 0;
			} else {
				return 1;
			}
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

		Graph g3 = new Graph(8);
		g3.addEdge(0, 2);
		g3.addEdge(2, 3);
		g3.addEdge(3, 4);
		g3.addEdge(4, 5);
		g3.addEdge(7, 5);
		g3.addEdge(0, 4);
		System.out.println();
		System.out.println();
		System.out.println("Topological sort 2");
		result = g3.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
	
		Graph g4 = new Graph(5);
		g4.addEdge(0, 1, 4);
		g4.addEdge(0, 2, 9);
		g4.addEdge(0, 4, 1);
		g4.addEdge(1, 2, 3);
		g4.addEdge(2, 3, 2);
		g4.addEdge(4, 3, 7);
		System.out.println();
		System.out.println();
		System.out.println("Finding shortest path:");
		result = g4.shortestPath(0, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		
		Graph g5 = new Graph(9);
		g5.addEdge(0, 4, 20);
		g5.addEdge(0, 1, 0);
		g5.addEdge(4, 1, 7);
		g5.addEdge(1, 3, 1);
		g5.addEdge(3, 6, 4);
		g5.addEdge(6, 7, 3);
		g5.addEdge(7, 8, 2);
		g5.addEdge(0, 8, 99);
		g5.addEdge(0, 5, 7);
		g5.addEdge(1, 6, 7);
		System.out.println();
		System.out.println();
		System.out.println("Finding shortest path:");
		result = g5.shortestPath(1, 6);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}

	}

}
