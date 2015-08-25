import java.util.ArrayList;
import java.util.Collections;
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
		//your code here
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		//your code here
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
		myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		//your code here
		LinkedList<Edge> myList = myAdjLists[from];
		for (int i = 0; i < myList.size(); i ++) {
			if (myList.get(i).to() == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring  vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		// your code here
		List<Integer> returnlst = new ArrayList<Integer>();
		LinkedList<Edge> edges = myAdjLists[vertex];
		for (int i = 0; i < edges.size(); i ++) {
			returnlst.add(edges.get(i).to());
		}
		return returnlst;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		//your code here
		for (int i = 0; i < myAdjLists.length; i ++) {
			if (isAdjacent(i, vertex)) {
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

		public DFSIterator(Integer start) {
			//your code here
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			fringe.push(start);
		}

		public boolean hasNext() {
			//your code here
			return !fringe.isEmpty();
		}

		public Integer next() {
			//your code here
			Integer next = fringe.pop();
			visited.add(next);
			for (int i = 0; i < myAdjLists[next].size(); i ++) {
				Integer o = myAdjLists[next].get(i).to();
				if (!visited.contains(o)) {
					fringe.push(o);
					visited.add(o);
				}
			}
			return next;
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
		// your code here
		return visitAll(startVertex).contains(stopVertex);
	}


	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> iterresult =  new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		if (!pathExists(startVertex, stopVertex)) {
			return new ArrayList<Integer>();
		}
		while (iter.hasNext()) {
			int temp = iter.next();
			iterresult.add(temp);
			if (stopVertex == temp) {
				break;
			}
		}
//		if (iterresult.get(iterresult.size() - 1) != stopVertex) {
//			return new ArrayList<Integer>();
//		}
		return traceBack(iterresult, startVertex, stopVertex);

	}

	//helper that returns a list of the path
	public ArrayList<Integer> traceBack(ArrayList<Integer> lst, int startVertex, int stopVertex) {
		int goBackwards = stopVertex;
		ArrayList<Integer> iterresult =  lst;
		ArrayList<Integer> result =  new ArrayList<Integer>();
		ArrayList<Integer> seen =  new ArrayList<Integer>();
		seen.add(stopVertex);

		if (isAdjacent(startVertex, stopVertex)) {
			result.add(startVertex);
			result.add(stopVertex);
			return result;
		}
		
		else {
			result.add(stopVertex);
			while (goBackwards != startVertex) {
				int newVert = getPrev(goBackwards, iterresult, seen);
				result.add(0, newVert); //need to add to beginning of the list
				seen.add(newVert);
				goBackwards = newVert;
			}
			return result;
		}
	}
	
	//helper for traceback (used by path) that returns the previous node
	public int getPrev (int start, ArrayList<Integer> lst, ArrayList<Integer> seen) {
		List  neighbors =  neighbors(start);
		for (int i = 0; i < neighbors.size(); i ++) {
			if (lst.contains(neighbors.get(i)) && !seen.contains(neighbors.get(i))) {
				return (int) neighbors.get(i);
			}
		}
		return 0;
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
		Integer[] currentInDegree = new Integer[myVertexCount];

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			// more statements go here
			for (int i = 0; i < myVertexCount; i++) {
				currentInDegree[i] = inDegree(i);
			}
			for (int i = 0; i < currentInDegree.length; i++) {
				if (currentInDegree[i] == 0) {
					fringe.push(i);
					currentInDegree[i] -= 1;
				}
			}
			
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			// you supply the real body of this method
			Integer next = fringe.pop();
			for (int i = 0; i < currentInDegree.length; i++) {
				if (isAdjacent(next, i)) {
					currentInDegree[i] -= 1;
				}
			}
			for (int i = 0; i < currentInDegree.length; i++) {
				if (currentInDegree[i] == 0) {
					fringe.push(i);
					currentInDegree[i] -= 1;
				}
			}
			return next;
			
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
		//        ArrayList<Integer> result;
		//
		//        Graph g1 = new Graph(5);
		//        g1.addEdge(0, 1);
		//        g1.addEdge(0, 2);
		//        g1.addEdge(0, 4);
		//        g1.addEdge(1, 2);
		//        g1.addEdge(2, 0);
		//        g1.addEdge(2, 3);
		//        g1.addEdge(4, 3);
		//        System.out.println("Traversal starting at 0");
		//        result = g1.visitAll(0);
		//        Iterator<Integer> iter;
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Traversal starting at 2");
		//        result = g1.visitAll(2);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Traversal starting at 3");
		//        result = g1.visitAll(3);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Traversal starting at 4");
		//        result = g1.visitAll(4);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Path from 0 to 3");
		//        result = g1.path(0, 3);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Path from 0 to 4");
		//        result = g1.path(0, 4);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Path from 1 to 3");
		//        result = g1.path(1, 3);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Path from 1 to 4");
		//        result = g1.path(1, 4);
		//        iter = result.iterator();
		//        while (iter.hasNext()) {
		//            System.out.println(iter.next() + " ");
		//        }
		//        System.out.println();
		//        System.out.println();
		//        System.out.println("Path from 4 to 0");
		//        result = g1.path(4, 0);
		//        if (result.size() != 0) {
		//            System.out.println("*** should be no path!");
		//        }
		//
//		        Graph g2 = new Graph(5);
//		        g2.addEdge(0, 1);
//		        g2.addEdge(0, 2);
//		        g2.addEdge(0, 4);
//		        g2.addEdge(1, 2);
//		        g2.addEdge(2, 3);
//		        g2.addEdge(4, 3);
//		        System.out.println();
//		        System.out.println();
//		        System.out.println("Topological sort");
//		        ArrayList<Integer> result = g2.topologicalSort();
//		        Iterator iter = result.iterator();
//		        while (iter.hasNext()) {
//		            System.out.println(iter.next() + " ");
//		        }
		Graph g5 = new Graph(8);
		    	g5.addUndirectedEdge(0,2); 
		    	g5.addUndirectedEdge(0,3); 
		    	g5.addUndirectedEdge(1,4); 
		    	g5.addUndirectedEdge(1,5); 
		    	g5.addUndirectedEdge(2,3); 
		    	g5.addUndirectedEdge(2,6); 
		    	g5.addUndirectedEdge(4,5); 
		
		g5.addUndirectedEdge(6, 7);
		g5.addUndirectedEdge(3, 6);
		g5.addUndirectedEdge(0, 3);
		g5.addUndirectedEdge(2, 0);
		g5.addUndirectedEdge(2, 4);
		//    	System.out.println(g5.isAdjacent(0, 5));
		//    	System.out.println(g5.isAdjacent(0, 6));
		//    	System.out.println(g5.isAdjacent(4, 5));

		//
		//    	System.out.println(g5.inDegree(0));
		//    	System.out.println(g5.neighbors(5));
		//    	

		//    	List<Integer> result = g5.visitAll(0);
		//    	System.out.println(result);

		List<Integer> result = g5.path(0, 7);
		System.out.println(result);
		System.out.println("0 3 6 7");
		System.out.println();
//
//		result = g5.path(0, 3);
//		System.out.println(result);
//		System.out.println("0 3");
//		System.out.println();
//
//		result = g5.path(0, 6);
//		System.out.println(result);
//		System.out.println("0 3 6");
//
//
//		result = g5.path(0, 7);
//		System.out.println(result);
//		Graph g7 = new Graph(8);
//		g7.addEdge(0, 2);
//		g7.addEdge(0, 3);
//		g7.addEdge(1, 3);
//		g7.addEdge(1, 4);
//		g7.addEdge(2, 5);
//		g7.addEdge(3, 5);
//		g7.addEdge(4, 6);
//		g7.addEdge(5, 7);
//		g7.addEdge(6, 7);
//		ArrayList<Integer> lst = g7.topologicalSort();
//		System.out.println(lst);
		
	}

}
