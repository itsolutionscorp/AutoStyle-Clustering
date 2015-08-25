import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.List;

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
        Edge e = new Edge(v1, v2, edgeInfo);
        myAdjLists[v1].add(e);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        addEdge(v1, v2, edgeInfo);
        addEdge(v2, v1, edgeInfo);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
    	Edge e = new Edge(from, to, null);
    	if (myAdjLists[from].contains(e)) {
    		return true;
    	}
    	return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
        return null;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
    	Edge e = new Edge(0, vertex, null);
        int count = 0;
        for (int i = 0; i < myVertexCount; i++) {
        	if (myAdjLists[i].contains(e)) {
        		count++;
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
        	return visited.size() < myVertexCount && fringe.size() > 0;
        }

        public Integer next() {
        	Integer current = fringe.pop();
        	while (visited.contains(current)) {
            	current = fringe.pop();
        	}
        	for (Edge e : myAdjLists[current]) {
        		fringe.push(e.to());
        	}
        	visited.add(current);
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
    	ArrayList<Integer> a = visitAll(startVertex);
    	if (a.contains(stopVertex)) {
    		return true;
    	}
    	return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	if (pathExists(startVertex, stopVertex)) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            Iterator<Integer> iter = new DFSIterator(startVertex);
            while (iter.hasNext()) {
            	Integer i = iter.next();
                result.add(i);
                if (i == stopVertex) {
                	break;
                }
            }
            for (int i = result.size() - 1; i > 0; i--) {
            	if (!isAdjacent(result.get(i-1), result.get(i))) {
            		result.remove(i-1);
            	}
            }
            return result;
    	} else return new ArrayList<Integer>();
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        Iterator<Integer> iter = new DijkstraIterator(startVertex);
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (iter.hasNext()) {
        	Integer temp = iter.next();
        	list.add(temp);
        	if (temp.equals(endVertex)) {
        		break;
        	}
        }
        for (int i = list.size() - 1; i > 0; i--) {
        	if (!isAdjacent(list.get(i-1), list.get(i))) {
        		list.remove(i-1);
        	}
        }
        return list;
    }
    
    private class DijkstraIterator implements Iterator<Integer> {
    	
    	private ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
    	
    	public DijkstraIterator (int start) {
    		ArrayList<Integer> all = visitAll(start);
    		fringe.insert(all.get(0), 0);
    		for (int i = 1; i < all.size(); i++) {
    			fringe.insert(all.get(i), Double.POSITIVE_INFINITY);
    		}
    	}
    	
    	public boolean hasNext() {
    		return !(fringe.size() == 0);
    	}
    	
    	public Integer next() {
    		ArrayHeap<Integer>.Node node = fringe.removeMin();
    		Integer item = node.item();
    		double priority = node.priority();
    		for (Edge e : myAdjLists[item]) {
    			Integer info = (Integer) e.info();
    			Integer index = e.to();
    			if (priority + info < fringe.checkPriority(index)) {
    				fringe.changePriority(index, priority + info);
    			}
    		}
    		return item;
    		
    	}
    	
    	public void remove() {
    		throw new UnsupportedOperationException();
    	}
    	
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
            	currentInDegree[i] = inDegree(i);
            }
            for (int i = 0; i < myVertexCount; i++) {
            	if (currentInDegree[i] == 0) {
            		fringe.push(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty() && visited.size() < myVertexCount;
        }

        public Integer next() {
        	Integer current = fringe.pop();
        	visited.add(current);
        	for (int i = 0; i < myVertexCount; i++) {
        		if (isAdjacent(current, i)) {
        			currentInDegree[i]--;
        		}
        	}
        	for (int i = 0; i < myVertexCount; i++) {
        		if (currentInDegree[i] == 0 && !visited.contains(i)) {
        			fringe.push(i);
        		}
        	}
        	return current;
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
        
        public boolean equals(Object obj) {
        	Edge e = (Edge) obj;
        	return e.to().equals(myTo);
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
        g3.addEdge(2, 4, 10);
        g3.addEdge(3, 2, 20);
        g3.addEdge(3, 4, 60);
        ArrayList<Integer> path = g3.shortestPath(0, 4);
        System.out.println();
        System.out.println();
        System.out.println(path);
    }

}
