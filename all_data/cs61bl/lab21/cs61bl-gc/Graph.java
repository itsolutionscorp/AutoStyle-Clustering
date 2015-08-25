import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Graph implements Iterable<Integer>{

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

	public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
		ArrayHeap<Integer> pq = new ArrayHeap<Integer>();
		HashMap<Integer, Object> prev = new HashMap<Integer, Object>();
		int [] distance = new int[myVertexCount];

		for(int i=0;i<myAdjLists.length;i++) {
			if(i == startVertex){
				pq.insert(i, 0);
				distance[i] = 0;
			}
			else{
				pq.insert(i, Integer.MAX_VALUE);
				distance[i] = Integer.MAX_VALUE;
			}
		}
		while (pq.peek().item() != endVertex) {
			ArrayHeap.Node n = pq.removeMin();
			int vertexNum = (Integer) n.item();
			for (Object o:neighbors(vertexNum)){
				Edge e = (Edge) o;
				int x = e.myTo;
				int y = (Integer) e.myEdgeInfo + distance[vertexNum];
				if (y < distance[x]) {
					distance[x] = y;
					pq.changePriority(x, distance[x]);
					prev.put(x, vertexNum);
				}
			}
		}

		int result = endVertex;
		ArrayList<Integer> djikstra = new ArrayList<Integer>();
		while (result != startVertex) {
			djikstra.add(0, result);
			result = (Integer) prev.get(result);
		}
		djikstra.add(0,result);
		return djikstra;
	}

	// Change the vertex the iterator will start DFS from
	public void setStartVertex(int v){
		if (v < myVertexCount && v >= 0){
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
		Edge edge1to2 = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(edge1to2);
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		Edge edge1to2 = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(edge1to2);
		Edge edge2to1 = new Edge(v2, v1, edgeInfo);
		myAdjLists[v2].add(edge2to1);
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		for (Edge e:myAdjLists[from]) {
			if (e.myTo.equals(to))
				return true;
		}
		return false;
	}

	// Returns a list of all the neighboring  vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		List result = new ArrayList();
		for (Edge e:myAdjLists[vertex]) {
			result.add(e);
		}
		return result;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int i = 0;i<myAdjLists.length;i++) {
			for (Edge e:myAdjLists[i]) {
				if (e.myTo == vertex)
					count++;
			}
		}
		return count;
	}

	public Iterator<Integer> iterator(){
		return new TopologicalIterator();
	}

	// A class that iterates through the vertices of this graph, starting with a given vertex.
	// Does not necessarily iterate through all vertices in the graph: if the iteration starts
	// at a vertex v, and there is no path from v to a vertex w, then the iteration will not
	// include w
	private class DFSIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private HashSet<Integer> visited;
		private int myStart;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			myStart = start;
			fringe.push(start);
		}

		public boolean hasNext() {
			return ! fringe.isEmpty();
		}

		public Integer next() {
			int result = fringe.pop();

			if (! visited.contains(result)) {
				for (Edge e:myAdjLists[result]) {
					fringe.push(e.myTo);
				}
				visited.add(result);
				boolean remaining = false;
				for (int i:fringe) {
					if (! visited.contains(i)) {
						remaining = true;
					}
				}
				if (! remaining) {
					fringe.clear();
				}
				return result;
			}
			return next();
		}

		//ignore this method
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
		ArrayList<Integer> arr = visitAll(startVertex);
		return arr.contains(stopVertex);
	}


	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (! pathExists(startVertex, stopVertex))
			return result;
		if (startVertex == stopVertex){
			result.add(startVertex);
			return result;
		}
		Iterator<Integer> iter = new DFSIterator(startVertex);
		while (iter.hasNext()) {
			int next = iter.next();
			if (pathExists(next, stopVertex))
				result.add(next);
			if (next == stopVertex)
				break;
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
		Integer[] currentInDegree;
		ArrayList<Integer> results;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			results = new ArrayList<Integer>();
			currentInDegree = new Integer[myVertexCount];
			for (int k = 0; k < myVertexCount; k++) {
				currentInDegree[k] = inDegree(k);
				if (currentInDegree[k] == 0)
					fringe.push(k);
			}

		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int result = fringe.pop();
			results.add(result);
			for (Object o:neighbors(result)) {
				Edge e = (Edge) o;
				currentInDegree[e.myTo]--;
				if (currentInDegree[e.myTo] == 0)
					fringe.push(e.myTo);
			}
			return result;

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
		g1.addEdge(0, 1, 10);
		g1.addEdge(0, 3, 30);
		g1.addEdge(0, 4, 100);
		g1.addEdge(1, 2, 50);
		g1.addEdge(2, 4, 10);
		g1.addEdge(3, 2, 20);
		g1.addEdge(3, 4, 60);

		ArrayList result1 = g1.shortestPath(0, 4);
		System.out.println(result1.toString());
		
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1, 10);
		g2.addEdge(0, 4, 5);
		g2.addEdge(1, 4, 2);
		g2.addEdge(1, 2, 1);
		g2.addEdge(4, 1, 3);
		g2.addEdge(4, 2, 9);
		g2.addEdge(4, 3, 2);
		g2.addEdge(2, 3, 4);
		g2.addEdge(3, 0, 7);
		g2.addEdge(3, 2, 6);
		ArrayList result2 = g2.shortestPath(0, 2);
		System.out.println(result2.toString());
	}

}
