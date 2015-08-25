import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Collections;

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
    	if (v1<myAdjLists.length && v2<myAdjLists.length)		
    		myAdjLists[v1].add(new Edge(v1,v2, edgeInfo));
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
    	if (v1<myAdjLists.length && v2<myAdjLists.length){
    		myAdjLists[v1].add(new Edge(v1,v2, edgeInfo));
    		myAdjLists[v2].add(new Edge(v2,v1, edgeInfo));    		
    	}
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
    	if (from<myAdjLists.length && to<myAdjLists.length){
    		Iterator<Edge> edgeIter = myAdjLists[from].iterator();
    		while (edgeIter.hasNext()){
    			Edge edge = edgeIter.next();
    			if (edge.to() == to){
    				return true;
    			}
    		}    		
    	}
    	return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
    	List neighbors = new ArrayList();
    	for (Edge edge:myAdjLists[vertex]){
    		neighbors.add(edge.to());
    	}
    	return neighbors;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for(int i=0; i<myAdjLists.length; i++){
        	if (i!=vertex){
        		for(Edge edge:myAdjLists[i]){
        			if (edge.to()==vertex){
        				count++;
        			}
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
        	if (start<myAdjLists.length){
        		fringe.push(start);        		
        	}
        }

        public boolean hasNext() { 
        	cleanFringe();
        	return !fringe.empty();
        }

        private void cleanFringe(){
        	while (!fringe.empty() && visited.contains(fringe.peek())){
        		fringe.pop();
        	}
        }
        
        public Integer next() {
        	Integer rtn = fringe.pop();
        	if (!visited.contains(rtn)){
        		visited.add(rtn);
        		for (Edge edge: myAdjLists[rtn]){
        			fringe.push(edge.to());
        		}
        		return rtn;
        	} else {
        		if (!hasNext()){
        			return null;
        		}
        		return next();         			
        	}
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
    	return visitAll(startVertex).contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> eventualPath = pather(startVertex, stopVertex);
    	if (eventualPath.isEmpty()){
    		return eventualPath;
    	} else {
    		return getProperPath(eventualPath, stopVertex, startVertex);
    	}
    }
    
    private ArrayList<Integer> getProperPath(ArrayList<Integer> eventual, int stopVertex, int startVertex){
    	ArrayList<Integer> properPath = new ArrayList<Integer>();
    	properPath = properPathHelper(eventual, properPath, stopVertex, startVertex);
    	Collections.reverse(properPath);
    	return properPath;
    }
    
    private ArrayList<Integer> properPathHelper(ArrayList<Integer> event, ArrayList<Integer> path, int stopVert, int startVert){
    	path.add(stopVert);
    	if (stopVert == startVert){
    		return path;
    	} else {
    		int preVert = 0;
    		mainLoop:
    		for (int potVert: event){
    			for (Edge edge :myAdjLists[potVert]){
    				if (edge.to()==stopVert){
    					preVert = potVert;
    					break mainLoop;
    				}
    			}
    		}
    		return properPathHelper(event, path, preVert, startVert);
    	}
    }
    
    public ArrayList<Integer> pather(int startVertex, int stopVertex){
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            int next = iter.next();
        	result.add(next);
            if (next==stopVertex){
            	return result;
            }
        }
        return new ArrayList<Integer>();
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
        private int [] currentInDegree;
        private HashSet<Integer> visited;
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new int[myVertexCount];
            visited = new HashSet<Integer>();
            fillCurrentInDegree();
            fillFringe();
        }
        
        private void fillCurrentInDegree(){
        	for (int i=0; i < myVertexCount; i++){
        		currentInDegree[i] = inDegree(i);        		
        	}
        }
        
        private void fillFringe(){
        	for (int i=0; i<currentInDegree.length; i++){
        		if (currentInDegree[i]==0 && !visited.contains(i)){
        			visited.add(i);
        			fringe.push(i);
        		}
        	}
        }
        
        public boolean hasNext() {
            return !fringe.isEmpty();
        }
        
        public Integer next() {
        	Integer rtn = fringe.pop();
        	visited.add(rtn);
        	List<Integer> neighbors = neighbors(rtn);
        	for (int i=0; i<neighbors.size(); i++){
        		currentInDegree[neighbors.get(i)] -= 1;
        	}
        	fillFringe();
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
    }

}
