import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
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
    	if (v1 < myVertexCount && v1 >= 0 && v2 < myVertexCount && v2 >= 0) {
    		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    	} else {
    		throw new IllegalArgumentException("Illegal Argument in addEdge method");
    	}
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
    	if (v1 < myVertexCount && v1 >= 0 && v2 < myVertexCount && v2 >= 0) {
	    	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	    	myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
    	} else {
    		throw new IllegalArgumentException("Illegal Argument in addEdge method");
    	}
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
    	if (from < myVertexCount && from >= 0 && to < myVertexCount && to >= 0) {
	    	LinkedList<Edge> lookup = myAdjLists[from];
	    	
	    	for (Edge temp : lookup) {
	    		if (temp.myTo == to) {
	    			return true;
	    		}
	    	}
	    	
	        return false;
    	} else {
    		throw new IllegalArgumentException("Illegal Argument in isAdjacent method");
    	}
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        if (vertex < myVertexCount && vertex >= 0) {
        	return myAdjLists[vertex];
        } else {
        	throw new IllegalArgumentException("Illegal Argument in neighbors method");
        }
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        
        if (vertex < myVertexCount && vertex >= 0) {
	        for (int i = 0; i < myVertexCount; i++) {
	        	for (Edge temp : myAdjLists[i]) {
	        		if (temp.myTo == vertex) {
	        			count++;
	        		}
	        	}
	        }
	        
	        return count;
        } else {
        	throw new IllegalArgumentException("Illegal Argument in inDegree method");
        }
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
        	if (start < myVertexCount && start >= 0) {
	            fringe = new Stack<Integer>();
	            visited = new HashSet<Integer>();
	            fringe.push(start);
        	} else {
        		throw new IllegalArgumentException("Illegal Argument while initializing DFSIterator");
        	}
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	Integer next = fringe.pop();
        	
        	for (Edge temp : myAdjLists[next]) {
        		if (!visited.contains(temp.myTo)) {
        			fringe.push(temp.myTo);
        		}
        	}
        	
        	visited.add(next);
        	
        	Integer temp;
        	while (!fringe.isEmpty()) {
        		temp = fringe.peek();
        		if (!visited.contains(temp)) {
        			break;
        		}
        		fringe.pop();
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
    	if (startVertex == stopVertex) return true;
    	
    	ArrayList<Integer> temp = visitAll(startVertex);
    	for (Integer vertex : temp) {
    		if (stopVertex == vertex) return true;
    	}
    	
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        Integer savetemp;
        boolean exist = false;
        
        while (iter.hasNext()) {
        	savetemp = iter.next();
            if (savetemp == stopVertex) {
            	exist = true;
            	break;
            }
        	result.add(savetemp);
        }

        if (exist) {
        	savetemp = stopVertex;
        	temp.add(savetemp);
        	for (int i = result.size() - 1; i >= 0; i--) {
        		if (isAdjacent(result.get(i), savetemp)) {
        			savetemp = result.get(i);
        			temp.add(savetemp);
        			if (savetemp == startVertex) {
        				break;
        			}
        		}
        	}
        	
        	Integer swap;
        	for (int i = 0; i < temp.size() / 2; i++) {
        		swap = temp.get(temp.size() - 1 - i);
        		temp.set(temp.size() - 1 - i, temp.get(i));
        		temp.set(i, swap);
        	}
        } 
        
        return temp;
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

    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
    	HashMap<Integer, Integer> history = new HashMap<Integer, Integer>();
    	HashMap<Integer, Integer> track = new HashMap<Integer, Integer>();
    	boolean isFinished = false;
    	ArrayList<Integer> list = new ArrayList<Integer>();

    	if (startVertex == endVertex) {
    		list.add(startVertex);
    		return list;
    	}
    	
    	for (int i = 0; i < myVertexCount; i++) {
    		fringe.insert(i, Integer.MAX_VALUE);
    		track.put(i, Integer.MAX_VALUE);
    	}
    	
    	fringe.changePriority(startVertex, 0);
    	track.put(startVertex, 0);
    	
    	while (fringe.peek() != null) {
    		ArrayHeap.Node node = fringe.removeMin();
    		Integer current = (Integer) node.item();
    		track.remove(current);
    		
    		if (current == endVertex && node.priority() != Integer.MAX_VALUE) {
    			isFinished = true;
    			break;
    		}
    		
    		for (Edge temp : myAdjLists[current]) {
    			if (track.containsKey(temp.myTo)) {
	    			if ((node.priority() + (Integer) temp.myEdgeInfo) < track.get(temp.myTo)) {
	    				track.put(temp.myTo, (int) (node.priority() + (Integer) temp.myEdgeInfo));
	    				fringe.changePriority(temp.myTo, (int) (node.priority() + (Integer) temp.myEdgeInfo));
	    				history.put(temp.myTo, current);
	    			}
    			}
    		}
    	}
    	
    	if (isFinished) {
    		Stack<Integer> Path = new Stack<Integer>();
    		Integer cursor = endVertex;
    		while (cursor != startVertex) {
    			Path.push(cursor);
    			cursor = history.get(cursor);
    		}
    		list.add(startVertex);
    		while (!Path.isEmpty()){
    			list.add(Path.pop());
    		}
    	}
    	
    	return list;
    }
    
    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private int[] counter;
        private HashSet<Integer> visited;
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            counter = new int[myVertexCount];
            visited = new HashSet<Integer>();
            
            for (int i = 0; i < myVertexCount; i++) {
            	counter[i] = inDegree(i);
            	if (counter[i] == 0) {
            		fringe.push(i);
            		visited.add(i);
            	}
            }

            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	Integer result = fringe.pop();
        	
        	for (Edge temp : myAdjLists[result]) {
        		counter[temp.myTo]--;
        	}
        	
            for (int i = 0; i < myVertexCount; i++) {
            	if (counter[i] == 0 && !visited.contains(i)) {
            		fringe.push(i);
            		visited.add(i);
            	}
            }
            return result;
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
        Iterator<Integer> iter;
        
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
        
        Graph g3 = new Graph(8);
       
        g3.addEdge(0, 3);
        g3.addEdge(0, 2);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(2, 5);
        g3.addEdge(3, 5);
        g3.addEdge(4, 6);
        g3.addEdge(5, 7);
        g3.addEdge(6, 7);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort");
        result = g3.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        
        System.out.println("G4 ========");
        Graph g4 = new Graph(5);
        g4.addEdge(0, 4, 5);
        g4.addEdge(0, 1, 10);
        g4.addEdge(1, 4, 2);
        g4.addEdge(1, 2, 1);
        g4.addEdge(2, 3, 4);
        g4.addEdge(3, 0, 7);
        g4.addEdge(3, 2, 6);
        g4.addEdge(4, 3, 2);
        g4.addEdge(4, 2, 9);
        g4.addEdge(4, 1, 3);
        ArrayList<Integer> list = g4.shortestPath(4, 2);
        for (int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i));
        }
        
        System.out.println("=========");
        Graph g5 = new Graph(5);
        g5.addEdge(0, 4, 100);
        g5.addEdge(0, 3, 30);
        g5.addEdge(0, 1, 10);
        g5.addEdge(1, 2, 50);
        g5.addEdge(2, 4, 10);
        g5.addEdge(3, 2, 20);
        g5.addEdge(3, 4, 60);
        list = g5.shortestPath(0, 2);
        for (int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i));
        }
    }

}
