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
		// your code here
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		// your code here
		addEdge(v1, v2, edgeInfo);
		addEdge(v2, v1, edgeInfo);
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		// your code here
		Iterator iter = myAdjLists[from].iterator();
		while (iter.hasNext()) {
			Edge cur = (Edge) iter.next();
			if (to == cur.to()) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		// your code here

		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Edge> iter = myAdjLists[vertex].iterator();

		while (iter.hasNext()) {
			result.add(iter.next().to());
		}
		return result;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		// your code here
		for (int i = 0; i < myVertexCount; i++) {
			if (neighbors(i).contains(vertex)) {
				count++;
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
			// your code here
			fringe = new Stack<Integer>();
			fringe.add(start);
			visited = new HashSet<Integer>();
		}

		public boolean hasNext() {
			// your code here
			if (fringe.isEmpty()) {
				return false;
			}
			return true;
		}

		public Integer next() {
			// your code here
			if (!fringe.isEmpty()) {
				int rtn = fringe.pop();

				if (!visited.contains(rtn)) {
					for (Object e : neighbors(rtn)) {
						fringe.add((Integer) e);
					}
					visited.add(rtn);
				}

				return rtn;
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
		// your code here
		if (startVertex == stopVertex) {
			return true;
		}
		for (Integer i : visitAll(startVertex)) {
			if (i == stopVertex) {
				return true;
			}
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		// you supply the body of this method
		if (!pathExists(startVertex, stopVertex)) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> history = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		HashMap<Integer, Integer> steps = new HashMap<Integer, Integer>();

		Integer cur = iter.next();
		Integer prev = cur;
		Integer next = cur;
		while (iter.hasNext()) {

			history.add(cur); // add to visited

			if (iter.hasNext()) {
				next = iter.next();
			}


			if (cur == stopVertex) {
				steps.put(cur, prev);
				break;
			}

			if (neighbors(cur).contains(next)) {
				steps.put(cur, prev);
				prev = cur;
			}

			cur = next;
		}

		Stack<Integer> reversePath = new Stack<Integer>();
		reversePath.push(stopVertex); // first in, last out
		int current = stopVertex;
		while (!steps.isEmpty() && !reversePath.empty()) {
			reversePath.push(steps.get(current));
			steps.remove(current);
			current = reversePath.peek();
			if (current == startVertex) {
				break;
			}
		}

		while (!reversePath.isEmpty()) {
			result.add(reversePath.pop());
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
		private int[] currentInDegree = new int[myVertexCount];

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			// more statements go here
			for (int i = 0; i < myVertexCount; i++) {
				currentInDegree[i] = inDegree(i);
				if (inDegree(i) == 0) {
					fringe.push(i);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			// you supply the real body of this method

			if (!fringe.isEmpty()) {
				int rtn = fringe.pop();
				for (Integer i : neighbors(rtn)) {
					currentInDegree[i] = currentInDegree[i] - 1;
					if (currentInDegree[i] == 0) {
						fringe.push(i);
					}
				}
				return rtn;
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
		Graph test = new Graph(10);
		test.addUndirectedEdge(0, 2);
		test.addUndirectedEdge(0, 3);
		test.addUndirectedEdge(1, 4);
		test.addUndirectedEdge(1, 5);
		test.addUndirectedEdge(2, 7);
		test.addUndirectedEdge(2, 3);
		test.addUndirectedEdge(2, 6);
		test.addUndirectedEdge(4, 5);
		System.out.println(test.pathExists(0, 2)); // true
		System.out.println(test.pathExists(0, 6)); // true
		System.out.println(test.pathExists(0, 1)); // false
		System.out.println(test.pathExists(4, 1)); // true
		System.out.println("Result 0 > 2: " + test.path(0, 2).toString());
		System.out.println("Result 0 > 6: " + test.path(0, 6).toString());

		System.out.println("-----------------------------------------------");
		Graph test2 = new Graph(10);
		test2.addEdge(4, 2);
		test2.addEdge(0, 1);
		test2.addEdge(2, 3);

		test2.addEdge(1, 2);
		test2.addEdge(2, 0);

		System.out.println(test2.pathExists(1, 0)); // true
		System.out.println(test2.pathExists(0, 1)); // true
		System.out.println(test2.pathExists(0, 3)); // true
		System.out.println(test2.pathExists(0, 4)); // false
		System.out.println(test2.pathExists(3, 2)); // false
		System.out.println(test2.pathExists(2, 4)); // false
		System.out.println("Result: " + test2.path(0, 2).toString());
		System.out.println("-----------------------------------------------");

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
	}

}
