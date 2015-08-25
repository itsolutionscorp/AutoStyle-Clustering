import java.util.ArrayList;
import java.util.HashMap;
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
        for (Edge edge : myAdjLists[from]) {
        	if (edge.to() == to) {
        		return true;
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
    	ArrayList<Integer> toReturn = new ArrayList<Integer>();
        for (Edge edge: myAdjLists[vertex]) {
        	toReturn.add(edge.to());
        }
        return toReturn;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (int i = 0; i < myAdjLists.length; i++) {
        	for (Edge edge : myAdjLists[i]) {
        		if (edge.to() == vertex) {
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
            fringe.push(start);
        }

        public boolean hasNext() {
            return fringe.isEmpty() == false;
        }

        public Integer next() {
            int toReturn = fringe.pop();
            visited.add(toReturn);
            for (Integer vertex : (List<Integer>) neighbors(toReturn)) {
            	if (visited.contains(vertex) == false) {
            		fringe.push(vertex);
            	}
            }
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

    
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        HashMap<Integer, Integer> currToPre = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> priorities = new HashMap<Integer, Integer>(); 
        HashSet<Integer> visited = new HashSet<Integer>();
        
        fringe.insert((Integer)startVertex, 0.0);
        priorities.put(startVertex, 0);
        for (int i = 0; i < myVertexCount; i++) {
        	if (i == startVertex) continue;
        	fringe.insert(i, Double.POSITIVE_INFINITY);
        	if (isAdjacent(0, i)) priorities.put((Integer) i, (Integer) getEdge(0, i).info());
        	else {
        		priorities.put(i, Integer.MAX_VALUE);
        	}
        }
        
        int topQueue = fringe.peek().item();
        while (endVertex != topQueue) {
        	visited.add(topQueue);
        	fringe.removeMin();  
        	for (Integer i: (List<Integer>) neighbors(topQueue)) {
        		if (visited.contains(i)) {
        			continue;
        		}
        		Integer iPriority = (Integer) getEdge(topQueue, i).info();
        		if (currToPre.containsKey(i)){
        			iPriority += (Integer) priorities.get(currToPre.get(topQueue));
        		}
        		double holder = fringe.prioritySpec(i);
        		if (((Integer) iPriority) < holder) {
        			fringe.changePriority(i, iPriority);
        			priorities.put(i, iPriority);
        			currToPre.put(i, topQueue);
        		}
        	}   			
        	topQueue = fringe.peek().item();	
        }
        
        path.add(0, endVertex);
        while (path.get(0) != startVertex) {
        	path.add(0, currToPre.get(path.get(0)));
        }
        return path;
    }
    
    private Edge getEdge (int vertex1, int vertex2) {
    	Edge current = myAdjLists[vertex1].get(0);
    	for (int i = 0; i < myAdjLists[vertex1].size(); i ++) {
    		current = myAdjLists[vertex1].get(i);
    		if (current.to() == vertex2) {
    			return current;
    		}
    		
    	}
    	return null;
    }
    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
    	if (startVertex == stopVertex) return true;
        List<Integer> path = visitAll(startVertex);
        return path.contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	ArrayList<Integer> visited = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        int current = startVertex;
        while (iter.hasNext() && current != stopVertex) {
        	current = iter.next();
        	visited.add(current);
        }
        
        if (iter.hasNext() == false && current != stopVertex) {
        	return new ArrayList<Integer>();
        }
        
        result.add(current);
        for (int i = visited.size() - 1; i >= 0; i--) {
        	if (isAdjacent(visited.get(i), current)) {
        		result.add(0, visited.get(i));
        		current = visited.get(i);
        	}
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
            for (int i = 0; i < myVertexCount; i++) {
            	int degree = inDegree(i);
            	if (degree == 0) {
            		fringe.push(i);
            		visited.add(i);
            	}
            	currentInDegree[i] = degree;
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            int toReturn = fringe.pop();
            for (int vertex : (ArrayList<Integer>) neighbors(toReturn)) {
            	currentInDegree[vertex] -= 1;
            }
            for (int i = 0; i < currentInDegree.length; i++) {
            	if (visited.contains(i) == false && currentInDegree[i] == 0) {
            		fringe.push(i);
            		visited.add(i);
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
//        Graph g2 = new Graph(5);
//        g2.addEdge(0, 1);
//        g2.addEdge(0, 2);
//        g2.addEdge(0, 4);
//        g2.addEdge(1, 2);
//        g2.addEdge(2, 3);
//        g2.addEdge(4, 3);
//        System.out.println();
//        System.out.println();
//        System.out.println("Topological sort");
//        result = g2.topologicalSort();
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//    	Graph g3 = new Graph(5);
//    	g3.addEdge(0, 1, 10);
//    	g3.addEdge(0, 4, 100);
//    	g3.addEdge(0, 3, 30);
//    	g3.addEdge(1, 2, 50);
//    	g3.addEdge(2, 4, 10);
//    	g3.addEdge(3, 2, 20);
//    	g3.addEdge(3, 4, 60);
//    	System.out.println(g3.shortestPath(0, 2));
    	
    	Graph g4 = new Graph(5);
    	g4.addEdge(0, 1, 7);
    	g4.addEdge(0, 3, 13);
    	g4.addEdge(0, 4, 100);
    	g4.addEdge(1, 2, 1);
    	g4.addEdge(1, 3, 8);
    	g4.addEdge(2, 3, 2);
    	g4.addEdge(3, 4, 20);
    	System.out.println(g4.shortestPath(0, 4));
    }

}
