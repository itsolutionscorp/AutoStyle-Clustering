import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
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
    	Edge a = new Edge(v1, v2, edgeInfo);
    	if(myAdjLists[v1] == null) {
    		LinkedList<Edge> e = new LinkedList<Edge>();
    		e.add(a);
    		myAdjLists[v1] = e;
    	} else {
    		myAdjLists[v1].add(a);
    	}
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	addEdge(v1, v2, edgeInfo);
    	addEdge(v2, v1, edgeInfo);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	for (Edge e : myAdjLists[from]) {
    		if (e.myTo == to) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public LinkedList<Edge> neighbors(int vertex) {
        // your code here
        return myAdjLists[vertex];
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i != vertex && i < myAdjLists.length; i++) {
        	for (Edge e : myAdjLists[i]) {
        		if (e.myTo == vertex) {
        			count ++;
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
        private int start_point;
        private boolean more;

        public DFSIterator(Integer start) {
            //your code here
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
        	start_point = start;
        	fringe.add(start);
        	more = true;
        }

        public boolean hasNext() {
            //your code here
            return more;
        }

        public Integer next() {
            //your code here
        	int returned = fringe.pop();
        	visited.add(returned);
        	LinkedList<Edge> a = neighbors(returned);
        	for (Edge e : a) {
        		if (!visited.contains(e.myTo)) {
        			fringe.push(e.myTo);
        		}
        	}
        	if (fringe.isEmpty()) {
        		more = false;
        	}
            return returned;
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
    	if (visitAll(startVertex).contains(stopVertex)) {
    		return true;
    	}
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) { //TODO
    	if (!pathExists(startVertex, stopVertex)) {
    		return new ArrayList<Integer>();
    	}
    	ArrayList<Integer> the_path = new ArrayList<Integer>();
    	Iterator<Integer> iter = new DFSIterator(startVertex);
    	while (iter.hasNext()) {
    		int a = iter.next();
    		if (pathExists(a, stopVertex)) {
        		the_path.add(a);
    		}
    		if (a == stopVertex) {
    			break;
    		}
    	}
        return the_path;
        // you supply the body of this method
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    
    public ArrayList<Integer> shortestPath(int startVertext, int endVertext) {
    	ArrayList<Integer> dijkstra = new ArrayList<Integer>();
    	ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
    	int[] pre = new int[myAdjLists.length];
    	int[] weights = new int[myAdjLists.length];
    	for (int i = 0; i < myAdjLists.length; i++) {
    		if (i == startVertext) {
    			fringe.insert(i, 0);
    		} else {
    			fringe.insert(i, Double.POSITIVE_INFINITY);
    		}
    	}
    	while(fringe.peek() != null) {
    		for (Edge e: neighbors(fringe.peek().item())) {
    			if (pre[e.myTo] == 0) {
        			pre[e.myTo] = fringe.peek().item();
    			}
    			if (weights[e.myTo] == 0 && e.myFrom == startVertext) {
        			weights[e.myTo] = (int)e.myEdgeInfo;
    			} 
    			else if (weights[e.myTo] == 0) {
    				int path = 0;
    				int pred = pre[e.myTo];
    				while(pred != startVertext && pred != 0) {
    					path += weights[pred];
    					pred = pre[pred];
    				}
    				weights[e.myTo] = path + (int)e.myEdgeInfo;
    			}
				if (weights[e.myTo] > (int)e.myEdgeInfo + weights[e.myFrom]) {
    				weights[e.myTo] = (int)e.myEdgeInfo + weights[e.myFrom];
    				pre[e.myTo] = e.myFrom;
    			}
    		}
    		fringe.removeMin();
    	}
    	int pred = pre[endVertext];
    	dijkstra.add(endVertext);
    	while (pred != startVertext) {

    		dijkstra.add(pred);
    		pred = pre[pred];
    	}
    	dijkstra.add(startVertext);
    	Collections.reverse(dijkstra);
    	return dijkstra;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private int[] currentInDegree;
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new int[myAdjLists.length];
            for (int i = 0; i < myAdjLists.length; i++) {
            	int degree = inDegree(i);
            	currentInDegree[i] = degree;
            	if (degree == 0) {
            		fringe.push(i);
            	}
            }
            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	int returned = fringe.pop();
        	LinkedList<Edge> the_neighbors = neighbors(returned);
        	for (Edge e : the_neighbors) {
        		currentInDegree[e.myTo] = currentInDegree[e.myTo] - 1;
        		if (currentInDegree[e.myTo] == 0) {
        			fringe.push(e.myTo);
        		}
        	}
            return returned;
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
        g1.addEdge(0, 1, 10);
        g1.addEdge(0, 3, 30);
        g1.addEdge(0, 4, 100);
        g1.addEdge(1, 2, 50);
        g1.addEdge(2, 4, 10);
        g1.addEdge(3, 2, 20);
        g1.addEdge(3, 4, 60);
        g1.shortestPath(0, 4);
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
//        
//        
//        Graph g5 = new Graph(7);
//        g5.addUndirectedEdge(0,2); 
//        g5.addUndirectedEdge(0,3); 
//        g5.addUndirectedEdge(1,4); 
//        g5.addUndirectedEdge(1,5); 
//        g5.addUndirectedEdge(2,3); 
//        g5.addUndirectedEdge(2,6); 
//        g5.addUndirectedEdge(4,5);
////        g5.addEdge(0,1); 
////        g5.addEdge(1,2); 
////        g5.addEdge(2,0); 
////        g5.addEdge(2,3); 
////        g5.addEdge(4,2); 
//        System.out.println(g5.pathExists(0, 3));
//        
//        
//        
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
    }

}
