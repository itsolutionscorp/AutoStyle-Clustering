import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.*;

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
		myAdjLists[v1].add(new Edge(v1, v2, null));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		myAdjLists[v1].add(new Edge(v1, v2, null));
		myAdjLists[v2].add(new Edge(v2, v1, null));
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		if (myAdjLists[from].contains(new Edge(from, to, null))
				|| myAdjLists[to].contains(new Edge(to, from, null))) {
			return true;
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		// your code here
		return null;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int i = 0; i < myAdjLists.length; i++) {
			if (myAdjLists[i] == null) {
				continue;
			}

			// else if (myAdjLists[i].contains(Edge(i, vertex, null))) {
			// System.out.println("hi");
			// return count += 1;
			// }

			for (int k = 0; k < myAdjLists[i].size(); k++) {
				if (myAdjLists[i].get(k).myTo == vertex) {
					count += 1;
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

		private Stack<Integer> fringe = new Stack<>();
		private HashSet<Integer> visited = new HashSet<>();

		public DFSIterator(Integer start) {
			fringe.push(start);
			visited.add(start);
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {

			Integer toReturn = fringe.pop();

			for (int nums : nextHelper(toReturn)) { // given toReturn number,
													// stacks appropriately the
													// rest
				if (!visited.contains(nums)) {
					fringe.push(nums);
					visited.add(nums);
				}
			}
			return toReturn;
		}

		// at this position in myAdjLists, order into decending order and push
		// onto stack
		private int[] nextHelper(int thingy) {

			int[] toReturn = new int[myAdjLists[thingy].size()];
			for (int i = 0; i < myAdjLists[thingy].size(); i++) {
				toReturn[i] = myAdjLists[thingy].get(i).myTo;
			}
			Arrays.sort(toReturn);

			for (int i = 0; i < toReturn.length / 2; i++) {
				int temp = toReturn[i];
				toReturn[i] = toReturn[toReturn.length - i - 1];
				toReturn[toReturn.length - i - 1] = temp;
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
		if (startVertex == stopVertex) {
			return true;
		} else {
			for (int number : visitAll(startVertex)) {
				if (number == stopVertex) {
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
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		if (startVertex == stopVertex) {
			toReturn.add(startVertex);
			return toReturn;
		} else {
			Iterator<Integer> iter = new DFSIterator(startVertex);
			while (iter.hasNext()) {
				int toCheck = iter.next();
				toReturn.add(toCheck);
				if (toCheck == stopVertex) {
					return toReturn;
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

		private Stack<Integer> fringe = new Stack<Integer>();
		private int[] currentInDegree = new int[myAdjLists.length];

		// more instance variables go here

		public TopologicalIterator() {

			for (int i = 0; i < myAdjLists.length; i++) {
				currentInDegree[i] = inDegree(i);

			}

			for (int i = 0; i < myAdjLists.length; i++) {
				if (currentInDegree[i] == 0) {
					fringe.push(i);

				//	System.out.println("Prints what was pushed on:" + i);
				}
			}

		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			Integer toReturn = fringe.pop();
			for(int i = 0; i < myAdjLists[toReturn].size() ; i++) {
				//System.out.println(myAdjLists[toReturn].size());
				currentInDegree[myAdjLists[toReturn].get(i).myTo] -= 1;
				//System.out.println(val);
				//currentInDegree[myAdjLists[toReturn].get(i).myTo] = val-1;
				//System.out.println()
				
				}
				
			currentInDegree[toReturn] = -1;
			
			for (int i = 0; i < myAdjLists.length; i++) {
				if (currentInDegree[i] == 0) {
					fringe.push(i);
				}
			}	
		
//			for (int i = 0; i < myAdjLists.length; i++) {
//				
//					currentInDegree[i] = inDegree(i);
//				
//			}
			return toReturn;
		}

//		private void removeHelper(int toBeKilled) {
//			myAdjLists[toBeKilled] = null;
//			currentInDegree[toBeKilled] = -1;
//
//			for (int i = 0; i < myAdjLists.length; i++) {
//				if (myAdjLists[i] == null) {
//					continue;
//				} else {
//
//					for (int k = 0; k < myAdjLists[i].size(); k++) {
//						if (myAdjLists[i].get(k).myFrom == toBeKilled) {
//							myAdjLists[i].remove(k);
//						}
//					}
//				}
//			}
//		}

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

		public boolean equals(Object o) {

			if (this.myFrom == ((Edge) o).myFrom
					&& this.myTo == ((Edge) o).myTo) {
				return true;
			} else {
				return false;
			}
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
		Iterator<Integer> iter;
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
		// System.out.println();
		// System.out.println();
		//
		// System.out.println("Made graph 7");
		// Graph tester = new Graph(7);
		// tester.addUndirectedEdge(0, 2);
		// tester.addUndirectedEdge(0, 3);
		// tester.addUndirectedEdge(1, 4);
		// tester.addUndirectedEdge(1, 5);
		// tester.addUndirectedEdge(2, 3);
		// tester.addUndirectedEdge(2, 6);
		// tester.addUndirectedEdge(4, 5);
		// System.out.println(tester.pathExists(6, 3));
		//
		// System.out.println();
		// System.out.println();
		//
		// System.out.println("Made graph 5sss");
		// Graph tester1 = new Graph(5);
		// tester1.addEdge(0, 1);
		// tester1.addEdge(1, 2);
		// tester1.addEdge(2, 0);
		// tester1.addEdge(2, 3);
		// tester1.addEdge(4, 2);
		// System.out.println(tester1.pathExists(0, 2));

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
