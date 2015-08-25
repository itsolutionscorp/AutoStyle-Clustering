import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.List;

public class Graph implements Iterable<Integer> {

	private LinkedList<Edge>[] myAdjLists;
	private int myVertexCount;
	private int myStartVertex;
	private HashMap<Integer, Integer> predecessors;
	private Stack<Integer> ofPancakes;

	// Initialize a graph with the given number of vertices and no edges.
	public Graph(int numVertices) {
		myAdjLists = new LinkedList[numVertices];
		predecessors = new HashMap<>();
		ofPancakes = new Stack<>();
		myStartVertex = 0;
		for (int k = 0; k < numVertices; k++) {
			myAdjLists[k] = new LinkedList<Edge>();
			predecessors.put(k, null);
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
		Edge edge = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(edge);
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		Edge edge = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(edge);
		myAdjLists[v2].add(edge);
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
	public List neighbors(int vertex) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (Edge e : myAdjLists[vertex]) {
			neighbors.add(e.to());
		}
		return neighbors;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (LinkedList<Edge> l : myAdjLists) {
			for (Edge e : l) {
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
			visited = new HashSet<Integer>();
			fringe.push(start);
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int toReturn = fringe.pop();
			visited.add(toReturn);
			for (Edge e : myAdjLists[toReturn]) {
				if (!visited.contains(e.to())) {
					fringe.push(e.to());
				}
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
			result.add(iter.next());
		}
		return result;
	}

	// Returns true iff there exists a path from STARVETEX to
	// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
	// in this graph. If STARVERTEX == STOPVERTEX, returns true.
	public boolean pathExists(int startVertex, int stopVertex) {
		List<Integer> sheep = visitAll(startVertex);
		for (Integer i : sheep) {
			if (i == stopVertex)
				return true;
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (!pathExists(startVertex, stopVertex)) {
			return result;
		}
		Iterator<Integer> iter = new DFSIterator(startVertex);
		while (iter.hasNext()) {
			result.add(iter.next());
			if (result.get(result.size() - 1) == stopVertex)
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
		private HashSet<Integer> visited;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[myVertexCount];
			visited = new HashSet<Integer>();
			ArrayList<Integer> vertices = visitAll(myStartVertex);
			for (Integer i : vertices) {
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
			Integer toReturn = fringe.pop();
			visited.add(toReturn);
			for (Integer i : ((ArrayList<Integer>) neighbors(toReturn))) {
				currentInDegree[i] -= 1;
			}
			ArrayList<Integer> vertices = visitAll(toReturn);
			for (Integer i : vertices) {
				if (currentInDegree[i.intValue()] == 0) {
					if (!visited.contains(i)) {
						fringe.push(i);
					}
				}
			}
			return toReturn;
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

		@Override
		public boolean equals(Object o) {
			Edge e = (Edge) o;
			return myFrom.equals(e.myFrom) && myTo.equals(e.myTo);
		}

	}

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new dijkstraIterator(startVertex);
		while (iter.hasNext()) {
			iter.next();
		}
		Stack<Integer> overflow = pancakeHelper(endVertex);
		while(!overflow.isEmpty()) {
			result.add(overflow.pop());
		}
		if (result.get(0) != startVertex) {
			return new ArrayList<Integer>();
		}
		return result;
	}
	
	private Stack<Integer> pancakeHelper(Integer i) {
		Integer predecessor = predecessors.get(i);
		ofPancakes.push(i);
		if (predecessor == null) {
			return ofPancakes;
		}
		return pancakeHelper(predecessor);
	}

	private class dijkstraIterator implements Iterator<Integer> {

		private ArrayHeap<Integer> fringe;
		private HashSet<Integer> visited;

		private dijkstraIterator(int myStartVertex) {
			fringe = new ArrayHeap<>();
			fringe.insert(myStartVertex, 0);
			ArrayList<Integer> vertices = visitAll(myStartVertex);
			for (Integer i : vertices) {
				fringe.insert(i, Double.POSITIVE_INFINITY);
			}
			visited = new HashSet<Integer>();
			visited.add(myStartVertex);
		}

		@Override
		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		@Override
		public Integer next() {
			ArrayHeap.Node node = fringe.removeMin();
			Integer toReturn = (Integer) node.item();
			double distance = node.priority();
			List<Integer> neighbors = neighbors(toReturn);
			if (neighbors.size() != 0) {
				for (Integer i : neighbors) {
					if (!visited.contains(i)) {
						visited.add(i);
						predecessors.put(i, toReturn);
						ArrayHeap.Node matoran = fringe.getNode(i);
						Edge ofTomorrow = new Edge(toReturn,
								(int) matoran.item(), null);
						int index = myAdjLists[toReturn.intValue()]
								.indexOf(ofTomorrow);
						Edge ofSword = myAdjLists[toReturn.intValue()]
								.get(index);
						if (distance + (Integer) ofSword.myEdgeInfo < fringe
								.getPriority((Integer) matoran.item())) {
							fringe.changePriority((Integer) matoran.item(),
									distance + (Integer) ofSword.myEdgeInfo);
						}
					}
				}
			}
			return toReturn;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> result;

		// Graph g1 = new Graph(5);
		// g1.addEdge(0, 1);
		// g1.addEdge(0, 2);
		// g1.addEdge(0, 4);
		// g1.addEdge(1, 2);
		// g1.addEdge(2, 0);
		// g1.addEdge(2, 3);
		// g1.addEdge(4, 3);
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
		// g2.addEdge(0, 2);
		// g2.addEdge(0, 4);
		// g2.addEdge(1, 2);
		// g2.addEdge(2, 3);
		// g2.addEdge(4, 3);
		// System.out.println();
		// System.out.println();
		// System.out.println("Topological sort");
		// result = g2.topologicalSort();
		// iter = result.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next() + " ");
		// }
		//
		// Graph g3 = new Graph(7);
		// g3.addUndirectedEdge(0,2);
		// g3.addUndirectedEdge(0,3);
		// g3.addUndirectedEdge(1,4);
		// g3.addUndirectedEdge(1,5);
		// g3.addUndirectedEdge(2,3);
		// g3.addUndirectedEdge(2,6);
		// g3.addUndirectedEdge(4,5);
		// System.out.println(g3.pathExists(1, 5));
		// System.out.println(g3.pathExists(0, 3));
		// System.out.println(g3.pathExists(2, 5));

		Graph g4 = new Graph(5);
		g4.addEdge(0, 1, 5);
		g4.addEdge(1, 2, 4);
		g4.addEdge(2, 0, 3);
		g4.addEdge(2, 3, 2);
		g4.addEdge(4, 2, 8);
		// System.out.println(g4.pathExists(1, 5));
		// System.out.println(g4.pathExists(2, 3));
		// System.out.println(g4.pathExists(4, 4));
		// System.out.println(g4.path(3, 0));

		System.out.println(g4.shortestPath(4, 1));
	}
}