import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
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
        LinkedList<Edge> testList = myAdjLists[from];
        int counter = 0;
        while (counter < testList.size()) {
        	if (testList.get(counter).myTo == to) {
        		return true;
        	}
        	counter++;
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
    	ArrayList<Integer> vertices = new ArrayList<Integer>();
    	LinkedList<Edge> testList = myAdjLists[vertex];
        int counter = 0;
        while (counter < testList.size()) {
        	vertices.add(testList.get(counter).myTo);
        	counter++;
        }
        return vertices;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (LinkedList<Edge> adjList : myAdjLists) {
        	LinkedList<Edge> testList = adjList;
            int counter = 0;
            while (counter < testList.size()) {
            	if (testList.get(counter).myTo == vertex) {
            		count++;
            	}
            	counter++;
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
        	fringe.push(start);
        	visited = new HashSet<Integer>();
        	visited.add(start);
        }

        public boolean hasNext() {
            if (!fringe.isEmpty()) {
            	return true;
            }
            return false;
        }

        public Integer next() {
            Integer topElement = fringe.pop();
            for (Integer neighbor : neighbors(topElement)) {
            	if (!visited.contains(neighbor)) {
            		fringe.push(neighbor);
            		visited.add(neighbor);
            	}
            }
            return topElement;
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
        } else {
        	if (visitAll(startVertex).contains(stopVertex)) {
        		return true;
        	}
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> paths = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        while (!paths.contains(stopVertex) && pathExists(startVertex, stopVertex)) {
            Integer thisVertex = iter.next();
            if (pathExists(thisVertex, stopVertex)) {
            	paths.add(thisVertex);
            }
        } 
        return paths;
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
    	ArrayList<Integer> finishedInts = new ArrayList<Integer>();
    	ArrayList<Integer> shortestPath = new ArrayList<Integer>();
    	HashMap<Integer, Integer> vertexParent = new HashMap<Integer, Integer>();
    	HashMap<Integer, Double> vertexDistance = new HashMap<Integer, Double>();
    	Stack<Integer> pathOfInts = new Stack<Integer>();
    	
    	int counter = 0;
    	for (LinkedList index : myAdjLists) {
    		if (counter == startVertex) {
    			fringe.insert(counter, 0);
    			vertexDistance.put(counter, 0.0);
    		} else {
    			fringe.insert(counter, Integer.MAX_VALUE);
    			vertexDistance.put(counter, Double.MAX_VALUE);
    		}
    		counter++;
    	}
    	
    	while (counter > 0) {
    		System.out.println(fringe);
    		ArrayHeap.Node returnValue = fringe.removeMin();
    		int returnInt = (int) returnValue.item();
    		Double priority = returnValue.priority();
    		finishedInts.add(returnInt);
    		for (Edge e : myAdjLists[returnInt]) {
    			Integer thisItem = e.myTo;
    			Double thisPriority = (Double) e.myEdgeInfo;
    			if (thisPriority + priority < vertexDistance.get(thisItem) && !finishedInts.contains(thisItem)) {
    				Double newPriority = (Double) (thisPriority + priority);
    				fringe.changePriority(thisItem, newPriority);
    				vertexDistance.put(thisItem, newPriority);
        			vertexParent.put(thisItem, returnInt);
    			}
    			
    		}
    		counter--;
    	}
    	
    	int findStartPath = endVertex;
    	pathOfInts.add(endVertex);
    	while (findStartPath != startVertex) {
    		pathOfInts.push(vertexParent.get(findStartPath));
    		findStartPath = vertexParent.get(findStartPath);
    	}
    	
    	while (!pathOfInts.isEmpty()) {
    		shortestPath.add(pathOfInts.pop());
    	}
    	
        return shortestPath;
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
        private ArrayList<Integer> currentInDegree;
        private ArrayList<Integer> results;
        
        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new ArrayList<Integer>();
            results = new ArrayList<Integer>();
            Integer counter = 0;
            while (counter < myAdjLists.length) {
            	Integer thisInDegree = inDegree(counter);
            	currentInDegree.add(thisInDegree);
            	counter++;
            }
            Integer counter2 = 0;
            while (counter2 < currentInDegree.size()) {
            	if (currentInDegree.get(counter2) == 0) {
            		fringe.push(counter2);
            	}
            	counter2++;
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            Integer poppedOff = fringe.pop();
            for (Edge e : myAdjLists[poppedOff]) {
            	Integer toInt = e.to();
            	currentInDegree.set(toInt, currentInDegree.get(toInt) - 1);
            	if (currentInDegree.get(toInt) == 0) {
            		fringe.push(toInt);
            	}
            }
            results.add(poppedOff);
            return poppedOff;
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
    	/*
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
        */
        
        Graph g3 = new Graph(6);
        g3.addEdge(1, 2, 4.0);
        g3.addEdge(1, 3, 2.0);
        g3.addEdge(2, 3, 3.0);
        g3.addEdge(2, 4, 2.0);
        g3.addEdge(2, 5, 3.0);
        g3.addEdge(3, 2, 1.0);
        g3.addEdge(3, 4, 4.0);
        g3.addEdge(3, 5, 5.0);
        g3.addEdge(5, 4, 1.0);
        g3.shortestPath(1, 5);
    }

}
