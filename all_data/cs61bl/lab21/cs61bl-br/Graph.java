import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Graph implements Iterable<Integer> {

	private LinkedList<Edge>[] myAdjLists;
	private int myVertexCount;
	private int myStartVertex;
	HashMap<Integer, Double> costs = new HashMap<Integer, Double>();

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
		if (v1 >= myAdjLists.length || v1 < 0) {
			return;
		}
		if (v2 >= myAdjLists.length || v2 < 0) {
			return;
		}
		Edge e = new Edge(v1, v2, edgeInfo);
		myAdjLists[v1].add(e);
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		// your code here
		if (v1 >= myAdjLists.length || v1 < 0) {
			return;
		}
		if (v2 >= myAdjLists.length || v2 < 0) {
			return;
		}
		Edge e1 = new Edge(v1, v2, edgeInfo);
		Edge e2 = new Edge(v2, v1, edgeInfo);
		myAdjLists[v1].add(e1);
		myAdjLists[v2].add(e2);
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		// your code here
		if (from >= myAdjLists.length || from < 0) {
			return false;
		}
		if (to >= myAdjLists.length || to < 0) {
			return false;
		}
		for (Edge e : myAdjLists[from]) {
			if (e.myTo == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		// your code here
		List<Integer> l = new ArrayList<Integer>();
		for (Edge e : myAdjLists[vertex]) {
			l.add(e.myTo);
		}
		return l;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		// your code here
		if (vertex >= myAdjLists.length || vertex < 0) {
			return count;
		}
		for (int i = 0; i < myAdjLists.length; i++) {
			for (Edge e : myAdjLists[i]) {
				if (e.myTo == vertex) {
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
			int rtn = fringe.pop();
			visited.add(rtn);
			for (Edge e : myAdjLists[rtn]) {
				if (!visited.contains(e.myTo)) {
					fringe.push(e.myTo);
				}
			}
			return rtn;
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
		}
		ArrayList<Integer> a = this.visitAll(startVertex);
		if (a.contains(stopVertex)) {
			return true;
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		int temp = Integer.MAX_VALUE;
		if (startVertex == stopVertex) {
			a.add(startVertex);
			return a;
		}
		while (iter.hasNext() && temp != stopVertex) {
			temp = iter.next();
			a.add(temp);
		}
		for (int i = a.size() - 2; i >= 0; i--) {
			if (!pathExists(a.get(i), a.get(i + 1))) {
				a.remove(i);
			}
		}
		return a;
		// you supply the body of this method
	}

	// public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
	// HashMap<Integer, Double> d = new HashMap<Integer, Double>();
	// HashMap<Integer, Integer> p = new HashMap<Integer, Integer>();
	// ArrayList<Integer> al = new ArrayList<Integer>();
	// d.put(startVertex , 0.0);
	// p.put(startVertex, startVertex);
	// for (int i = 0; i < myAdjLists.length; i++){
	// if (i != startVertex){
	// d.put(i, Double.POSITIVE_INFINITY);
	// }
	// }
	// int v = startVertex;
	// List<Integer> w;
	// // d.remove(0);
	// while (!d.isEmpty()){
	// Integer temp = Integer.MAX_VALUE;
	// for (int d2 : d.keySet()){
	// if (d.get(d2) < temp)
	// temp = d2;
	// }
	// v = temp;
	// Double temp2 = d.get(v);
	// d.remove(v);
	// w = neighbors(v);
	// int i = 0;
	// for (Integer j : w){
	// if (d.containsKey(j)){
	// if(d.get(j) == Double.POSITIVE_INFINITY){
	// d.put(j, (Double)(myAdjLists[v].get(i).myEdgeInfo));
	// }else{
	// // System.out.println(v);
	// d.put(j, (Double)(myAdjLists[v].get(i).myEdgeInfo) + temp2);
	// }
	// p.put(j, v);
	// }
	// i++;
	// }
	// // System.out.println(v);
	// if (v == endVertex){
	// break;
	// }
	// }
	// while (p.get(v) != startVertex){
	// al.add(v);
	// v = p.get(p.get(v));
	// }
	// Collections.reverse(al);
	// return al;
	// }

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		PriorityQueue<Integer> fringe = new PriorityQueue<Integer>(myAdjLists.length, new VerticeComparator());
		HashMap<Integer, Integer> predecessors = new HashMap<Integer, Integer>();
		for (int i = 0; i < myAdjLists.length; i++){
			if (i != startVertex)
				costs.put(i, Double.POSITIVE_INFINITY);
			else costs.put(i, 0.);
		}
		for (int i = 0; i < myAdjLists.length; i++){
			fringe.add(i);
		}
		while (!fringe.isEmpty()){
			Integer i = fringe.poll();
			if (i == endVertex)
				break;
			for (Integer neighbor : neighbors(i)){
				for (int j = 0; j < myAdjLists[i].size(); j++){
					if (myAdjLists[i].get(j).myTo == neighbor){
						if (costs.get(i) + (Integer)(myAdjLists[i].get(j).myEdgeInfo) < costs.get(neighbor)){
							costs.put(neighbor, costs.get(i) + (Integer)(myAdjLists[i].get(j).myEdgeInfo));
							predecessors.put(neighbor, i);
							fringe.remove(neighbor);
							fringe.add(neighbor);
						}
						break;
					}
				}
			}
		}
		int temp = endVertex;
		while (temp != startVertex){
			a.add(temp);
			temp = predecessors.get(temp);
		}
		a.add(startVertex);
		Collections.reverse(a);
		return a;
	}
	
	class VerticeComparator implements Comparator<Integer>{

		public int compare(Integer o1, Integer o2) {
			if (costs.get(o1) < costs.get(o2))
				return -1;
			if (costs.get(o1) > costs.get(o2))
				return 1;
			return 0;
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
			// more statements go here
			currentInDegree = new int[myAdjLists.length];
			for (int i = 0; i < myAdjLists.length; i++) {
				currentInDegree[i] = inDegree(i);
			}
			for (int i = 0; i < currentInDegree.length; i++) {
				if (currentInDegree[i] == 0) {
					fringe.push(i);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			// return new Integer(0);
			// you supply the real body of this method
			int rtn = fringe.pop();
			for (int i = 0; i < currentInDegree.length; i++) {
				if (neighbors(rtn).contains(i)) {
					currentInDegree[i] -= 1;
					if (currentInDegree[i] == 0) {
						fringe.push(i);
					}
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
		System.out.println(g1.inDegree(3));
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

		Graph g3 = new Graph(7);
		g3.addUndirectedEdge(0, 2);
		g3.addUndirectedEdge(0, 3);
		g3.addUndirectedEdge(1, 4);
		g3.addUndirectedEdge(1, 5);
		g3.addUndirectedEdge(2, 3);
		g3.addUndirectedEdge(2, 6);
		g3.addUndirectedEdge(4, 5);
		System.out.println(g3.pathExists(5, 1));

		Graph g4 = new Graph(4);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		g4.addEdge(2, 3);
		g4.addEdge(4, 2);
		System.out.println(g4.pathExists(1, 0));
		System.out.println(g4.pathExists(2, 4));

		Graph g5 = new Graph(10);
		g5.addEdge(0, 4);
		g5.addEdge(1, 4);
		g5.addEdge(1, 5);
		g5.addEdge(2, 5);
		g5.addEdge(2, 6);
		g5.addEdge(3, 6);
		g5.addEdge(4, 7);
		g5.addEdge(5, 7);
		g5.addEdge(5, 8);
		g5.addEdge(6, 8);
		g5.addEdge(7, 9);
		g5.addEdge(8, 9);
		System.out.println();
		System.out.println();
		g5.setStartVertex(0);
		System.out.println("Topological sort");
		result = g5.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}

		Graph g6 = new Graph(10);
		g6.addUndirectedEdge(0, 1, 1);
		g6.addUndirectedEdge(0, 2, 1);
		g6.addUndirectedEdge(1, 3, 2);
		g6.addUndirectedEdge(2, 4, 2);
		g6.addUndirectedEdge(4, 5, 3);
		g6.addUndirectedEdge(5, 3, 3);
		g6.addUndirectedEdge(0, 5, 10);
		g6.addUndirectedEdge(1, 6, 13);
		g6.addUndirectedEdge(5, 6, 7);
		g6.addUndirectedEdge(4, 6, 11);
		System.out.println();
		System.out.println();
		System.out.println("Dijkstra's thing");
		ArrayList<Integer> r = g6.shortestPath(1, 6);
		for (Integer i : r) {
			System.out.println(i);
		}
	}

}
