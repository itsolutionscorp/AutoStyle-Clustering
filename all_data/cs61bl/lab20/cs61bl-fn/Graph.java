import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
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
		for (Edge j : myAdjLists[from]) {
			if (j.to() == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (Edge j : myAdjLists[vertex]) {
			neighbors.add(j.to());
		}
		return neighbors;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		LinkedList<Edge> temp;
		for (int i = 0; i < myVertexCount; i++) {
			if (i != vertex) {
				temp = myAdjLists[i];
				for (Edge j : temp) {
					if (j.to() == vertex) {
						count++;
					}
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
		private LinkedList<Edge> current;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			fringe.push(start);
			visited.add(start);
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			if (hasNext()) {
				int tempVertex = fringe.pop();
				current = myAdjLists[tempVertex];
				for (Edge i : current) {
					if (!visited.contains(i.to())) {
						visited.add(i.to());
						fringe.push(i.to());
					}
				}
				return tempVertex;
			}
			return null;
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
		return visitAll(startVertex).contains(stopVertex);
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		if (pathExists(startVertex, stopVertex)) {
			Set<Integer> visited = new HashSet<>();
			Stack<Integer> fringe = new Stack<>();
			Map<Integer, Integer> steps = new HashMap<>();
			fringe.push(startVertex);
			while (!fringe.isEmpty()) {
				int currentVertex = fringe.pop();
				if (currentVertex == stopVertex) {
					break;
				}
				visited.add(currentVertex);
				for (Edge g : myAdjLists[currentVertex]) {
					if (!visited.contains(g.to())) {
						steps.put(g.to(), currentVertex);
						fringe.push(g.to());
					}
				}
			}
			Stack<Integer> reversePath = new Stack<>();
			Integer currentStep = stopVertex;
			while (currentStep != null) {
				Integer previousStep = steps.get(currentStep);
				if (previousStep != null) {
					reversePath.push(previousStep);
				}
				currentStep = previousStep;
			}
			ArrayList<Integer> path = new ArrayList<Integer>();
			while (!reversePath.isEmpty()) {
				path.add(reversePath.pop());
			}
			path.add(stopVertex);
			return path;
		} else {
			return new ArrayList<Integer>();
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
		private HashMap<Integer, Integer> InDegrees;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			InDegrees = new HashMap<Integer, Integer>();
			for (int i = 0; i < myVertexCount; i++) {
				int degree = inDegree(i);
				if (degree == 0) {
					fringe.push(i);
				}
				InDegrees.put(i, degree);
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			if (hasNext()) {
				int temp = fringe.pop();
				InDegrees.put(temp, InDegrees.get(temp) - 1);
				for (int j : neighbors(temp)) {
					int newDegree = InDegrees.get(j) - 1;
					InDegrees.put(j, newDegree);
					if (newDegree == 0) {
						fringe.push(j);
					}
				}
				return temp;
			}
			return null;
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
		 g3.addEdge(0, 3);
		 g3.addEdge(1, 3);
		 g3.addEdge(1, 4);
		 g3.addEdge(2, 5);
		 g3.addEdge(3, 5);
		 g3.addEdge(4, 6);
		 g3.addEdge(5, 7);
		 g3.addEdge(6, 7);
		 System.out.println();
		 System.out.println();
		 System.out.println("Topological sort");
		 result = g3.topologicalSort();
		 iter = result.iterator();
		 while (iter.hasNext()) {
		 System.out.println(iter.next() + " ");
		 }
	}

}
