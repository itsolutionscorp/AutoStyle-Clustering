import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
    	for (Edge i : myAdjLists[from]) {
    		if (i.to() == to) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	ArrayList<Integer> result = new ArrayList<Integer>();
        for (Edge e : myAdjLists[vertex]) {
	            result.add(e.to());
	    }
        return result;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < myAdjLists.length; i++) {
        	if (isAdjacent(i, vertex)) count ++;
        }
        return count;
    }

    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }

    // A class that iterates through the vertices of this graph, starting with a given vertex.
    // Does not necessarily iterate through all vertices in the graph: if the iteration starts
    // at a vertex v, and there is no path from v to a .vertex w, then the iteration will not
    // include w
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        public DFSIterator(Integer start) {
            //your code here
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
        	fringe.add(start);
        }

        public boolean hasNext() {
            //your code here
        	if (fringe.isEmpty()) return false;
        	int index = 0;
        	int check = fringe.elementAt(fringe.size() - 1 - index);
        	while (visited.contains(check)) {
        		index++;
            	if (fringe.size() - 1 <= index) return false;
        		check = fringe.elementAt(fringe.size() - 1 - index);
        	}
        	return true;
        }

        public Integer next() {
            //your code here
        	int check = fringe.pop();
        	while (visited.contains(check)) {
        		check = fringe.pop();
        	}
        	visited.add(check);
        	for (Edge e : myAdjLists[check]) {
        		fringe.add(e.to());
        	}
        	return check;
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
        ArrayList<Integer> Path = visitAll(startVertex);
        for (Integer e : Path){
            if (e.equals(stopVertex)){
                return true;
            }
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        Stack<Integer> resultS = new Stack<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<Integer> temp = new Stack<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
      if(!pathExists(startVertex,stopVertex)){
        return result;
      }
      while (iter.hasNext()) {
        Integer next = iter.next();
        if(next == stopVertex){
        	temp.push(next);
            break;
        }
        temp.push(next);
      }
      Integer latestV = temp.pop();
      resultS.push(latestV);
      int current = latestV;
      while(!temp.empty()){
        current = temp.pop();
        if(current == startVertex){
          latestV = current;
          resultS.push(current);
          break;
        }
        for (Edge e : myAdjLists[current]) {
	        if(e.to().equals(latestV)){
	            latestV = current;
	            resultS.push(current);
	        }
        }
    }
        
        while(!resultS.empty()){
          result.add(resultS.pop());
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
        HashSet<Integer> result;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new int[myVertexCount];
            result = new HashSet<Integer>();
            InDegreeInit();
        }

        public boolean hasNext() {
        	if (fringe.isEmpty()) return false;
        	int check = fringe.peek();
        	while (result.contains(check)) {
        		fringe.pop();
            	if (fringe.size() == 0) return false;
        		check = fringe.peek();
        	}
        	return true;
        }

        public Integer next() {
        	int check = fringe.pop();
        	result.add(check);
        	for (Edge e : myAdjLists[check]) {
        		currentInDegree[e.to()] = currentInDegree[e.to()]-1;
        	}
        	for (int i = 0; i < myVertexCount; i++) {
        		if (currentInDegree[i]==0){
        			fringe.push(i);
           		}
        	}
        	System.out.println(fringe);
        	return check;

            // you supply the real body of this method,
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }
        
        public void InDegreeInit(){
        	for (int i = 0; i < myVertexCount; i++) {
        		if (inDegree(i)==0){
        			fringe.push(i);
        		}
            	currentInDegree[i] = inDegree(i);      	
            }
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
        System.out.println("Path Exists Test");
        Graph G = new Graph(7);
        G.addUndirectedEdge(0,2);
        G.addUndirectedEdge(1,5);
        G.addUndirectedEdge(2,3);
        G.addUndirectedEdge(2,6);
        G.addUndirectedEdge(6,4);
        System.out.println("visitAll print\n" + G.visitAll(0));
        System.out.println(G.pathExists(0,2));
        System.out.println(G.pathExists(0,6));
        System.out.println(G.pathExists(0,1));
        System.out.println(G.path(0,6));

           
        Graph G2 = new Graph(6);
        G2.addEdge(0,1);
        G2.addEdge(1,2);
        G2.addEdge(2,0);
        G2.addEdge(2,3);
        G2.addEdge(4,2);
        System.out.println(G2.pathExists(0, 1));
        System.out.println(G2.pathExists(0,3));
        
        Graph G3 = new Graph(10);
        G3.addUndirectedEdge(0,2);
        G3.addUndirectedEdge(2,3);
        G3.addUndirectedEdge(2,5);
        G3.addUndirectedEdge(2,6);
        G3.addUndirectedEdge(5,8);
        G3.addUndirectedEdge(8,2);
        G3.addUndirectedEdge(8,3);
        G3.addUndirectedEdge(8,6);
        G3.addUndirectedEdge(6,9);
        G3.addUndirectedEdge(6,3);
        G3.addUndirectedEdge(6,7);
        G3.addUndirectedEdge(7,4);
        G3.addUndirectedEdge(7,3);
        G3.addUndirectedEdge(4,6);
        G3.addUndirectedEdge(4,1);
        G3.addUndirectedEdge(1,3);
        System.out.println(G3.path(0,2));
        System.out.println(G3.path(0,9));
        System.out.println(G3.path(9,1));
        System.out.println(G3.path(3,3));

    }

}
