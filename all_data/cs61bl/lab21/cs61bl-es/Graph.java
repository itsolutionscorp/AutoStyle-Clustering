import java.util.ArrayList;
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
	private ArrayHeap<Integer> fringe;

	// Initialize a graph with the given number of vertices and no edges.
	public Graph(int numVertices) {
		myAdjLists = new LinkedList[numVertices];
		myStartVertex = 0;
		for (int k = 0; k < numVertices; k++) {
			myAdjLists[k] = new LinkedList<Edge>();
		}
		myVertexCount = numVertices;
		fringe = new ArrayHeap<Integer>();
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
		LinkedList<Edge> neighbors = myAdjLists[from];
		for (Edge neighbor : neighbors) {
			if (neighbor.to() == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring  vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		LinkedList<Edge> neighbors = myAdjLists[vertex];
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		for (Edge neighbor : neighbors) {
			rtn.add(neighbor.to());
		}
		return rtn;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int from = 0; from < myVertexCount; from++) {
			LinkedList<Edge> neighbors = myAdjLists[from];
			for (Edge neighbor : neighbors) {
				if (neighbor.to() == vertex) {
					count++;
				}
			}
		}
		return count;
	}
	
	public Edge getEdge(int start, int end) {
		LinkedList<Edge> edges = myAdjLists[start];
		for (Edge e : edges) {
			if (e.to() == end) {
				return e;
			}
		}
		return null;
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

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			fringe.push(start);
			visited = new HashSet<Integer>();
		}

		public boolean hasNext() {
			return !fringe.empty();
		}

		public Integer next() {
			Integer rtn = fringe.pop();
			visited.add(rtn);
			LinkedList<Edge> neighbors = myAdjLists[rtn];
			for (Edge neighbor : neighbors) {
				if (!visited.contains(neighbor.to())) {
					fringe.push(neighbor.to());
				}
			}
			return rtn;
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

	// Returns true iff there exists a path from STARTVERTEX to
	// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
	// in this graph. If STARTVERTEX == STOPVERTEX, returns true.
	public boolean pathExists(int startVertex, int stopVertex) {
		if (startVertex == stopVertex) {
			return true;
		}
		ArrayList<Integer> canVisit = visitAll(startVertex);
		return canVisit.contains(stopVertex);
	}


	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (startVertex == stopVertex) {
			result.add(startVertex);
			return result;
		}
		else if (pathExists(startVertex, stopVertex)) {
			Iterator<Integer> iter = new DFSIterator(startVertex);
			ArrayList<Integer> visited = new ArrayList<Integer>();
			while (iter.hasNext()) {
				Integer current = iter.next();
				if (current == stopVertex) {
					result.add(stopVertex);
					break;
				}
				visited.add(current);
			}
			while (result.get(0) != startVertex) {
				Integer to = result.get(0);
				for (Integer from : visited) {
					if (isAdjacent(from, to)) {
						result.add(0, from);
						visited.remove(new Integer(from));
						break;
					}
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

	public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
		if (!pathExists(startVertex, endVertex)) {
			return null;
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		if (startVertex == endVertex) {
			path.add(startVertex);
			return path;
		}
		fringe.insert(startVertex, 0);
		ArrayHeap.Node currentVertex = null;
		ArrayList<Integer> processed = new ArrayList<Integer>();
		ArrayList<ArrayHeap.Node> processedNodes = new ArrayList<ArrayHeap.Node>();
		while (!fringe.isEmpty()) {
			currentVertex = fringe.peek();
			Integer currentVertexItem = (Integer) currentVertex.item();
			processed.add(currentVertexItem);
			processedNodes.add(currentVertex);
			double currentPriority = currentVertex.priority();
			
			ArrayList<Integer> neighbors = new ArrayList<Integer>(neighbors(currentVertexItem));
			if (currentVertex.item().equals(endVertex)) {
				break;
			}
			else {
				for (Integer neighbor : neighbors) {
					double newPriority = currentPriority + (Integer) getEdge(currentVertexItem, neighbor).info();
					if (!processed.contains(neighbor)) {
						if (!fringe.contains(neighbor)) {
							fringe.insert(neighbor, Double.POSITIVE_INFINITY);
						}
						fringe.changePredAndPriority(neighbor, currentVertexItem, newPriority);
					}
				}
			}
			fringe.removeMin();
		}
		path.add(endVertex);
		while (!currentVertex.predecessor().equals(startVertex)) {
			path.add(0, (Integer) currentVertex.predecessor());
			for (ArrayHeap.Node n : processedNodes) {
				if (n.item().equals(currentVertex.predecessor())) {
					currentVertex = n;
					break;
				}
			}
		}
		path.add(0, startVertex);
		return path;
	}

	private class TopologicalIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private int[] currentInDegree;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[myVertexCount];
			for (int vertex = 0; vertex < myVertexCount; vertex++) {
				int degree = inDegree(vertex);
				currentInDegree[vertex] = degree;
				if (degree == 0) {
					fringe.push(vertex);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int rtn = fringe.pop();
			List neighbors = neighbors(rtn);
			for (Object neighbor : neighbors) {
				currentInDegree[(Integer) neighbor]--;
				if (currentInDegree[(Integer) neighbor] == 0) {
					fringe.push((Integer) neighbor);
				}
			}
			return rtn;
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
		System.out.println("Path from 0 to 3"); // should be 0 4 3
		result = g1.path(0, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 0 to 4"); // should be 0 4
		result = g1.path(0, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 1 to 3"); // should be 1 2 3
		result = g1.path(1, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 1 to 4"); // should be 1 2 0 4
		result = g1.path(1, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 4 to 0"); // should be no path
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

		Graph g3 = new Graph(7);
		g3.addUndirectedEdge(0,2); 
		g3.addUndirectedEdge(0,3); 
		g3.addUndirectedEdge(1,4); 
		g3.addUndirectedEdge(1,5); 
		g3.addUndirectedEdge(2,3); 
		g3.addUndirectedEdge(2,6); 
		g3.addUndirectedEdge(4,5); 
		// none of these should print false
		System.out.println(g3.pathExists(2, 0));
		System.out.println(g3.pathExists(6, 0));
		System.out.println(g3.pathExists(3, 6));
		System.out.println(!g3.pathExists(0, 1));
		System.out.println(!g3.pathExists(0, 4));
		System.out.println(!g3.pathExists(0, 5));

		Graph g4 = new Graph(5);
		g4.addEdge(0,1); 
		g4.addEdge(1,2); 
		g4.addEdge(2,0); 
		g4.addEdge(2,3); 
		g4.addEdge(4,2); 
		// none of these should print false
		System.out.println(g4.pathExists(0, 1));
		System.out.println(g4.pathExists(1, 0));
		System.out.println(!g4.pathExists(0, 4));
		System.out.println(g4.pathExists(0, 3));
		System.out.println(!g4.pathExists(3, 0));
		
		
		System.out.println("----TESTING SHORTEST PATH----");
		Graph g5 = new Graph(8);
		g5.addEdge(0, 1, 20);
		g5.addEdge(1, 5, 10);
		g5.addEdge(5, 2, 10);
		g5.addEdge(2, 5, 50);
		g5.addEdge(2, 7, 20);
		g5.addEdge(3, 2, 10);
		g5.addEdge(2, 3, 10);
		g5.addEdge(5, 3, 40);
		g5.addEdge(0, 3, 80);
		g5.addEdge(4, 1, 50);
		g5.addEdge(3, 6, 20);
		g5.addEdge(0, 6, 90);
		g5.addEdge(6, 0, 20);
		g5.addEdge(4, 6, 30);
		ArrayList<Integer> l = g5.shortestPath(0, 7);
		System.out.println(l.toString()); // should print [0, 1, 5, 2, 7]
		System.out.println(g5.shortestPath(0, 0).toString()); // should print [0]
		System.out.println(g5.shortestPath(0, 4)); // should print null
		System.out.println(g5.shortestPath(3, 5)); // should print [3, 2, 5]
		
		g5 = new Graph(8);
		g5.addEdge(0, 1, 20);
		g5.addEdge(1, 5, 10);
		g5.addEdge(5, 2, 200);
		g5.addEdge(2, 5, 50);
		g5.addEdge(2, 7, 20);
		g5.addEdge(3, 2, 10);
		g5.addEdge(2, 3, 10);
		g5.addEdge(5, 3, 40);
		g5.addEdge(0, 3, 80);
		g5.addEdge(4, 1, 50);
		g5.addEdge(3, 6, 20);
		g5.addEdge(0, 6, 90);
		g5.addEdge(6, 0, 20);
		g5.addEdge(4, 6, 30);
		l = g5.shortestPath(0, 7);
		System.out.println(l.toString()); // should print [0, 1, 5, 3, 2, 7]
		
		g5 = new Graph(8);
		g5.addEdge(0, 1, 900);
		g5.addEdge(1, 5, 10);
		g5.addEdge(5, 2, 200);
		g5.addEdge(2, 5, 50);
		g5.addEdge(2, 7, 20);
		g5.addEdge(3, 2, 10);
		g5.addEdge(2, 3, 10);
		g5.addEdge(5, 3, 40);
		g5.addEdge(0, 3, 80);
		g5.addEdge(4, 1, 50);
		g5.addEdge(3, 6, 20);
		g5.addEdge(0, 6, 90);
		g5.addEdge(6, 0, 20);
		g5.addEdge(4, 6, 30);
		l = g5.shortestPath(0, 7);
		System.out.println(l.toString()); // should print [0, 3, 2, 7]
	}

}
