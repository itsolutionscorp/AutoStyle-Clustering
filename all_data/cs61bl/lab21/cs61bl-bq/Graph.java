import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Graph implements Iterable<Integer> {

	private LinkedList<Edge>[] myAdjLists;
	private int myVertexCount;
	private int myStartVertex;

	// Initialize a graph with the given number of vertices and no edges.
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
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		addEdge(v1, v2, edgeInfo);
		addEdge(v2, v1, edgeInfo);
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		LinkedList<Edge> neighbors = myAdjLists[from];
		for (Edge e : neighbors) {
			if (e.to() == to) {
				return true;
			}
		}
		return false;
	}

	public Edge getEdge(int from, int to) {
		LinkedList<Edge> neighbors = myAdjLists[from];
		for (Edge e : neighbors) {
			if (e.to() == to) {
				return e;
			}
		}
		return null;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		LinkedList<Integer> intList = new LinkedList<Integer>();
		for (Edge e : myAdjLists[vertex]) {
			intList.add(e.to());
		}
		return intList;

	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (LinkedList<Edge> lL : myAdjLists) {
			for (Edge e : lL) {
				if (e.to() == vertex) {
					count++;
				}
			}
		}
		return count;
	}

	public Iterator<Integer> iterator() {
		return new TopologicalIterator();
	}

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		DijkstraIterator iter = new DijkstraIterator(startVertex, endVertex);
		Stack<Integer> stack = new Stack<Integer>();
		
		if (!pathExists(startVertex, endVertex)) {
			System.out.println("There is no path between vertex " + startVertex + " and vertex " + endVertex);
			return result;
		}
		
		if (startVertex == endVertex) {
			result.add(startVertex);
			return result;
		}
		
		int last = 0;
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				last = iter.next();
			}
		} else {
			return result;
		}
		stack.push(last);
		last = iter.predecessors.get(last);
		while (last != startVertex) {
			stack.push(last);
			last = iter.predecessors.get(last);
		}
		stack.push(last);
		while(!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	// Edge weights are doubles //
	private class DijkstraIterator implements Iterator<Integer> {
		private ArrayHeap<Integer> fringe;
		private HashSet<Integer> seen;
		private int end;
		public HashMap<Integer, Integer> predecessors;

		public DijkstraIterator(int startVertex, int endVertex) {
			end = endVertex;
			predecessors = new HashMap<Integer, Integer>();
			fringe = new ArrayHeap<Integer>();
			// Add in start vertex with predecessor as null, weight as 0.
			fringe.insert(startVertex, (double) 0);
			seen = new HashSet<Integer>();
			seen.add(startVertex);
			Iterator<Integer> vertices = toDFS();
			// Add in all vertices, setting predecessor to null and weight to
			// positive infinity.
			if (vertices.hasNext()) {
				vertices.next();
			}
			while (vertices.hasNext()) {
				int v = vertices.next();
				if (!seen.contains(v)) {
					fringe.insert(v, Double.POSITIVE_INFINITY);
				}
				seen.add(v);
			}
		}

		public boolean hasNext() {
			return fringe.peek() != null;
		}

		public Integer next() {
			int v = fringe.peek().item();
			double p = fringe.get(v).priority();
			fringe.removeMin();
			if (v == end) {
				fringe = new ArrayHeap<Integer>();
				return v;
			}
			for (int n : neighbors(v)) {
				if (fringe.contains(n)) {
					fringe.changePriority(n, p
							+ (double) (int) getEdge(v, n).info());
					predecessors.put(n, v);
				}
			}
			return v;
		}
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
			int rtn = fringe.pop();
			for (Integer i : neighbors(rtn)) {
				if (!visited.contains(i)) {
					fringe.push(i);
				}
			}
			visited.add(rtn);
			return rtn;
		}

		public HashSet<Integer> contains() {
			return visited;
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
		if (startVertex == stopVertex) {
			return true;
		}
		if (neighbors(startVertex).contains(stopVertex)) {
			return true;
		} else {
			for (Integer i : neighbors(startVertex)) {
				return pathExists(i, stopVertex);
			}
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		if (!pathExists(startVertex, stopVertex)) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		while (iter.hasNext()) {
			int i = iter.next();
			if (i == stopVertex) {
				result.add(i);
				break;
			}

		}
		HashSet<Integer> contains = ((DFSIterator) iter).contains();
		int end = result.get(0);
		while (end != startVertex) {
			for (Integer key : contains) {
				if (neighbors(key).contains(end) && !result.contains(key)) {
					result.add(key);
					end = key;
				}
			}
		}
		return result;
	}

	public ArrayList<Integer> topologicalSort() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new TopologicalIterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	public Iterator<Integer> toDFS() {
		return new DFSIterator(myStartVertex);
	}

	private class TopologicalIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private int[] currentInDegree;
		private HashSet<Integer> seen;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[myVertexCount];
			seen = new HashSet<Integer>();
			Iterator<Integer> vertices = toDFS();
			while (vertices.hasNext()) {
				int v = vertices.next();
				currentInDegree[v] = inDegree(v);
			}
			for (int i = 0; i < currentInDegree.length; i++) {
				if (currentInDegree[i] == 0) {
					fringe.push(i);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int i = fringe.pop();
			seen.add(i);
			for (int n : neighbors(i)) {
				int curr = currentInDegree[n];
				currentInDegree[n] = curr - 1;
			}
			for (int v = 0; v < currentInDegree.length; v++) {
				if (currentInDegree[v] == 0 && !seen.contains(v)) {
					fringe.push(v);
				}
			}
			return i;
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

		public Edge(int to) {
			myFrom = null;
			myTo = to;
			myEdgeInfo = null;
		}

		public Edge(int to, double weight) {
			myFrom = null;
			myTo = to;
			myEdgeInfo = weight;
		}

		public Edge(int from, int to, Object info) {
			myFrom = new Integer(from);
			myTo = new Integer(to);
			myEdgeInfo = info;
		}

		public void setPredecessor(int i) {
			myFrom = i;
		}

		public Integer predecessor() {
			return myFrom;
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
		System.out.println("Contains path from 2 to 1? " + g1.pathExists(2, 1));
		System.out.println("Contains path from 3 to 4? " + g1.pathExists(3, 4));
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
		System.out.println(result.size());
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

		g2 = new Graph(5);
		g2.addEdge(0, 1, 10);
		g2.addEdge(0, 3, 30);
		g2.addEdge(0, 4, 100);
		g2.addEdge(1, 2, 50);
		g2.addEdge(3, 2, 20);
		g2.addEdge(3, 4, 60);
		g2.addEdge(2, 4, 10);
		System.out.println();
		System.out.println();
		System.out.println("Dijkstra's Algorithm");
		result = g2.shortestPath(3, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
	}

}
