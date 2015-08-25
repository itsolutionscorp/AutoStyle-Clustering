import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
        Edge toBeAdded = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(toBeAdded);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        Edge toBeAdded1 = new Edge(v1, v2, null);
        Edge toBeAdded2 = new Edge(v2, v1, null);
        myAdjLists[v1].add(toBeAdded1);
        myAdjLists[v2].add(toBeAdded2);
        
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        for (Edge e : myAdjLists[from]) {
        	if ((e.myFrom == from) && (e.myTo == to)) {
        		return true;
        	}
        }
        return false;
    }

    // Returns an ArrayList of the shortest path between two vertices.
    public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
        // Run Dijkstra's on startVertex.
        // Find end to start.
        // Return.
        ArrayHeap<Integer> myHeap = new ArrayHeap<Integer>();
        ArrayList<Integer> minPath = new ArrayList<Integer>();        
        Double[] myWeights = new Double[myVertexCount];
        Integer[] myPaths = new Integer[myVertexCount];

        for (int i = 0; i < myAdjLists.length; i += 1) {
            if (i == startVertex) {
                myWeights[i] = 0.0;
                myPaths[i] = startVertex;
            } else {
                myWeights[i] = Double.POSITIVE_INFINITY;
                myHeap.insert(i, Double.POSITIVE_INFINITY);
                myPaths[i] = -1;
            }
        }

        dijk(startVertex, myHeap, myWeights, myPaths);
        
        int pointer = endVertex;
        while (pointer != startVertex) {
            minPath.add(pointer);
            pointer = myPaths[pointer];
        }
        minPath.add(startVertex);

        Collections.reverse(minPath);
        return minPath;
    }

    public ArrayList<Integer> dijk(int s, ArrayHeap<Integer> heap, Double[] weights, Integer[] paths) {
        ArrayList<Integer> allPaths = new ArrayList<Integer>();
        allPaths.add(s);
        ArrayList<Integer> n = neighbors(s);
        for (Integer each : n) {
            if (!allPaths.contains(each)) {
                Double w = edgeWeight(s, each);
                if (w + weights[s] < weights[each]) {
                    weights[each] = w + weights[s];
                    heap.changePriority(each, weights[each]);
                    paths[each] = s;
                }                
            }
        }

        if (heap.peek() != null) {
            int min = heap.removeMin().item();
            allPaths.addAll(dijk(min, heap, weights, paths));            
        }

        return allPaths;
    }

    public Double edgeWeight(int u, int v) {
        for (Edge e : myAdjLists[u]) {
            if (e.myTo == v) {
                return (Double) e.myEdgeInfo;
            }
        }
        return 0.0;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public ArrayList<Integer> neighbors(int vertex) {
        ArrayList<Integer> vertexNeighbors = new ArrayList<Integer>();
    	for (Edge e : myAdjLists[vertex]) {
        	vertexNeighbors.add(e.myTo);
        }
    	return vertexNeighbors;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (LinkedList<Edge> l: myAdjLists) {
        	for (Edge e : l) {
        		if (e.myTo == vertex) {
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
            if (fringe.isEmpty()) {
            	return false;
            }
            return true;
        }

        public Integer next() {
        	Integer curr = fringe.pop();
            visited.add(curr);
            for (Edge e : myAdjLists[curr]) {
	            if (!visited.contains(e.myTo)) {
	            	fringe.push(e.myTo);
	            }
            }
        	return curr;
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
        if (startVertex == stopVertex) {
        	return true;
        }
        ArrayList<Integer> all = visitAll(startVertex);
        if (all.contains(stopVertex)) {
        	return true;
        }
        else {
        	return false;
        }
    }

    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        ArrayList<Integer> path = new ArrayList<Integer>();

        if (pathExists(startVertex, stopVertex)) {
            // you supply the body of this method
            result.add(startVertex);
            while (iter.hasNext()) {
                if (iter.next() == stopVertex) {
                    break;
                }
                else {
                    result.add(iter.next());
                }
            }
            path.add(stopVertex);
            return pathBuilder(startVertex, stopVertex, result, path);
        }
        return result;
    }

    public ArrayList<Integer> pathBuilder(int startVertex, int stopVertex, ArrayList<Integer> visited, ArrayList<Integer> path) {
        for (Integer e : visited) {
            if (isAdjacent(e, path.get(0))) {
                path.add(0, e);
                if (e == startVertex) {
                    return path;
                }
            break;
            }
        }
        return pathBuilder(startVertex, stopVertex, visited, path);
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
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1, 2.0);
        g1.addEdge(0, 2, 4.0);
        g1.addEdge(0, 4, 5.0);
        g1.addEdge(1, 2, 1.0);
        g1.addEdge(2, 0, 2.0);
        g1.addEdge(2, 3, 2.0);
        g1.addEdge(4, 3, 4.0);

        System.out.println(g1.shortestPath(0, 1));
        System.out.println(g1.shortestPath(0, 2));
        System.out.println(g1.shortestPath(0, 3));
        System.out.println(g1.shortestPath(2, 1));
    }

}
