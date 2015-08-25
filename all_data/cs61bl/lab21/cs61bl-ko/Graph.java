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
        // TODO your code here
    	Edge temp = new Edge(v1, v2, edgeInfo);
    	if (!myAdjLists[v1].contains(temp)) {
    		myAdjLists[v1].add(temp);
    	}
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        // TODO your code here
    	addEdge(v1, v2, edgeInfo);
    	addEdge(v2, v1, edgeInfo);    	
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        // TODO your code here
    	for (Edge i : myAdjLists[from]) {
    		if (i.to() == to) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // TODO your code here
    	LinkedList<Integer> ret = new LinkedList<Integer>();
    	for (Edge e : myAdjLists[vertex]) {
    		ret.add(e.to());
    	}
    	return ret;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        // TODO your code here
        for (LinkedList<Edge> l : myAdjLists) {
        	for (Edge e : l) {
        		if (e.to() == vertex) {
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
            // TODO your code here
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
        	fringe.push(start);
        }

        public boolean hasNext() {
            // TODO your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            // TODO your code here
        	Integer ret = fringe.pop();
        	visited.add(ret);
        	for (Edge e : myAdjLists[ret]) {
        		if (! visited.contains(e.to())) {
        			fringe.push(e.to());
        		}
        	}
            return ret;
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
        // TODO your code here
    	if (startVertex == stopVertex) {
    		return true;
    	}
    	ArrayList<Integer> check = visitAll(startVertex);
    	if (check.contains(stopVertex)) {
    		return true;
    	}
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        // TODO you supply the body of this method
    	ArrayList<Integer> answer = new ArrayList<Integer>();
        if (!pathExists(startVertex, stopVertex)) {
        	return answer;
        }
        Iterator<Integer> iter = new DFSIterator(startVertex);
        ArrayList<Integer> visited = new ArrayList<Integer>();
        Integer end = null;
        while (iter.hasNext()) {
        	end = iter.next();
        	if (end.equals(stopVertex)) {
        		answer.add(end);
        		break;
        	}
        	if (!visited.contains(end)) {
        		visited.add(end);
        	}
        }
        Integer temp = end;
        while (!temp.equals(startVertex))
	        for (Integer i: visited) {
	        	if (isAdjacent(i, temp)) {
	        		answer.add(0, i);
	        		temp = i;
	        		break;
	        	}
        }
        return answer;
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

        // TODO more instance variables go here
        Integer[] currentInDegree;

        public TopologicalIterator() {
        	fringe = new Stack<Integer>();
            // TODO more statements go here
            currentInDegree = new Integer[myAdjLists.length];
            for (int k = 0; k < currentInDegree.length; k++) {
            	currentInDegree[k] = inDegree(k);
            	if (currentInDegree[k].equals(0)) {
            		fringe.push(k);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            // TODO you supply the real body of this method
        	Integer ret = fringe.pop();
        	List<Integer> temp = neighbors(ret);
        	for (Integer i : temp) {
        		currentInDegree[i]--;
        	}
        	currentInDegree[ret]--;
        	for (int i = 0; i < currentInDegree.length; i++) {
        		if (currentInDegree[i].equals(0)) {
        			if (!fringe.contains(i)) {
        				fringe.push(i);
        			}
        		}
        	}
        	return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
    	ArrayList<Integer[]> latest = new ArrayList<Integer[]>();
    	for (int i = 0; i < myAdjLists.length; i++) {
    		fringe.insert(i, Integer.MAX_VALUE);
    		Integer[] pair = {-1, Integer.MAX_VALUE};
    		latest.add(pair);
    	}
    	fringe.changePriority(startVertex, 0);
    	latest.get(startVertex)[1] = 0;
    	double soFar;
    	while (fringe.peek() != null) {
    		Integer check = fringe.removeMin().item();
    		soFar = latest.get(check)[1];
    		for (Edge e : myAdjLists[check]) {
    			if (soFar + (double) (int) e.info() < latest.get(e.to())[1]) {
	    			fringe.changePriority(e.to(), soFar + (double) (int) e.info());
	    			latest.get(e.to())[0] = check;
	    			latest.get(e.to())[1] = (int) (soFar + (double) (int) e.info());
    			}
    		}
        }
    	for (Integer[] i : latest) {
    		System.out.println(i[0] + " and " + i[1]);
    	}
    	ArrayList<Integer> answer = new ArrayList<Integer>();
    	int stop = endVertex;
    	while (stop != -1) {
    		if (latest.get(stop)[1] != Integer.MAX_VALUE) {
    			answer.add(0, stop);
    		}
    		stop = latest.get(stop)[0];
    	}
    	return answer;
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
        
        @Override
        public boolean equals(Object obj) {
        	Edge other = (Edge) obj;
        	if (myTo == other.myTo) {
        		if (myFrom == other.myFrom) {
        			if (myEdgeInfo.equals(other.myEdgeInfo)) {
        				return true;
        			}
        		}
        	}
        	return false;
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
        
        // our addition
        Graph g6 = new Graph(8);
        g6.addUndirectedEdge(0,2); 
        g6.addUndirectedEdge(0,3); 
        g6.addUndirectedEdge(1,4); 
        g6.addUndirectedEdge(1,5); 
        g6.addUndirectedEdge(2,3); 
        g6.addUndirectedEdge(2,6); 
        g6.addUndirectedEdge(4,5); 

        g6.addEdge(0,1); 
        g6.addEdge(1,2); 
        g6.addEdge(2,0); 
        g6.addEdge(2,3); 
        g6.addEdge(4,2);
        g6.addEdge(3,7);
        
	    for (int i = 0; i < 8; i++) {
	    	for (int j = 0; j < 8; j ++) {
	    		System.out.println("i: " + i + " j: " + j + " " + g6.pathExists(i, j));
	    	}
	    	
	    }
        
        System.out.println("Lookie here ( ͡° ͜ʖ ͡°)");
        
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

        System.out.println("( ͡° ͜ʖ ͡°)");
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
        
        Graph g3 = new Graph(6);
        g3.addEdge(5, 2);
        g3.addEdge(2, 1);
        g3.addEdge(1, 4);
        g3.addEdge(4, 0);
        g3.addEdge(0, 3);
        System.out.println();
        System.out.println();
        System.out.println("path test in straight line");
        result = g3.path(5, 3);
        ArrayList<Integer> result2 = g3.path(5, 5);
        ArrayList<Integer> result3 = g3.path(3, 5);
        ArrayList<Integer> result4 = g3.path(5, 0);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println("Topological sort");
        result = g3.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        
        Graph g4 = new Graph(1);
        g4.addEdge(0, 0);
        System.out.println(g4.isAdjacent(0, 0));
        System.out.println("Topological sort");
        result = g4.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        
        System.out.println("G5");
        Graph g5 = new Graph(4);
        g5.addEdge(1, 2);
        g5.addEdge(1, 0);
        g5.addEdge(0, 2);
        System.out.println(g5.path(0, 3));
        System.out.println(g5.path(3, 0));
        System.out.println(g5.path(3, 3));
        System.out.println(g5.isAdjacent(3, 3));
        System.out.println(g5.isAdjacent(0, 3));
        System.out.println(g5.neighbors(3));
        System.out.println(g5.neighbors(2));
        System.out.println(g5.neighbors(1));
        System.out.println(g5.inDegree(3));
        System.out.println(g5.inDegree(0));
        System.out.println("Topological sort");
        result = g5.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        
        System.out.println("G7");
        Graph g7 = new Graph(5);
        g7.addEdge(0, 1, 10);
        g7.addEdge(0, 3, 30);
        g7.addEdge(0, 4, 100);
        g7.addEdge(1, 2, 50);
        g7.addEdge(2, 4, 10);
        g7.addEdge(3, 2, 20);
        g7.addEdge(3, 4, 60);
        System.out.println(g7.shortestPath(0, 4)); // [0, 3, 2, 4]
        System.out.println(g7.shortestPath(0, 3)); // [0, 3]
        System.out.println(g7.shortestPath(0, 2)); // [0, 3, 2]
        System.out.println(g7.shortestPath(0, 1)); // [0, 1]
        System.out.println(g7.shortestPath(0, 0)); // [0]
        System.out.println(g7.shortestPath(4, 0)); // []
        System.out.println(g7.shortestPath(4, 1)); // []
        System.out.println(g7.shortestPath(4, 2)); // []
        System.out.println(g7.shortestPath(4, 3)); // []
        System.out.println(g7.shortestPath(4, 4)); // [4]
        
        Graph g8 = new Graph(8);
        // A - 0
        // B - 1
        // C - 2
        // D - 3
        // E - 4
        // F - 5
        // G - 6
        // H - 7
        g8.addEdge(0, 1, 20);
        g8.addEdge(0, 3, 80);
        g8.addEdge(0, 6, 90);
        g8.addEdge(1, 5, 10);
        g8.addEdge(2, 3, 10);
        g8.addEdge(2, 5, 50);
        g8.addEdge(2, 7, 20);
        g8.addEdge(3, 6, 20);
        g8.addEdge(4, 1, 50);
        g8.addEdge(4, 6, 30);
        g8.addEdge(5, 2, 10);
        g8.addEdge(5, 3, 40);
        g8.addEdge(6, 0, 20);
        System.out.println(g8.shortestPath(0, 6)); // []
        
        
    }

}
