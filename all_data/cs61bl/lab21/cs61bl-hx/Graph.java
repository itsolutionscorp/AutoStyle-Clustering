import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Consumer;

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
		if (myAdjLists[v1].isEmpty()){
			myVertexCount++;
		}

		if (myAdjLists[v2].isEmpty()){
			myVertexCount++;
		}

		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		if (myAdjLists[v1].isEmpty()){
			myVertexCount++;
		}

		if (myAdjLists[v2].isEmpty()){
			myVertexCount++;
		}

		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo)); 
		myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {

		for (int i = 0; i< myAdjLists[from].size();i++){
			if (myAdjLists[from].get(i).to() == to){
				return true;
			}
		}    	
		return false;
	}

	// Returns a list of all the neighboring  vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		return myAdjLists[vertex];
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (int i = 0 ; i<  myAdjLists.length;i++){
			for (int j = 0; j< myAdjLists[i].size();j++){
				if (myAdjLists[i].get(j).to() == vertex){
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

		private Stack<Integer> fringe;
		private HashSet<Integer> visited;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			setStartVertex(start);
			fringe.add(start);
		}

		public boolean hasNext() {			
			return !fringe.isEmpty();
		}

		public Integer next() {
			int current = fringe.pop();
			visited.add(current);	

			for (int i = myAdjLists[current].size() -1; i >= 0;i--){
				int tmp = myAdjLists[current].get(i).to();
				if (!visited.contains(tmp) && !fringe.contains(tmp))
					fringe.add(myAdjLists[current].get(i).to());
			}
			return current;
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
		if (startVertex == stopVertex){
			return true;
		}
		ArrayList<Integer> maxPath = visitAll(startVertex);
		if(maxPath.contains(stopVertex)){
			return true;
		}
		return false;
	}


	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		//		return new ArrayList<Integer>();
		// you supply the body of this method
		if (startVertex == stopVertex){
			ArrayList<Integer> rtn = new ArrayList<Integer>();
			rtn.add(startVertex);
			return rtn;
		}		

		Stack<Integer> visited = new Stack<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		ArrayList<Integer> rtn = new ArrayList<Integer>();

		// if the vertex has the edge
		for (Object e: neighbors(startVertex)){
			Edge temp = (Edge) e;				
			if (temp.to() == stopVertex){
				ArrayList<Integer> rtn1 = new ArrayList<Integer>();
				rtn1.add(startVertex);
				rtn1.add(stopVertex);

				return rtn1;
			}
		}		

		while (iter.hasNext()) {
			int temp = iter.next();
			visited.add(temp);
			if (temp == stopVertex){
				break;
			}
		}
		if (!visited.contains(stopVertex)){
			return new ArrayList<Integer>();
		}

		int bottom = visited.pop();
		int top = -1;// = visited.pop();
		while (!visited.isEmpty()){
			top = visited.pop();
			if(pathExists(top, bottom)){
				rtn.add(bottom);
				//				rtn.add(top);
				bottom = top;
			}

		}

		ArrayList<Integer> reverse = new ArrayList<Integer>();
		reverse.add(top);
		for(int i = rtn.size()-1; i >=0; i--){
			reverse.add(rtn.get(i));
		}
		return reverse;
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
		private ArrayList<Integer> visited;

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			visited = new ArrayList<Integer>();
			// more statements go here
			currentInDegree = new int[myAdjLists.length];
			for(int i = 0; i<myAdjLists.length;i++){
				currentInDegree[i] = inDegree(i);
			}
			for (int i = 0; i < currentInDegree.length; i++){
				if (currentInDegree[i] == 0){
					fringe.add(i);
					visited.add(i);
				}
			}
		}	

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int current = fringe.pop();	
			for (Object e: neighbors(current)){
				Edge temp = (Edge) e;				
				currentInDegree[temp.to()] -= 1;

			}			
			for (int i = 0; i < currentInDegree.length; i++){
				if (currentInDegree[i] == 0 && !visited.contains(i)){
					fringe.add(i);
					visited.add(i);
				}
			}	
			return current;
		}

		public void remove() {
			throw new UnsupportedOperationException(
					"vertex removal not implemented");
		}

	}

	public ArrayList<Integer> shortestPath (int startVertex, int endVertex){

		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<vertexNode> iter = new Dijkstra(startVertex);
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		vertexNode temp = null;
		while (iter.hasNext()) {
			 temp = iter.next();
			if (temp.me == endVertex)
				break;			
		}
		while(temp!=null){
			result.add(temp.me);
			temp = temp.myPredecessor;
		}
		for(int i = result.size()-1; i>=0; i--){
			rtn.add(result.get(i));
		}
		return rtn;

	}

	protected class edgePriority implements Comparator<vertexNode>
	{

		public int compare(vertexNode o1, vertexNode o2)
		{
			return Double.compare(o1.dist, o2.dist);
		}
	}

	private class Dijkstra implements Iterator<vertexNode>{

		private PriorityQueue<vertexNode> fringe;
		private vertexNode head;
//		private HashMap<Integer, vertexNode> visited;
		private ArrayList<vertexNode> myNodes;
		public Dijkstra(int startVertex){
			fringe = new PriorityQueue<vertexNode>(myAdjLists.length, new edgePriority());
//			visited = new HashMap<Integer, vertexNode>();
			myNodes = new ArrayList<vertexNode>();
			head = new vertexNode(startVertex, null, 0);
			fringe.add(head);
			myNodes.add(head);
			for (int i = 0; i< myAdjLists.length;i++){
				if (i!=startVertex){
					vertexNode newNode  = new vertexNode(i, null,  Double.POSITIVE_INFINITY);
					fringe.add(newNode);
					myNodes.add(newNode);
				}
			}

		}


		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !fringe.isEmpty();
		}

		@Override
		public vertexNode next() {
			vertexNode current = fringe.poll();	
//			visited.put(current.me, current);
			for(int i = 0; i<myAdjLists[current.me].size(); i++){
				vertexNode child = myNodes.get(myAdjLists[current.me].get(i).myTo);
				double edgeValue = ((Integer)myAdjLists[current.me].get(i).myEdgeInfo).doubleValue();
				if(fringe.contains(child)){
					if(current.dist+edgeValue<child.dist){
						vertexNode newNode = new vertexNode(child.me, current, current.dist+edgeValue);
						fringe.remove(child);
						myNodes.remove(child);
						fringe.add(newNode);
						myNodes.add(newNode.me, newNode);
					}
				}
			}
			return current;
		}
	}
	public class vertexNode {
		public int me;
		public vertexNode myPredecessor;
		public double dist;

		public vertexNode(int v, vertexNode predecessor, double d){
			me = v;
			myPredecessor = predecessor;
			dist = d;
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
		
		
		Graph g3 = new Graph(5);
		g3.addEdge(0, 1, 10);
		g3.addEdge(0, 3, 30);
		g3.addEdge(0, 4, 100);
		g3.addEdge(1, 2, 50);
		g3.addEdge(2, 4,10);
		g3.addEdge(3, 2, 20);
		g3.addEdge(3, 4,60);
		result = g3.shortestPath(0,4);
		iter = result.iterator();
		System.out.println("Bullshit sort");
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
	}

}
