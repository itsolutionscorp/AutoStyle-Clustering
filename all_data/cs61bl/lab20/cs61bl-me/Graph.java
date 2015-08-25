import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
    	Edge newEdge = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(0, newEdge);
    	myAdjLists[v2].add(0, newEdge);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge newEdge = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(0, newEdge);
    	myAdjLists[v2].add(0, newEdge);
    	Edge newEdge2 = new Edge(v2, v1, edgeInfo);
    	myAdjLists[v1].add(0, newEdge2);
    	myAdjLists[v2].add(0, newEdge2);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
    	for (Edge e: myAdjLists[from]) {
    		if (e.to() == to) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public ArrayList<int[]> neighbors(int vertex) {
    	ArrayList<int[]> neighbors = new ArrayList<int[]>();
    	for (Edge e: myAdjLists[vertex]) {
    		int[] association = new int[2];
    		association[0] = vertex;
    		association[1] = e.to();
    		neighbors.add(association);
    	}
        return neighbors;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < myVertexCount; i++)
        	for (Edge e: myAdjLists[i]) {
        		if (e.to() == vertex) {
        			count++;
        		}
        	}
        return count / 2;
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
        	fringe.add(start);
        	visited.add(start);
        }

        public boolean hasNext() {
            //your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
        	int next = fringe.pop();
        	visited.add(next);
        	for (Edge e: myAdjLists[next]) {
        		if (!visited.contains(e.to())) {
        			fringe.push(e.to());
        		}
        	}
        	for (int i: visited) {
        		if (fringe.contains(i)) {
        			fringe.remove(fringe.indexOf(i));
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
    	if (startVertex == stopVertex) {
    		return true;
    	}
    	for (Edge e: myAdjLists[startVertex]) {
    		if (e.to() == startVertex) {
    			break;
    		}
    		if (pathExists(e.to(), stopVertex)) {
    			return true;
    		}
    	}
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> hist = new ArrayList<Integer>();
        hist.add(startVertex);
        // you supply the body of this method
        if (startVertex == stopVertex){
        	return hist;
        } else {
        	for (Edge e: myAdjLists[startVertex]) {
        		if (pathExists(e.to(), stopVertex)) {
        			for (int i: path(e.to(), stopVertex)) {
        				hist.add(i);
        			}
        			return hist;
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
        private int[] currentInDegree;
        private HashSet<Integer> visited;

        // more instance variables go here

        public TopologicalIterator() {
        	fringe = new Stack<Integer>();
        	currentInDegree = new int[myVertexCount];
        	visited =  new HashSet<Integer>();
        	for (int i = 0; i < myVertexCount; i++) {
        		int inDeg = inDegree(i);
        		currentInDegree[i] = inDeg;
        		if (inDeg == 0) {
        			fringe.add(i);
        			visited.add(i);
        		}
        	}
        	
        	//KEPT AS REFERENCE TO MY PAIN
//            fringe = new Stack<Integer>();
//            currentInDegree = new int[myAdjLists.length];
//            for (int i= 0; i < myAdjLists.length; i++) {
//            	currentInDegree[i] = -1;
//            }
//            //Keeps track of which ints are of InDegree 0 (because int[]s default to 0.)
//            boolean isSource;
//            //This is ludicrously ugly, and all it does is find the sources.
//            for (int i = 0; i < myAdjLists.length; i++) {
//            	isSource = true; 
//            	for (int j = 0; j < myAdjLists.length; j++) {
//            		for (Edge e: myAdjLists[j]) {
//            			if (e.to() == i) {
//            				isSource = false;
//            			}
//            		}
//            	}
//            	if (isSource) {
//            		currentInDegree[i] = 0;
//            		fringe.add(i);
//            	}
//            }
//            for (int i = 0; i < myAdjLists.length; i++) {
//            	if (inDegree(i) == 0) {
//            		currentInDegree[i] = 0;
//            	}
//            }
//            
//            //ABANDON ALL HOPE, YE WHO ENTER HERE.
//            for (int i = 0; i < myAdjLists.length; i++) {
//            	for (int k = 0; k < myAdjLists.length; k++) {
//            		if (currentInDegree[k] == i) {
//            			for (int[] j: neighbors(i)) {
//            				if (currentInDegree[j[2]] != -1) {
//            					currentInDegree[j[2]] = i + 1;
//            				}
//            			}
//            		}
//            	}
//            }
            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            // you supply the real body of this method
        	int toReturn = fringe.pop();
        	for (int[] i: neighbors(toReturn)) {
        		currentInDegree[i[1]] = currentInDegree[i[1]] - 1;
        	}
        	for (int i = 0; i < myVertexCount; i++) {
        		if (currentInDegree[i] == 0) {
        			if (!visited.contains(i)) {
        				fringe.push(i);
        				visited.add(i);
        			}
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
