import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.HashMap;
import java.util.List;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices]; //initialize array
        myStartVertex = 0; //start index is always 0
        for (int k = 0; k < numVertices; k++) {
            myAdjLists[k] = new LinkedList<Edge>(); //initialize the list
        }
        myVertexCount = numVertices; //update vertex count
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
        if (!isAdjacent(v1, v2)) {
        	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
        }
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        if (!isAdjacent(v1, v2) && !isAdjacent(v2, v1)) {
        	addEdge(v1, v2, edgeInfo);
        	addEdge(v2, v1, edgeInfo);
        }
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
    	if (from >= myAdjLists.length) {
    		return false;
    	}
        LinkedList<Edge> listToSearch = myAdjLists[from];
        for (Edge i : listToSearch) {
        	if (i.myFrom == from && i.myTo == to) {
        		return true;
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
    	LinkedList<Integer> list = new LinkedList<Integer>();
    	if (vertex >= myAdjLists.length || vertex < 0) {
    		return null;
    	}
    	for (Edge e : myAdjLists[vertex]) {
    		list.add(e.myTo);
    	}
    	return list;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (LinkedList<Edge> list : myAdjLists) {
        	for (Edge i : list) {
        		if (i.myTo == vertex) {
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
            fringe.add(start);
            visited = new HashSet<Integer>();
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            int toReturn = fringe.pop();
            for(int i : neighbors(toReturn)) {
            	fringe.push(i); //Add all its neighbor items
            }
            visited.add(toReturn);
            while(!fringe.isEmpty() && visited.contains(fringe.peek())) {
            	fringe.pop();
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

    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
    	return pathExists(startVertex, stopVertex, new HashSet<Integer>());
    }
    
    private boolean pathExists(int startVertex, int stopVertex, HashSet<Integer> visited) {
    	if (startVertex == stopVertex) { //reached base case
    		return true;
    	}
    	visited.add(startVertex);
    	for (int i : neighbors(startVertex)) {
    		if (!visited.contains(i)) {
    			if (pathExists(i, stopVertex, visited)) {
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
    	ArrayList<Integer> toReturn = path(startVertex, stopVertex, new HashSet<Integer>());
    	if (toReturn == null) {
    		return new ArrayList<Integer>(); //no path, return an empty list
    	}
    	return toReturn; //return normally
    }
    
    //That's a helper method!!!
    private ArrayList<Integer> path(int startVertex, int stopVertex, HashSet<Integer> visited) {
    	if (startVertex == stopVertex) { //reached base case
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		list.add(startVertex);
    		return list;
    	}
    	
    	visited.add(startVertex); //mark start vertex for visited	
    	for (int i : neighbors(startVertex)) { //traverse all neighbors
    		if (!visited.contains(i)) {
    			ArrayList<Integer> list = path(i, stopVertex, visited);
    			if (list != null) { //No path was found
        			list.add(0, startVertex);
        			return list;
    			}
    		}
    	}
    	return null; //IMPORTANT the helper method return null if no path is found
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex) {
    	HashMap<Integer, ValueDistancePair> distanceMap = new HashMap<Integer, ValueDistancePair>(); //Vertex - Distance map
    	PriorityQueue<ValueDistancePair> queue = new PriorityQueue<ValueDistancePair>(); //a pair queue
    	
    	
    	for (int i : visitAll(startVertex)) {
    		ValueDistancePair current;
    		if (i == startVertex) {
    			current = new ValueDistancePair(i, 0);
    		} else {
    			current = new ValueDistancePair(i, Integer.MAX_VALUE);
    		}
    		distanceMap.put(i, current);
    		queue.offer(current);
    	}
    	
    	while (!queue.isEmpty()) {
    		ValueDistancePair p = queue.poll();
    		for (int n : neighbors(p.getItem())) {
    			ValueDistancePair current = distanceMap.get(n); //n or current is current neighbor
    			if (current.myDistance > p.myDistance + 1) { //need update
    				queue.remove(current); //first remove from queue
    				current.myDistance = p.myDistance + 1; //update distance
    				current.setPrev(p.getItem()); //update "prev"
    				queue.add(current); //add back!
    			}
    		}
    	}
    	
    	Stack<Integer> reversePath = new Stack<Integer>();
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	while (distanceMap.get(endVertex) != null) {
    		reversePath.push(distanceMap.get(endVertex).getItem());
    		endVertex = distanceMap.get(endVertex).getPrev();
    	}
    	while(!reversePath.isEmpty()) {
    		path.add(reversePath.pop());
    	}
    	return path;
    	
    	
    }
    
    private class ValueDistancePair implements Comparable {
    	private int myItem;
    	private int myDistance;
    	private int myPrevious;
    	
    	public ValueDistancePair(int item, int distance) {
    		myItem = item;
    		myDistance = distance;
    		myPrevious = -8888;
    	}
    	
    	public int getItem() {
    		return myItem;
    	}
    	public int myDistance() {
    		return myDistance;
    	}
    	public void setPrev(int prev) {
    		myPrevious = prev;
    	}
    	public int getPrev() {
    		return myPrevious;
    	}
    	
    	public int compareTo(Object ano) {
    		ValueDistancePair another = (ValueDistancePair)ano;
			return myDistance - another.myDistance;
    	}
    	public int hashcode() {
    		return myItem;
    	}
    	public boolean equals(Object ano) {
    		ValueDistancePair another = (ValueDistancePair)ano;
			return myDistance == another.myDistance && myItem == another.myItem;
    	}
    	
    }
    
    

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            return new Integer(0);
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
        result = g1.shortestPath(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.shortestPath(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.shortestPath(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        result = g1.shortestPath(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        result = g1.shortestPath(4, 0);
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
        
        Graph g3 = new Graph(3);
        g3.addEdge(0, 1, 1);
        g3.addEdge(0, 2, 4);
        g3.addEdge(1, 2, 1);
        System.out.println(g3.shortestPath(0, 2));
    }

}
