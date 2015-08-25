import java.util.ArrayList;
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
		List<Edge> aList = myAdjLists[from];
		for (Edge ed : aList) {
			if (ed.to() == to)
				return true;
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (Edge ed : myAdjLists[vertex]) {
			neighbors.add(ed.to());
		}
		return neighbors;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int i = 0; i < myVertexCount; i++) {
			for (Edge ed : myAdjLists[i]) {
				if (ed.to() == vertex) {
					count++;
					break;
				}
			}
		}
		return count;
	}

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
		HashMap<Integer, Double> priorities = new HashMap<Integer, Double>();
		fringe.insert(startVertex, 0);
		priorities.put(startVertex, 0.);
		HashMap<Integer, Integer> predecessor = new HashMap<Integer, Integer>(
				myVertexCount);
		HashSet<Integer> finished = new HashSet<Integer>();
		for (int i = 0; i < myVertexCount; i++) {
			if (i != startVertex) {
				fringe.insert(i, Double.POSITIVE_INFINITY);
				priorities.put(i, Double.POSITIVE_INFINITY);
			}
		}
		while (true) {
			int current;
			try {
				current = fringe.removeMin().item();
			} catch (NullPointerException e) {
				break;
			}
			for (Edge e : myAdjLists[current]) {
				int neighbor = e.to();
				if (!finished.contains(neighbor)) {
					if (priorities.get(neighbor) > priorities.get(current)
							+ (Integer) e.myEdgeInfo) {
						priorities.put(neighbor, priorities.get(current)
								+ (Integer) e.myEdgeInfo);
						fringe.changePriority(neighbor, priorities.get(current)
								+ (Integer) e.myEdgeInfo);
						predecessor.put(neighbor, current);
					}
				}
			}
			finished.add(current);
			if (current == endVertex)
				break;
		}
		Stack<Integer> temp = new Stack<Integer>();
		Integer current = endVertex;
		if (predecessor.get(current) != null)
			while (current != null) {
				temp.push(current);
				current = predecessor.get(current);
			}
		while (!temp.empty()) {
			path.add(temp.pop());
		}
		return path;
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
			Integer toReturn = null;
			Integer x = fringe.pop();
			if (!visited.contains(x)) {
				toReturn = x;
				for (Integer neighbor : (List<Integer>) neighbors(x)) {
					fringe.push(neighbor);
				}
				visited.add(toReturn);
			}
			return toReturn;
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
			Integer x = iter.next();
			if (x != null)
				result.add(x);
		}
		return result;
	}

	// Returns true iff there exists a path from STARVETEX to
	// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
	// in this graph. If STARVERTEX == STOPVERTEX, returns true.
	public boolean pathExists(int startVertex, int stopVertex) {
		if (startVertex == stopVertex)
			return true;
		else {
			ArrayList<Integer> visitingPath = visitAll(startVertex);
			if (visitingPath.contains(stopVertex)) {
				return true;
			}
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> returnPath = new ArrayList<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> fringe = new Stack<Integer>();
		HashMap<Integer, Integer> steps = new HashMap<Integer, Integer>();
		fringe.push(startVertex);
		while (!fringe.isEmpty()) {
			Integer current = fringe.pop();
			if (current == stopVertex)
				break;
			visited.add(current);
			for (Integer neighbor : (List<Integer>) neighbors(current)) {
				if (!visited.contains(neighbor)) {
					fringe.push(neighbor);
					steps.put(neighbor, current);
				}
			}
		}
		Stack<Integer> temp = new Stack<Integer>();
		if (steps.get(stopVertex) != null)
			temp.push(stopVertex);
		Integer x = stopVertex;
		while (x != null) {
			Integer previous = steps.get(x);
			if (previous != null) {
				temp.push(previous);
			}
			x = previous;
		}
		while (!temp.empty()) {
			returnPath.add(temp.pop());
		}
		return returnPath;
	}

	public ArrayList<Integer> topologicalSort() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new TopologicalIterator();
		while (iter.hasNext()) {
			int x = iter.next();
			result.add(x);
		}
		return result;
	}

	private class TopologicalIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private int[] currentInDegree = new int[myVertexCount];
		private List noAccess = new ArrayList<Integer>();

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			for (int i = 0; i < myVertexCount; i++) {
				if (inDegree(i) == 0)
					fringe.push(i);
				currentInDegree[i] = inDegree(i);
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer x = fringe.pop();
			noAccess.add(x);
			for (Integer neighbor : (List<Integer>) neighbors(x)) {
				currentInDegree[neighbor]--;
				if (currentInDegree[neighbor] <= 0
						&& !noAccess.contains(neighbor)) {
					fringe.push(neighbor);
				}
			}
			return x;
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
		result = g1.visitAll(0);
		System.out.println(result);
		Iterator<Integer> iter;
		iter = result.iterator();
		while (iter.hasNext()) {
			int x = iter.next();
			System.out.println(x + " ");
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
	}

}
