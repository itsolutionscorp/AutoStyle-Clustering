import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.*;

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
		return myAdjLists[from].contains(to);
	}

	// Returns a list of all the neighboring  vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		LinkedList<Edge> l = myAdjLists[vertex];
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (Edge e: l) {
			ret.add(e.to());
		}
		return ret;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (LinkedList i: myAdjLists) {
			if (i.contains(vertex)) {
				count ++;
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
		private Integer next;
		private boolean firstRun;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			fringe.push(start);
			visited = new HashSet<Integer>();
			int blank = fringe.pop();
			if (!visited.contains(blank)) {
				visited.add(blank);
				LinkedList<Edge> n = myAdjLists[blank];
				for (Edge x: n) {
					fringe.push(x.to());
				}
				next = blank;
			}
			if (next!=null){
			firstRun=true;}
		}

		public boolean hasNext() {
			return !(fringe.isEmpty())||(next!=null);
		}

		public Integer next() {
			if (fringe.isEmpty()){
				Integer toReturn=next;
				next = null;
				return toReturn;
			}
			int blank = fringe.pop();
			if (!visited.contains(blank)) {
				visited.add(blank);
				LinkedList<Edge> n = myAdjLists[blank];
				for (Edge x: n) {
					fringe.push(x.to());
				}
				Integer toReturn=next;
				next = blank;
				return toReturn;
			}
			if (!(fringe.isEmpty())){
				return next();
			}
			Integer toReturn=next;
			next=null;
			return toReturn;
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
		return pathExistsHelper(startVertex, stopVertex, new ArrayList<Integer>());
	}
	
	public boolean pathExistsHelper(int startVertex, int stopVertex, ArrayList<Integer> v) {
		ArrayList<Integer> a = visitAll(startVertex);
		if (a.contains(stopVertex)) {
			return true;
		}
		for (int i: a) {
			if (!v.contains(i)) {
				v.add(i);
				if (pathExistsHelper(i, stopVertex, v)) {
					return true;
				}
			}
			
		}
		return false;
	}


	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		return pathHelper(startVertex, stopVertex, new ArrayList<Integer>(), new ArrayList<Integer>()); 
	}

	public ArrayList<Integer> pathHelper(int startVertex, int stopVertex, ArrayList<Integer> a, ArrayList<Integer> v) {
		if (!pathExists(startVertex, stopVertex)) {
			return new ArrayList<Integer>();
		} else if (startVertex == stopVertex) {
			a.add(startVertex);
			return a;
		} else {
			a.add(startVertex);
			LinkedList<Edge> b = myAdjLists[startVertex];
			for (Edge x: b) {
				int next = x.to();
				if (!v.contains(next)) {
					v.add(next);
					ArrayList<Integer> path = pathHelper(next, stopVertex, a, v);
					if (!path.isEmpty()) {
						return path;
					}
				}
			}
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
		private int currentInDegree[];
		private ArrayList<Integer> currentZero;
		private HashSet<Integer> visited;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			visited=new HashSet<Integer>();
			currentInDegree=new int[myAdjLists.length];
			for(LinkedList<Edge> x:myAdjLists){
				for(Edge y:x){
					currentInDegree[y.to()]+=1;
				}
			}
			
			currentZero=new ArrayList<Integer>();
			for(int i=0; i<currentInDegree.length; i++){
				if(currentInDegree[i]==0){
					currentZero.add(i);
				}
			}
			for (int item:currentZero){
				if (!visited.contains(item)){
					fringe.push(item);
					visited.add(item);
				}
			}

		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int blank = fringe.pop();
				LinkedList<Edge> n = myAdjLists[blank];
				for (Edge x: n) {
					currentInDegree[x.to()]-=1;
				}
				currentZero= new ArrayList<Integer>();
				for(int i=0; i<currentInDegree.length; i++){
					if(currentInDegree[i]==0){
						currentZero.add(i);
					}
				}
				for (int item:currentZero){
					if (!visited.contains(item)){
						fringe.push(item);
						visited.add(item);
					}
				}
				return blank;
			
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
	}

}
