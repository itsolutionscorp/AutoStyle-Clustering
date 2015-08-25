import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	static private class edgeCompare implements Comparator<Edge> {

		@Override
		public int compare(Edge e1, Edge e2) {
			return e2.to() - e1.to();
		}
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
		Collections.sort(myAdjLists[v1], new edgeCompare());

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
			if (e.to() == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	@SuppressWarnings("rawtypes")
	public List neighbors(int vertex) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (Edge e : myAdjLists[vertex]) {
			result.add(e.to());
		}
		return result;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (LinkedList<Edge> lst : myAdjLists) {
			for (Edge e : lst) {
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
			fringe.push(start);
			visited = new HashSet<Integer>();
			visited.add(start);
		}

		public boolean hasNext() {
			return (!fringe.isEmpty());
		}

		public Integer next() {
			Integer result = fringe.pop();
			for (Edge e : myAdjLists[result]) {
				if (!visited.contains(e.to())) {
					fringe.push(e.to());
					visited.add(e.to());
				}
			}
			return result;
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
		while (iter.hasNext()) {
			Integer next = iter.next();
			if (next == stopVertex) {
				return true;
			}
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		boolean finishPath = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<Integer> fringe = new Stack<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		HashMap<Integer, Integer> prevNodeMap = new HashMap<Integer, Integer>();
		fringe.push(startVertex);
		while (!fringe.isEmpty()) {
			Integer currNode = fringe.pop();
			if (currNode == stopVertex) {
				finishPath = true;
				break;
			}
			for (Edge e : myAdjLists[currNode]) {
				if (!visited.contains(e.to())) {
					fringe.push(e.to());
					prevNodeMap.put(e.to(), currNode);
				}
			}
			visited.add(currNode);
		}
		if (finishPath) {
			Stack<Integer> reverseStack = new Stack<Integer>();
			Integer currNode = stopVertex;
			while (currNode != startVertex) {
				reverseStack.push(currNode);
				currNode = prevNodeMap.get(currNode);
			}
			reverseStack.push(currNode);
			while (!reverseStack.isEmpty()) {
				result.add(reverseStack.pop());
			}
			return result;
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

	private class TopologicalIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private int[] currentInDegree;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[myVertexCount];
			// fill in currentInDegree
			for (int i = 0; i < myVertexCount; i++) {
				currentInDegree[i] = inDegree(i);
			}
			fringe.push(zeroDegree());
		}

		private Integer zeroDegree() {
			for (int v = 0; v < myVertexCount; v++) {
				if (currentInDegree[v] == 0) {
					return v;
				}
			}
			return null;
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer result = fringe.pop();
			@SuppressWarnings("unchecked")
			LinkedList<Integer> neighbors = (LinkedList<Integer>) neighbors(result);
			for (Integer k : neighbors) {
				currentInDegree[k] -= 1;
			}
			currentInDegree[result] = -1;
			Integer zeroDegreeResult = zeroDegree();
			if (zeroDegreeResult != null) {
				fringe.push(zeroDegree());
			}
			return result;

		}

		public void remove() {
			throw new UnsupportedOperationException("vertex removal not implemented");
		}

	}

	private class DijkstraAlgorithm {
		
		private ArrayHeap<Integer> fringe;
		HashMap<Integer, Integer> stepMap;

		private DijkstraAlgorithm(int initialNode) {
			fringe = new ArrayHeap<Integer>();
			stepMap = new HashMap<Integer, Integer>();
			fringe.insert(initialNode, 0);
			for (int k = 0; k < myVertexCount; k++) {
				if (k != initialNode) {
					fringe.insert(k, Double.POSITIVE_INFINITY);
				}
			}
		}

		private boolean hasNext() {
			return !fringe.isEmpty();
		}

		private Integer next() {
			Integer currNode = fringe.removeMin().item();
			double currDistance = fringe.getPriority(currNode);
			for (Edge e : myAdjLists[currNode]) {
				Integer adjNode = e.to();
				Integer currStepDistance = (Integer) e.info();
				if (!fringe.contains(adjNode)) {
					continue;
				} else {
					double adjDistance = fringe.getPriority(adjNode);
					double newDistance = currDistance + currStepDistance;
					if (newDistance < adjDistance) {
						fringe.changePriority(adjNode, newDistance);
						stepMap.put(adjNode, currNode);
					}
				}

			}
			return currNode;
		}

	}

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		if (!pathExists(startVertex, endVertex)) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		DijkstraAlgorithm d = new DijkstraAlgorithm(startVertex);
		while (d.hasNext()) {
			Integer next = d.next();
			if (next == endVertex) {
				Stack<Integer> newStack = new Stack<Integer>();
				Integer currNode = endVertex;
				while (currNode != startVertex) {
					newStack.push(currNode);
					currNode = d.stepMap.get(currNode);
				}
				newStack.push(currNode);
				while (!newStack.isEmpty()) {
					result.add(newStack.pop());
				}
				return result;
			}
		}
		return null;

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

		Graph g3 = new Graph(5);
		g3.addEdge(0, 1, 10);
		g3.addEdge(0, 2, 60);
		g3.addEdge(0, 4, 120);
		g3.addEdge(1, 2, 40);
		g3.addEdge(2, 3, 20);
		g3.addEdge(4, 3, 50);
		g3.addEdge(3, 4, 60);
		System.out.println("shortest path from 0 to 4: " + g3.shortestPath(0, 4));
		System.out.println("shortest path from 1 to 0: " + g3.shortestPath(0, 1));
		System.out.println("shortest path from 0 to 2: " + g3.shortestPath(0, 2));
		System.out.println("shortest path from 3 to 0: " + g3.shortestPath(3, 0) + " <--should be no path");
		System.out.println("shortest path from 4 to 2: " + g3.shortestPath(4, 2) + " <--should be no path");
		
	}

}
