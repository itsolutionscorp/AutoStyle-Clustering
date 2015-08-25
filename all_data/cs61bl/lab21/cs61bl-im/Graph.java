import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.*;

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
        for (Edge e: myAdjLists[from]) {
            if (e.to() == to)
                return true;
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        for (Edge e: myAdjLists[vertex]) {
            vertices.add(e.to());
        }
        return vertices;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (int v = 0; v < myVertexCount; v ++) {
            if (isAdjacent(v, vertex))
                count ++;
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
            fringe.push(start);
            visited = new HashSet<Integer>();
        }

        public boolean hasNext() {
            //your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
            Integer toReturn = fringe.pop();
            visited.add(toReturn);
            for (Integer v: (ArrayList<Integer>) neighbors(toReturn))
                if (!visited.contains(v))
                    fringe.push(v);
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
        if (startVertex == stopVertex)
            return true;
        return visitAll(startVertex).contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (startVertex == stopVertex) {
            result.add(startVertex);
            return result;
        }
        if (!pathExists(startVertex, stopVertex)) return result;

        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            Integer v = iter.next();
            result.add(v);
            if (v == stopVertex)
                break;
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

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new int[myVertexCount];
            for (int i = 0 ; i < myVertexCount; i++) {
                for (int v: (ArrayList<Integer>) neighbors(i)) {
                    currentInDegree[v]++;
                }
            }
            for (int v = 0; v < myVertexCount; v++) {
                if (currentInDegree[v] == 0) {
                    fringe.push(v);
                }
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            // you supply the real body of this method
            Integer toReturn = fringe.pop();
            for (int v: (ArrayList<Integer>) neighbors(toReturn)) {
                currentInDegree[v]--;
                if (currentInDegree[v] == 0) {
                    fringe.push(v);
                }
            }
            return toReturn;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	if (! pathExists(startVertex, endVertex)) {
    		return new ArrayList<Integer>();
    	}
        ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        Stack<Integer> forReverse = new Stack<Integer>();
        int[] vertices = new int[myVertexCount];
        fringe.insert(startVertex, 0);
        for (int i = 0; i < myVertexCount; i ++) {
        	if (i != startVertex) {
        		fringe.insert(i, Double.POSITIVE_INFINITY);
        	}
        }
        while (!fringe.isEmpty()) {
        	ArrayHeap<Integer>.Node vNode = fringe.removeMin();
        	if (vNode.item().equals(endVertex)) {
        		break;
        	}
        	for (Edge e: myAdjLists[vNode.item()]) {
        		if (fringe.changePriority(e.to(), vNode.priority() + (int) e.info())) {
        			vertices[e.to()] = vNode.item();
        		}
        	}
        }
        for (int i = endVertex; i != startVertex; i = vertices[i]) {
        	forReverse.push(i);
        }
        forReverse.push(startVertex);
        
        while (!forReverse.isEmpty()) {
        	path.add(forReverse.pop());
        }
        return path;
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
        g3.addEdge(0,  1, 10);
        g3.addEdge(0, 3, 30);
        g3.addEdge(0, 4, 100);
        g3.addEdge(1, 2, 50);
        g3.addEdge(2, 4, 10);
        g3.addEdge(3, 4, 60);
        g3.addEdge(3, 2, 20);
        
        for (int v = 0; v < g3.myVertexCount; v ++) {
        	for (int w = 0; w < g3.myVertexCount; w ++) {
	        	System.out.println("Shortest distance from " + v + " to " + w + " is ");
	        	for (Integer i: g3.shortestPath(v, w)) {
	        		System.out.print(i + " ");
	        	}        
	        	System.out.println();
        	}
        }
    }

}
