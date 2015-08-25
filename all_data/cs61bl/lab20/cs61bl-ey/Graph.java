import java.util.ArrayList;
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
			throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
		}
	}

	// Add to the graph a directed edge from vertex v1 to vertex v2.
	public void addEdge(int v1, int v2) {
		if (v1 >= 0 && v1 < myAdjLists.length && v2 >= 0 && v2 < myAdjLists.length) {
			addEdge(v1, v2, null);
		}
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2.
	public void addUndirectedEdge(int v1, int v2) {
		if (v1 >= 0 && v1 < myAdjLists.length && v2 >= 0 && v2 < myAdjLists.length) {
			addUndirectedEdge(v1, v2, null);
		}
	}

	// Add to the graph a directed edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addEdge(int v1, int v2, Object edgeInfo) {
		if (v1 >= 0 && v1 < myAdjLists.length && v2 >= 0 && v2 < myAdjLists.length) {
			Edge e = new Edge(v1, v2, edgeInfo);
			myAdjLists[v1].add(e);
		}
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		if (v1 >= 0 && v1 < myAdjLists.length && v2 >= 0 && v2 < myAdjLists.length) {
			Edge e = new Edge(v1, v2, edgeInfo);
			myAdjLists[v1].add(e);
			Edge e2 = new Edge(v2, v1, edgeInfo);
			myAdjLists[v2].add(e2);
		}
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		if (from >= 0 && from < myAdjLists.length && to >= 0 && to < myAdjLists.length) {
			for (Edge e : myAdjLists[from]) {
				if (e.myTo == to)
					return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (Edge e : myAdjLists[vertex]) {
			a.add(e.myTo);
		}
		return a;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		if (vertex >= 0 && vertex < myAdjLists.length) {
			for (int i = 0; i < myAdjLists.length; i++) {
				for (Edge e : myAdjLists[i]) {
					if (e.myTo == vertex)
						count++;
				}
			}
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
			visited = new HashSet<Integer>();
			fringe = new Stack<Integer>();
			setStartVertex(start);
			fringe.push(myStartVertex);
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer rtn = fringe.pop();
			visited.add(rtn);
			for (Edge e : myAdjLists[rtn]) {
				if (!visited.contains(e.myTo)) {
					fringe.push(e.myTo);
				}
			}
			return rtn;
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
		if (startVertex == stopVertex)
			return true;
		ArrayList<Integer> visited = visitAll(startVertex);
		if (visited.contains(stopVertex))
			return true;
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		if (startVertex == stopVertex) {
			a.add(startVertex);
			return a;
		}
		Iterator<Integer> iter = new DFSIterator(startVertex);
		Integer v = Integer.MAX_VALUE;
		while (iter.hasNext() && v != stopVertex) {
			v = iter.next();
			a.add(v);
		}
		for (int i = a.size() - 2; i >= 0; i--) {
			if (!pathExists(a.get(i), a.get(i + 1))) {
				a.remove(i);
			}
		}
		return a;
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

		// more instance variables go here
		private Integer[] currentInDegree;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			// more statements go here
			currentInDegree = new Integer[myAdjLists.length];
			for (int i = 0; i < myAdjLists.length; i++) {
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
			Integer rtn = fringe.pop();
			List<Integer> a = neighbors(rtn);
			for (Integer i : a) {
				currentInDegree[i] -= 1;
				if (currentInDegree[i] == 0) {
					fringe.push(i);
				}
			}
			return rtn;
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
		System.out.println();

		Graph g3 = new Graph(7);
		g3.addUndirectedEdge(0, 2);
		g3.addUndirectedEdge(0, 3);
		g3.addUndirectedEdge(1, 4);
		g3.addUndirectedEdge(1, 5);
		g3.addUndirectedEdge(2, 3);
		g3.addUndirectedEdge(2, 6);
		g3.addUndirectedEdge(4, 5);
		System.out.println(g3.pathExists(5, 1));
		ArrayList<Integer> a = g3.path(1, 5);
		for (Integer i : a) {
			System.out.println(i);
		}
		System.out.println();

		Graph g4 = new Graph(5);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		g4.addEdge(2, 3);
		g4.addEdge(4, 2);
		System.out.println(g4.pathExists(2, 4));
		ArrayList<Integer> b = g4.path(1, 0);
		for (Integer i : b) {
			System.out.println(i);
		}

		Graph g5 = new Graph(7);
		g5.addEdge(0, 1);
		g5.addEdge(0, 2);
		g5.addEdge(0, 3);
		g5.addEdge(3, 2);
		g5.addEdge(2, 1);
		g5.addEdge(1, 4);
		g5.addEdge(4, 5);
		g5.addEdge(2, 5);
		g5.addEdge(3, 5);
		g5.addEdge(5, 6);
		// System.out.println(g5.pathExists(2, 4));
		System.out.println("Topological sort");
		result = g5.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		
		Graph g6 = new Graph(10);
		g6.addEdge(0, 4);
		g6.addEdge(1, 4);
		g6.addEdge(1, 5);
		g6.addEdge(2, 5);
		g6.addEdge(2, 6);
		g6.addEdge(3, 6);
		g6.addEdge(4, 7);
		g6.addEdge(5, 7);
		g6.addEdge(5, 8);
		g6.addEdge(6, 8);
		g6.addEdge(7, 9);
		g6.addEdge(8, 9);
		System.out.println();
		System.out.println();
		g6.setStartVertex(0);
		System.out.println("Topological sort");
		result = g6.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
	}

}
