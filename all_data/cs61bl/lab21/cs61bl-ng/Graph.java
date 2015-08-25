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
		Edge temp = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(temp);
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
		for (Edge temp : myAdjLists[from]) {
			if (temp.to() == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		// your code here
		return myAdjLists[vertex];
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int i = 0; i < myVertexCount; i++) {
			if (isAdjacent(i, vertex)) {
				count++;
			}
		}
		// your code here
		return count;
	}

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		heap.insert(startVertex, 0);
		if (!pathExists(startVertex, endVertex)){
			return res;
		}
		for (int i = 0; i < myVertexCount; i++) {
			if (i != startVertex) {
				heap.insert(i, Double.POSITIVE_INFINITY);
			}
		}
		while (!heap.isEmpty()) {
			ArrayHeap<Integer>.Node temp = heap.removeMin();
			res.add(temp.item());
			if (temp.item() == endVertex) {
				break;
			}
			for (Edge e : myAdjLists[temp.item()]) {
				heap.changePriority(e.to(), temp.priority() + (Integer) e.info());
			}
		}
		return res;
	}

	//////////////////////////////////////////////////////////////////////////////
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
			fringe.push(start);
		}

		public boolean hasNext() {
			// your code here
			return !fringe.isEmpty();
		}

		public Integer next() {
			// your code here
			Integer temp = null;
			while (hasNext()) {
				temp = fringe.pop();
				visited.add(temp);
				for (Edge e : myAdjLists[temp]) {
					if (!visited.contains(e.to())) {
						fringe.push(e.to());
					}
				}
				break;
			}
			return temp;
		}

		// ignore this method
		public void remove() {
			throw new UnsupportedOperationException("vertex removal not implemented");
		}

	}
	///////////////////////////////////////////////////////////////////////////////////////////

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
		} else {
			if (visitAll(startVertex).contains(stopVertex)) {
				return true;
			}
			return false;
		}
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		// you supply the body of this method
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);

		while (iter.hasNext()) {
			result.add(iter.next());
			if (result.get(result.size() - 1) == stopVertex) {
				break;
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

	//////////////////////////////////////////////////////////////////////////
	private class TopologicalIterator implements Iterator<Integer> {
		private int[] currentInDegree;
		private Stack<Integer> fringe;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			// more statements go here
			currentInDegree = new int[myVertexCount];
			for (int i = 0; i < myVertexCount; i++) {
				if (inDegree(i) == 0) {
					fringe.push(i);
				} else {
					currentInDegree[i] = inDegree(i);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			// you supply the real body of this method
			Integer temp = fringe.pop();
			for (Edge e : myAdjLists[temp]) {
				currentInDegree[e.myTo]--;
				if (currentInDegree[e.myTo] == 0)
					fringe.push(e.myTo);
			}
			return temp;
		}

		public void remove() {
			throw new UnsupportedOperationException("vertex remol not implemented");
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////

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
		g1.addEdge(0, 1, 10);
		g1.addEdge(0, 4, 100);
		g1.addEdge(0, 3, 30);
		g1.addEdge(1, 2, 50);
		g1.addEdge(2, 4, 10);
		g1.addEdge(3, 2, 20);
		g1.addEdge(3, 4, 60);

		ArrayList<Integer> temp = g1.shortestPath(0, 4);
		System.out.println("Shortest Path from 1 to 4");
		for (Integer i : temp) {
			System.out.println(i);
		}

	}

}
