import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
		// your code here
		Edge e = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(e);
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
		List<Edge> l = myAdjLists[from];
		for (Edge e : l) {
			if (e.myFrom == from && e.myTo == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		// your code here
		List<Integer> result = new LinkedList<Integer>();
		for (LinkedList<Edge> l : myAdjLists) {
			for (Edge e : l) {
				if (e.myFrom == vertex) {
					result.add(e.myTo);
				} else if (e.myTo == vertex) {
					result.add(e.myFrom);
				}
			}
		}

		return result;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		// your code here
		for (List<Edge> l : myAdjLists) {
			for (Edge e : l) {
				if (e.myTo == vertex) {
					count++;
					break;
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
			int temp = fringe.pop();
			visited.add(temp);
			List<Edge> l = myAdjLists[temp];
			for (Edge e : l) {
				if (!visited.contains(e.myTo)) {
					fringe.push(e.myTo);
				}
			}
			return temp;
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
		Iterator<Integer> iter = new DFSIterator(startVertex);
		HashSet<Integer> visited = new HashSet<Integer>();
		while (iter.hasNext()) {

			visited.add(iter.next());
			if (visited.contains(stopVertex)) {
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
		ArrayList<Integer> tempResult = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);

		while (iter.hasNext()) {
			int temp = iter.next();
			tempResult.add(temp);
			if (temp == stopVertex) {
				break;
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(tempResult.get(tempResult.size() - 1));
		for (int k = tempResult.size() - 1; k >= 0; k--) {
			int last = result.get(result.size() - 1);
			int temp = tempResult.get(k);
			if (isAdjacent(temp, last)) {
				result.add(temp);
			}

		}
		tempResult = new ArrayList<Integer>();
		if (result.contains(stopVertex)) {
			for (int i = result.size() - 1; i >= 0; i--) {
				tempResult.add(result.get(i));
			}
		}

		return tempResult;
	}

	/**
	 * shortestPath
	 * 
	 * Uses Dijkstra's algorithm to find the shortest path in a graph from
	 * startVertex to endVertex
	 * 
	 * Assume myEdgeInfo is positive Integer object
	 * 
	 * @param startVertex
	 * @param endVertex
	 * @return arraylist of integer objects
	 */
	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		Map<Integer, Double> vertexToPriorityMap = new HashMap<Integer, Double>();
		Map<Integer, Integer> vertexPrevMap = new HashMap<Integer, Integer>();
		double distToStart = 0;
		double distToNode = Integer.MAX_VALUE;

		// STEP 0: Add all nodes to fringe
		for (int i = 0; i < myVertexCount; i++) {
			if (i == startVertex) {
				fringe.insert(i, distToStart);
				vertexToPriorityMap.put(i, distToStart);
				vertexPrevMap.put(i, null);
			} else {
				fringe.insert(i, distToNode);
				vertexToPriorityMap.put(i, distToNode);
			}
		}
		List<Edge> edgesVertexHas;
		// STEP 1: Add adjacents of startNode to heap with distance
		while (fringe.peek() != null) {
			ArrayHeap<Integer>.Node temp = fringe.peek();
			edgesVertexHas = myAdjLists[temp.item()];
			for (Edge e : edgesVertexHas) {

				int vertex = e.myTo;
				if (!visited.contains(vertex)) {

					double previousD = vertexToPriorityMap.get(vertex);
					double challengerD = vertexToPriorityMap.get(temp.item())
							+ (double) e.myEdgeInfo;
					if (previousD > challengerD) {
						fringe.changePriority(vertex, challengerD);
						vertexToPriorityMap.put(vertex, challengerD);
						vertexPrevMap.put(vertex, temp.item());
					}
				}

			}
			visited.add(temp.item());
			fringe.removeMin();

		}
		// System.out.println(vertexToPriorityMap.get(3));

		ArrayList<Integer> resultReverse = new ArrayList<Integer>();
		int temp = endVertex;
		resultReverse.add(temp);
		while (vertexPrevMap.get(temp) != null) {
			temp = vertexPrevMap.get(temp);
			resultReverse.add(temp);
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = resultReverse.size() - 1; i >= 0; i--) {
			result.add(resultReverse.get(i));
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

		// more instance variables go here
		private int[] currentInDegree;
		private Set<Integer> visited;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			// more statements go here
			currentInDegree = new int[myVertexCount];
			visited = new HashSet<Integer>();
			for (int i = 0; i < myVertexCount; i++) {
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
			int temp = fringe.pop();
			visited.add(temp);
			List<Integer> l = neighbors(temp);
			for (int i : l) {
				currentInDegree[i]--;
			}
			for (int i = 0; i < myVertexCount; i++) {
				if (currentInDegree[i] == 0 && !visited.contains(i)
						&& !fringe.contains(i)) {
					visited.add(i);
					fringe.push(i);
				}
			}
			return temp;
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

		Graph g1 = new Graph(5);
		g1.addEdge(0, 1, 10.0);
		g1.addEdge(0, 4, 5.0);
		g1.addEdge(1, 4, 2.0);
		g1.addEdge(1, 2, 1.0);
		g1.addEdge(2, 3, 4.0);
		g1.addEdge(3, 0, 7.0);
		g1.addEdge(3, 2, 6.0);
		g1.addEdge(4, 3, 2.0);
		g1.addEdge(4, 1, 3.0);
		g1.addEdge(4, 2, 9.0);
		System.out.println(g1.shortestPath(0, 2));
		// System.out.println("Traversal starting at 0");
		// result = g1.visitAll(0);
		// Iterator<Integer> iter;
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Traversal starting at 2");
		// result = g1.visitAll(2);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Traversal starting at 1");
		// result = g1.visitAll(1);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Traversal starting at 3");
		// result = g1.visitAll(3);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Traversal starting at 4");
		// result = g1.visitAll(4);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Path from 0 to 3");
		// result = g1.path(0, 3);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Path from 0 to 4");
		// result = g1.path(0, 4);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Path from 1 to 3");
		// result = g1.path(1, 3);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Path from 1 to 4");
		// System.out.println(g1.pathExists(1, 4));
		// result = g1.path(1, 4);
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		// System.out.println();
		// System.out.println();
		// System.out.println("Path from 4 to 0");
		// result = g1.path(4, 0);
		// if (result.size() != 0) {
		// System.out.println("*** should be no path!");
		// }
		//
		// Graph g2 = new Graph(5);
		// g2.addEdge(0, 1);
		// g2.addEdge(0, 3);
		// g2.addEdge(1, 2);
		// // g2.addEdge(2, 3);
		// g2.addEdge(2, 4);
		// g2.addEdge(3, 4);
		//
		// System.out.println();
		// System.out.println();
		// System.out.println("Topological sort");
		// result = g2.topologicalSort();
		// iter = result.iterator();
		// // System.out.println(result.size());
		// while (iter.hasNext()) {
		// System.out.print(iter.next() + " ");
		// }
	}

}
