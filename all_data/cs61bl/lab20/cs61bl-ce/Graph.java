import java.util.ArrayList;
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
    	for(int i = 0; i < this.myAdjLists[from].size(); i++) {
    		if (myAdjLists[from].contains(to)) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	List<Integer> myList = new ArrayList<Integer>();
    	for(int i = 0; i < this.myAdjLists[vertex].size(); i ++) {
    		myList.add(myAdjLists[vertex].get(i).to());
    	}
        return myList;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < this.myAdjLists.length; i++) {
        	for (int j = 0; j < this.myAdjLists[i].size();j++) {
        		if (this.myAdjLists[i].get(j).to() == vertex) {
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
            //your code here
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
        	fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
        	if (fringe.isEmpty()) {
        		return false;
        	}
        	int topNode = fringe.peek();
        	while (fringe.isEmpty() == false && visited.contains(topNode)) {
        		fringe.pop();
        		if (fringe.isEmpty()) {
        			return false;
        		}
        		topNode = fringe.peek();
        	}
            return true;
        }

        public Integer next() {
            //your code here
        	int topNode = fringe.pop();
        	visited.add(topNode);
        	List neigh = neighbors(topNode);
        	for (int i = 0; i < neigh.size(); i++) {
        		fringe.push((Integer) neigh.get(i));
//        		}
        	}
            return topNode;
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
    	}else if (visitAll(startVertex).contains(stopVertex)) {
    		return true;
    	}
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        Stack<Integer> fringe = new Stack<Integer>();
        ArrayList<Integer> pathFound = new ArrayList<Integer>();
        if (startVertex == stopVertex) {
        	result.add(startVertex);
        	return result;
        }
        while (iter.hasNext()) {
        	int next = iter.next();
        	result.add(next);
        	if (next == stopVertex) {
    			int current = stopVertex;
    			fringe.push(current);
    			while(!result.isEmpty()) {
    				for (int i = 0; i < result.size(); i++) {
    					if (current == startVertex) {
    						while (!fringe.isEmpty()) {
    							pathFound.add(fringe.pop());
    						}	
    						return pathFound;
    					}
    					if (neighbors(result.get(i)).contains(current)) {
    						fringe.push(result.get(i));
    						current = result.get(i);
    						result.remove(i);
    						i = 0;
    					}
    				}
    			}
        	}
        }
    	return new ArrayList<Integer>();
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
        
        Graph g3 = new Graph(7);
        g3.addUndirectedEdge(0, 2);
        g3.addUndirectedEdge(0, 3);
        g3.addUndirectedEdge(1, 4);
        g3.addUndirectedEdge(1, 5);
        g3.addUndirectedEdge(2, 3);
        g3.addUndirectedEdge(2, 6);
        g3.addUndirectedEdge(4, 5);
        System.out.println();
        System.out.println();
        System.out.println("Undirected Path exists check");
        System.out.println(g3.pathExists(0, 4));
        System.out.println(g3.pathExists(0, 6));
        System.out.println(g3.pathExists(1, 4));
        System.out.println(g3.pathExists(6, 3));
        System.out.println(g3.pathExists(3, 6));
        
        Graph g4 = new Graph(5);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.addEdge(2, 3);
        g4.addEdge(4, 2);
        System.out.println();
        System.out.println();
        System.out.println("Directed Path exists check");
        System.out.println(g4.pathExists(0, 3));
        System.out.println(g4.pathExists(3, 0));
        System.out.println(g4.pathExists(4, 0));
        System.out.println(g4.pathExists(0, 0));
        System.out.println(g4.pathExists(2, 1));
        System.out.println(g4.pathExists(2, 4));

    }

}