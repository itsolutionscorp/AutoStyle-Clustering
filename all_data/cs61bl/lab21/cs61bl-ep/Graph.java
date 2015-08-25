import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;

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
		if (v1 < myVertexCount && v1 >= 0 && v2 < myVertexCount && v2 >= 0) {
			myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
		} else {
			throw new IllegalArgumentException(
					"Parameters v1 and v2 must both be less than "
							+ myVertexCount
							+ " and greater than or equal to 0.");
		}
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		if (v1 < myVertexCount && v1 >= 0 && v2 < myVertexCount && v2 >= 0) {
			addEdge(v1, v2, edgeInfo);
			addEdge(v2, v1, edgeInfo);
		} else {
			throw new IllegalArgumentException(
					"Parameters v1 and v2 must both be less than "
							+ myVertexCount
							+ " and greater than or equal to 0.");
		}
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		if (from < myVertexCount && from >= 0 && to < myVertexCount && to >= 0) {
			for (Edge edge : myAdjLists[from]) {
				if (edge.to() == to) {
					return true;
				}
			}
			return false;
		} else {
			throw new IllegalArgumentException(
					"Parameters from and to must both be less than "
							+ myVertexCount
							+ " and greater than or equal to 0.");
		}
		// return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		if (vertex < myVertexCount && vertex >= 0) {
			ArrayList<Integer> neighbors = new ArrayList<Integer>();
			for (Edge edge : myAdjLists[vertex]) {
				neighbors.add(edge.to());
			}
			return neighbors;
		} else {
			throw new IllegalArgumentException(
					"Parameter vertex must be less than " + myVertexCount
							+ " and greater than or equal to 0.");
		}
		// return null;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int k = 0; k < myVertexCount; k++) {
			if (isAdjacent(k, vertex)) {
				count++;
			}
		}
		return count;
	}

	
	
	// Dijkstra's Algorithm; returns shortest path from startVertex to endVertex
	// If no path exists, returns an empty arrayList
	// If startVertex == endVertex, returns a one element arrayList
	// Assumes that each edge in the graph has a myEdgeInfo object that is a
	// positive Integer
	// myFringe is a hashmap with key a vertex, value an ArrayList (with
	// predecessor vertex in 0th index & distance from startVertex info in 1st index)
	// myVisited is a hashmap with key a vertex, value the predecessor vertex
	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		HashMap<Integer, ArrayList> myFringe = new HashMap<Integer, ArrayList>();
		HashMap<Integer, Object> myVisited = new HashMap<Integer, Object>();
		ArrayList<Integer> myDijkstraPath = new ArrayList<Integer>();
		
		ArrayList<Integer> testIfPathExists = path(startVertex, endVertex);
		if(testIfPathExists.size() == 0) {
			return myDijkstraPath;
		}
		if (startVertex == endVertex) {
			myDijkstraPath.add(startVertex);
			return myDijkstraPath;
		}
		
		for (int k = 0; k < myVertexCount; k++) {
			myFringe.put(k, new ArrayList());
			myFringe.get(k).add(0, null);
			if (k == startVertex) {
				myFringe.get(k).add(1, 0);
			} else {
				myFringe.get(k).add(1, Double.POSITIVE_INFINITY);
			}
		}

		int toReturn = startVertex;
		while (toReturn != endVertex) {
			int distanceFromStart = (int) myFringe.get(toReturn).get(1);
			for (Edge neighbor : myAdjLists[toReturn]) {
				if (myFringe.containsKey(neighbor.to())) {
					int possibleNewDistanceFromStart = distanceFromStart
							+ (int) neighbor.myEdgeInfo;
					if (myFringe.get(neighbor.to()).get(1)
							.equals(Double.POSITIVE_INFINITY)
							|| possibleNewDistanceFromStart < (int) myFringe.get(
									neighbor.to()).get(1)) {
						myFringe.get(neighbor.to()).set(0, toReturn);
						myFringe.get(neighbor.to()).set(1, possibleNewDistanceFromStart);
						
					}
				}
			}

			myVisited.put(toReturn, myFringe.get(toReturn).get(0));
			myFringe.remove(toReturn);

			int smallestDistance = 2147483647;
			for (int k = 0; k < myVertexCount; k++) {
				if (myFringe.containsKey(k)
						&& !myFringe.get(k).get(1)
								.equals(Double.POSITIVE_INFINITY)) {
					if ((int) myFringe.get(k).get(1) < smallestDistance) {
						smallestDistance = (int) myFringe.get(k).get(1);
						toReturn = k;
					}
				}
			}	
		}
		
		myVisited.put(toReturn, myFringe.get(toReturn).get(0));
		myFringe.remove(toReturn);
		
		Integer myPredecessor = endVertex;
		while (myPredecessor != null) {
			myDijkstraPath.add(0, myPredecessor);
			if (myVisited.get(myPredecessor) != null) {
				myPredecessor = (Integer) myVisited.get(myPredecessor);
			} else {
				break;
			}
		}
		return myDijkstraPath;
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
			return !fringe.empty();
			// return false;
		}

		public Integer next() {
			Integer nextVertex = fringe.pop();
			/*
			 * if (!visited.contains(nextVertex)) { visited.add(nextVertex);
			 * List<Integer> vertexNeighbors = neighbors(nextVertex);
			 * Collections.sort(vertexNeighbors); for (int i =
			 * vertexNeighbors.size()-1; i >= 0; i--){
			 * fringe.push(vertexNeighbors.get(i)); } return nextVertex; } else
			 * { return next(); }
			 */
			while (visited.contains(nextVertex)) {
				if (!fringe.empty()) {
					nextVertex = fringe.pop();
				} else {
					return null;
				}
			}
			visited.add(nextVertex);
			List<Integer> vertexNeighbors = neighbors(nextVertex);
			Collections.sort(vertexNeighbors);
			for (int i = vertexNeighbors.size() - 1; i >= 0; i--) {
				if (!visited.contains(vertexNeighbors.get(i))) {
					fringe.push(vertexNeighbors.get(i));
				}
			}
			return nextVertex;

			// return null;
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
		ArrayList<Integer> paths = visitAll(startVertex);
		if (paths.contains(stopVertex)) {
			return true;
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		// return new ArrayList<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);

		if (startVertex == stopVertex) {
			path.add(startVertex);
			return path;
		}
		Integer justAdded = startVertex;
		while (iter.hasNext() && justAdded != stopVertex) {
			justAdded = iter.next();
			path.add(justAdded);
		}
		if (justAdded != stopVertex) {
			path.clear();
			return path;
		} else {
			// return path;
			ArrayList<Integer> result = new ArrayList<Integer>();
			int toIndex = path.size() - 1;
			result.add(path.get(toIndex));
			int fromIndex = toIndex - 1;

			while (fromIndex >= 0) {
				if (isAdjacent(path.get(fromIndex), path.get(toIndex))) {
					result.add(0, path.get(fromIndex));
					toIndex = fromIndex;
					fromIndex = toIndex - 1;
				} else {
					fromIndex--;
				}
			}
			if (result.get(0) == startVertex) {
				return result;
			} else {
				result.clear();
				return result;
			}

		}

		// return path;

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

		private HashSet<Integer> visited;
		private int[] currentInDegree;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[myVertexCount];
			visited = new HashSet<Integer>();
			for (int k = 0; k < myVertexCount; k++) {
				currentInDegree[k] = inDegree(k);
			}
			for (int k = myVertexCount - 1; k >= 0; k--) {
				if (currentInDegree[k] == 0) {
					fringe.add(k);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer toReturn = fringe.pop();
			while (visited.contains(toReturn)) {
				if (!fringe.empty()) {
					toReturn = fringe.pop();
				} else {
					return null;
				}
			}
			visited.add(toReturn);
			List<Integer> toReturnNeighbors = neighbors(toReturn);
			for (Integer vertex : toReturnNeighbors) {
				currentInDegree[vertex] = currentInDegree[vertex] - 1;
			}
			for (int k = myVertexCount - 1; k >= 0; k--) {
				if (currentInDegree[k] == 0 && !visited.contains(k)) {
					fringe.add(k);
				}
			}
			return toReturn;
			// return new Integer(0);
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
		System.out.println();
		System.out.println();
		Graph g3 = new Graph(7);
		g3.addUndirectedEdge(0, 2);
		g3.addUndirectedEdge(0, 3);
		g3.addUndirectedEdge(1, 4);
		g3.addUndirectedEdge(1, 5);
		g3.addUndirectedEdge(2, 3);
		g3.addUndirectedEdge(2, 6);
		g3.addUndirectedEdge(4, 5);
		System.out.println(g3.pathExists(0, 6));
		System.out.println();
		System.out.println();
		Graph g4 = new Graph(5);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		g4.addEdge(2, 3);
		g4.addEdge(4, 2);
		System.out.println(g4.pathExists(0, 4));
		System.out.println();
		System.out.println();
		System.out.println("Dijkstra's Algorithm");
		Graph g5 = new Graph(5);
		g5.addEdge(0, 1, 10);
		g5.addEdge(0, 3, 30);
		g5.addEdge(0, 4, 100);
		g5.addEdge(1, 2, 50);
		g5.addEdge(2, 4, 10);
		g5.addEdge(3, 2, 20);
		ArrayList<Integer> myDijkstra = g5.shortestPath(0, 4);
		for (Integer node : myDijkstra) {
			System.out.println(node);
		}
	}

}
