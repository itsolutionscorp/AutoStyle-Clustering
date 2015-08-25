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
		Edge e = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(e);
		Edge e2 = new Edge(v2, v1, edgeInfo);
		myAdjLists[v2].add(e2);
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		// your code here
		for (Edge e : myAdjLists[from]) {
			if (e.myTo == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Edge> neighbors(int vertex) {
		// your code here
		ArrayList<Edge> neighbors = new ArrayList<Edge>();
		for (Edge e : myAdjLists[vertex]) {
			neighbors.add(e);
		}
		return neighbors;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
//		for (Edge e : neighbors(vertex)) {
////			if (isAdjacent(e.myFrom, vertex)) {
//				count++;
////			}
//		}
		// your code here
		for (int i = 0; i < myVertexCount; i++) {
			for (Edge e: myAdjLists[i]) {
				if (e.myTo == vertex) {
					if(vertex == 1) {
						//System.out.println(e + " " + i + " degree test");
					}
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
			int i = fringe.pop();
			visited.add(i);
			for (Edge e : myAdjLists[i]) {
				if (!visited.contains(e.myTo)) {
					fringe.push(e.myTo);
				}
			}
			return i;
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
		} else if (visitAll(startVertex).contains(stopVertex)) {
			return true;
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		// you supply the body of this method

		if (startVertex == stopVertex) {
			path.add(startVertex);
		} else if (pathExists(startVertex, stopVertex)) {
			ArrayList<Integer> result = new ArrayList<Integer>();
			Iterator<Integer> iter = new DFSIterator(startVertex);

			while (iter.hasNext()) {
				Integer a = iter.next();
				result.add(a);
				if (a == stopVertex) {
					break;
				}
			}
			int index = result.size() -1 ;
			path.add(result.get(index));
			for (int i = index - 1; i >= 0; i--) {
				if (isAdjacent(result.get(i), path.get(0))) {
					path.add(0, result.get(i));
				}
			}
		}
		return path;
	}
	
	public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    ShortestPathIterator iter = new ShortestPathIterator(startVertex);
		while (iter.hasNext()) {
			int next = iter.next();
			if (next == endVertex) {
				break;
			}
		}
		LinkedList<Edge> edges = iter.edgeList;
		Edge end = getEdge(endVertex, edges);
		
		Stack<Integer> reversed = new Stack<Integer>();
		while(end != null) {
			//System.out.println(end);
			
			reversed.add(end.myTo);
			end = getEdge(end.myFrom, edges);
		}
		result.add(startVertex);
		while(!reversed.isEmpty()) {
			result.add(reversed.pop());
		}
	    return result;
	}
	
	public static Edge getEdge(int endVertex, LinkedList<Edge> edges) {
		for(Edge edge : edges) {
			//System.out.println(edge.myFrom + " " + edge.myTo + " " + endVertex + " " + edge.myEdgeInfo);
			if(edge.myTo == endVertex) {
				return edge;
				
			}
		}
		return null;
	}

	private class ShortestPathIterator implements Iterator<Integer> {
		private int size = 0;
		private ArrayHeap<Edge> heap;
		private LinkedList<Edge> edgeList;
		private Edge first;
		
		public ShortestPathIterator(int startVertex) {
			heap = new ArrayHeap<Edge>();
			edgeList = new LinkedList<Edge>(); 
			
			for(int i = 0; i < myAdjLists.length; i++) {
				Edge edge = null;
				if(i == startVertex) {
					edge = new Edge(startVertex, startVertex, 0.0);
					first = edge;
				} else {
					edge = new Edge(-1, i, Double.POSITIVE_INFINITY);
					edgeList.add(edge);
				}
				heap.insert(edge, (Double) edge.myEdgeInfo);
				
				size++;
			}
			
		}
		
		
		public boolean hasNext() {
			return size != 0;
		}
		
		public Integer next() {
			ArrayHeap.Node next = heap.removeMin();
			size--;
			Edge current = (Edge) next.item();
			int index = current.myTo;
			LinkedList<Edge> edges = myAdjLists[index];
			for(Edge edge : edges) {
				int from = edge.myFrom;
				if(from == index) {
					int cost = (Integer) edge.myEdgeInfo;
					
					Edge heapEdge = null;
					for(Edge edgeIter : edgeList) {
						if(edgeIter.to().equals(edge.to())) {
							heapEdge = edgeIter;
							break;
						}
					}
					if(heapEdge == null) heapEdge = first;
					if(cost + (Double) current.myEdgeInfo 
							< (Double) heapEdge.myEdgeInfo) {
						heapEdge.myEdgeInfo = cost + (Double) current.myEdgeInfo;
						heapEdge.myFrom = index;
						heap.changePriority(heapEdge, (Double)heapEdge.myEdgeInfo);
					}
				}
				
			}
			
			return index;
		}


		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
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
//			System.out.println(inDegree(0));
			for (int i = 0; i < myVertexCount; i ++) {
				currentInDegree[i] = inDegree(i);
				if (currentInDegree[i] == 0) {
					//System.out.println(i);
					fringe.push(i);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int i = fringe.pop();
			for (Edge e: myAdjLists[i]) {
				
				currentInDegree[e.myTo] = currentInDegree[e.myTo] - 1;
				//System.out.println(i + " to " + e.myTo + " degree " + currentInDegree[e.myTo]);
				if (currentInDegree[e.myTo] == 0) {
					fringe.push(e.myTo);
				}
			}
			return i;
			// you supply the real body of this method
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
		ArrayList<Integer> result1;

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
		
		
		//System.out.println(g2.inDegree(1) + " degree test");
		System.out.println();
		System.out.println();
		System.out.println("Topological sort");
//		System.out.println();
		result = g2.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		
		Graph g3 = new Graph(5);
		g3.addEdge(0, 4, 100);

		g3.addEdge(4, 3, 10);
		g3.addEdge(3, 1, 10);
		g3.addEdge(4, 2, 20);
		g3.addEdge(2, 0, 30);
		g3.addEdge(1, 0, 40);
		
		System.out.println();
		System.out.println();
		System.out.println("Dijkstra's Method");
		result = g3.shortestPath(4, 0);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		
	}

}
