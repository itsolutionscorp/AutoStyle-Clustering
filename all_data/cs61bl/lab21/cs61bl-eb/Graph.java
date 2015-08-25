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

public class Graph implements Iterable<Integer>{

	private LinkedList<Edge>[] myAdjLists; // neighbors
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
		Edge edge = new Edge(v1, v2, edgeInfo); 
		myAdjLists[v1].add(edge); 
		Collections.sort(myAdjLists[v1], new c());

	}





	static private class c implements Comparator<Edge>{

		// Descending order!
		@Override
		public int compare(Edge e1, Edge e2){
			return e2.myTo - e1.myTo;
		}
	}





	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		Edge edge = new Edge(v1, v2, edgeInfo); 
		myAdjLists[v1].add(edge); 
		Edge edge2 = new Edge(v2, v1, edgeInfo); 
		myAdjLists[v2].add(edge2); 
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		for (Edge e : myAdjLists[from]) {
			if (e.myTo == to) {
				return true; 
			}
		}
		return false;
	}

	// Returns a list of all the neighboring  vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		LinkedList<Integer> result = new LinkedList<Integer>(); 
		for (Edge e : myAdjLists[vertex]) {
			result.add(e.myTo); 
		}
		return result; 
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (LinkedList<Edge> l : myAdjLists) {
			for (Edge e : l) {
				if (e.myTo == vertex) {
					count++; 
				}
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

		private Stack<Integer> fringe; // all the unvisited vertices  
		private HashSet<Integer> visited;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>(); 
			visited = new HashSet<Integer>(); 
			fringe.push(start); 
			visited.add(start); 
		}

		public boolean hasNext() {
			return (!fringe.isEmpty()); 
		}

		public Integer next() {
			Integer rtn = fringe.pop(); 
			for (Edge e : myAdjLists[rtn]) {
				if (!visited.contains(e.myTo)) {
					fringe.push(e.myTo);
					visited.add(e.myTo); 
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

	// Returns true iff there exists a path from STARVETEX to
	// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
	// in this graph. If STARVERTEX == STOPVERTEX, returns true.
	public boolean pathExists(int startVertex, int stopVertex) {
		Iterator<Integer> i = new DFSIterator(startVertex); 
		// Note: if you do not <Integer> then you need to cast i.next() to (Integer) i.next() below
		while (i.hasNext()) {
			Integer next = i.next(); 
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
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		Stack<Integer> fringe = new Stack<Integer>(); // all the unvisited vertices  
		HashSet<Integer> visited = new HashSet<Integer>(); ;
		HashMap<Integer, Integer> curr_prev  = new HashMap<Integer, Integer>(); 
		// Note: maps from current vertex to previous vertex  
		fringe.push(startVertex); 
		boolean hasFoundPath = false; 
		while (!fringe.isEmpty()) {
			Integer curr = fringe.pop(); 
			if (curr == stopVertex) {
				hasFoundPath = true;
				break; 
			}
			for (Edge e : myAdjLists[curr]) {
				if (!visited.contains(e.myTo)) {
					fringe.push(e.myTo);
					curr_prev.put(e.myTo, curr); 
				}
			}
			visited.add(curr);
		}
		if (hasFoundPath) {
			Integer curr = stopVertex; 

			// Trace backwards 
			Stack<Integer> temp = new Stack<Integer>();

			while (curr != startVertex) {
				temp.push(curr); 
				curr = curr_prev.get(curr); 
			}
			temp.push(curr); 

			while (!temp.isEmpty()) {
				rtn.add(temp.pop()); 
			}
			return rtn; 

		} else {
			return rtn;
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
		private int[] currentInDegree; 

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[myVertexCount]; 
			// fill in currentInDegree
			for (int i = 0; i < myVertexCount; i++) {
				currentInDegree[i] = inDegree(i); 
			}
			fringe.push(findZero()); // ask on Piazza which one to push 
			// Note: 1) at the beginning, it is gauranteed there is at least
			// 					1 node that has at least 1 zero 
			// 			 2) we will change inDegree to -1 in next() 
		}

		private Integer findZero() {
			for (int i = 0; i < myVertexCount; i++) {
				if (currentInDegree[i] == 0) {
					return i; 
				}
			}
			return null; 
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer rtn = fringe.pop(); 
			LinkedList<Integer> neighbors = (LinkedList<Integer>) neighbors(rtn); 
			for (Integer i : neighbors) {
				currentInDegree[i]--; 
			}
			currentInDegree[rtn] = -1; // it will not be visited again 
			Integer findZeroResult = findZero(); 
			if (findZeroResult != null) {
				fringe.push(findZero()); 
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
		
		System.out.println("*************************Dijkstra's Algorithm1*******");
		Graph g3 = new Graph(5);
		g3.addEdge(0, 1, 10);
		g3.addEdge(0, 3, 30);
		g3.addEdge(0, 4, 100);
		g3.addEdge(1, 2, 50);
		g3.addEdge(2, 4, 10);
		g3.addEdge(3, 2, 20);
		g3.addEdge(3, 4, 60);
		System.out.println("g3.shortestPath(0, 4) " + g3.shortestPath(0, 4));
		System.out.println("g3.shortestPath(0, 1) " + g3.shortestPath(0, 1));
		System.out.println("g3.shortestPath(0, 2) " + g3.shortestPath(0, 2));
		System.out.println("g3.shortestPath(3, 0) " + g3.shortestPath(3, 0));
		
		System.out.println("*************************Dijkstra's Algorithm2*******");
		Graph g4 = new Graph(5);
		g4.addEdge(0, 1, 10);
		g4.addEdge(0, 4, 5);
		g4.addEdge(1, 2, 1);
		g4.addEdge(1, 4, 2);
		g4.addEdge(2, 3, 4);
		g4.addEdge(3, 0, 7);
		g4.addEdge(3, 2, 6);
		g4.addEdge(4, 1, 3);
		g4.addEdge(4, 2, 9);
		g4.addEdge(4, 3, 2); 

		System.out.println("g4.shortestPath(0, 4) " + g4.shortestPath(0, 4));
		System.out.println("g4.shortestPath(0, 1) " + g4.shortestPath(0, 1));
		System.out.println("g4.shortestPath(0, 2) " + g4.shortestPath(0, 2));
		System.out.println("g4.shortestPath(3, 0) " + g4.shortestPath(3, 0));
		System.out.println("g4.shortestPath(4, 2) " + g4.shortestPath(4, 2));
	}
	
	
	
	
	
	
	
	
	
	
	private class DijkstraAlgorithm {
		// Note: non-static class because need to use myVertexCount which is non-static
		private ArrayHeap<Integer> fringe;
		HashMap<Integer, Integer> steps;
		private Integer start;
		
		private DijkstraAlgorithm(Integer start) {
			fringe = new ArrayHeap<Integer>();
			steps = new HashMap<Integer, Integer>();
			this.start = start;
			// Add the start vertex to the fringe with distance zero
			fringe.insert(start, 0);
			// Then, add all other vertices to the fringe with distance infinity
			for (Integer i = 0; i < myVertexCount; i++) {
				if (i != start) {
					fringe.insert(i, Double.POSITIVE_INFINITY);
				}
			}
		}
		
		
		private boolean hasNext() {
			return !fringe.isEmpty();
		}
		
		private Integer next() {
			// Remove and save the vertex v in the fringe for which the distance from start is minimal
			Integer curr = fringe.removeMin().item();
			double start_to_curr_distance = fringe.getPriority(curr);
			// Then, for each neighbor w of v, do the following:
			for (Edge e : myAdjLists[curr]) {
				Integer neighbor = e.myTo;
				Integer curr_to_neigh_distance = (Integer) e.myEdgeInfo;
				if (!fringe.contains(neighbor)) {
					// 1. If w is not in the fringe, do nothing 
					// (we've already found the shortest path from the start vertex to w).
				} else {
					// 2. Otherwise, w's distance might need updating
					// if the path through v to w is a shorter path
					// than what we've seen so far. 
					// In that case, replace the distance for w's fringe entry
					// with the distance from start to v 
					// plus the weight of the edge (v, w), and replace its predecessor with v
					double neighbor_curr_distance = fringe.getPriority(neighbor);
					if (start_to_curr_distance + curr_to_neigh_distance < neighbor_curr_distance) {
						fringe.changePriority(neighbor, start_to_curr_distance + curr_to_neigh_distance);
						steps.put(neighbor, curr);
					}
				}
			
			}
			return curr;
		}
		
	}
	
	
	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		if (!pathExists(startVertex, endVertex)) {
			// if path does not exist will return NullPointerException 
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		DijkstraAlgorithm d = new DijkstraAlgorithm(startVertex);
		while (d.hasNext()) {
			Integer next = d.next();
			if (next == endVertex) {
				// find it!
				Stack<Integer> temp = new Stack<Integer>();
				Integer curr = endVertex;
				while (curr != startVertex) {
					temp.push(curr);
					curr = d.steps.get(curr);
				}
				temp.push(curr);
				while (!temp.isEmpty()) {
					rtn.add(temp.pop()); 
				}
				return rtn; 
			}
		}
		return rtn; // did not find it

	}

}

