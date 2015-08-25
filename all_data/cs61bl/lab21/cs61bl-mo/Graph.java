import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	PriorityQueue<Edge> fringe = new PriorityQueue<Edge>();
    	ArrayList<Integer> path = new ArrayList<Integer>();    	
    	int[] dist = new int[myVertexCount];
    	int[] prevList = new int[myVertexCount];
    	
    	for (int i = 0; i < myVertexCount; i++) {
    		dist[i] = Integer.MAX_VALUE;
    	}
    	dist[startVertex] = 0;
    	prevList[startVertex] = startVertex;
    	for (Edge e : myAdjLists[startVertex]){
    		fringe.offer(e);
    	}
    	int prevNode = startVertex;
    	while (!fringe.isEmpty()) {
	    	Edge next = fringe.poll();
	    	prevNode = next.myFrom;
	    	if (next.to() == endVertex) {
	    		prevList[next.to()] = prevNode;
	    		dist[next.to()] = dist[prevNode] + (int)next.info();
	    		break;
	    	}
	    	else if (dist[prevNode] + (int)next.info() < dist[next.to()]) {
	    		dist[next.to()] = dist[prevNode] + (int)next.info();
	    		prevList[next.to()] = prevNode;
	    		for (Edge e : myAdjLists[(int) next.to()]) {
	    			if (!fringe.contains(e)) {
	    				fringe.offer(e);
	    			}
	    		}
	    	}
	    	else {
	    		for (Edge e : myAdjLists[(int) next.to()]) {
	    			if (!fringe.contains(e)) {
	    				fringe.offer(e);
	    			}
	    		}	    		
	    	}

    	}
    	if (dist[endVertex] == Integer.MAX_VALUE) {
    		return path;
    	}
    	ArrayList<Integer> backpath = new ArrayList<Integer>();    	
		int toAdd = prevList[endVertex];
    	while (toAdd != startVertex) {
    		backpath.add(toAdd);
    		toAdd = prevList[toAdd];
    	}
    	backpath.add(startVertex);
    	for(int i = backpath.size()-1; i >= 0; i--) {
    		path.add(backpath.get(i));
    	}
    	path.add(endVertex);
    	return path;
    	
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
    	Edge e = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(e);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge e1 = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(e1);
    	Edge e2 = new Edge(v2, v1, edgeInfo);
    	myAdjLists[v2].add(e2);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	boolean hasEdge = false;
    	for (Edge e : myAdjLists[from]) {
    		if (e != null) {
    		if (e.to() == to) {
    			hasEdge = true;
    		}
    		}
    	}
        return hasEdge;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
        // your code here
        ArrayList<Integer> l = new ArrayList<>();
	        for (Edge e : myAdjLists[vertex]) {
	        	if (e != null ){
	    		l.add(e.to());
	        }
        }
        return l;
    }
        
    

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < myVertexCount; i++){
	        for (Edge e : myAdjLists[i]) {
	        	if (e != null) {
	    		if (e.to() == vertex) {
	    			count++;
	    		}
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
           else if (!fringe.isEmpty() && !visited.contains(fringe.peek())) {
        	   return true;
           }
           else if (visited.contains(fringe.peek())) {
        	   fringe.pop();
        	   return hasNext();
           }
           return false;
        }

        public Integer next() {
            //your code here
        	int next = fringe.pop();
        	while (visited.contains(next)){
        		next = fringe.pop();
        	}
        	visited.add(next);
        	if (myAdjLists[next] != null) {
	        	for (Edge e : myAdjLists[next]) {
	        		if (e != null) {
	            	fringe.push(e.to());
	        		}
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
    	boolean toReturn  = false;
        ArrayList<Integer> a = visitAll(startVertex);
       
        if (startVertex == stopVertex) {
        	toReturn = true;
        }
        else if (myAdjLists[startVertex] == null) {
        	toReturn = false;
        }
        else if	(a.contains(stopVertex) && a.contains(startVertex)) {
        	toReturn = true;
        	
        }
        return toReturn;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        // you supply the body of this method
    	HashSet<Integer> visited = new HashSet<Integer>();
    	Stack<Integer> fringe = new Stack<Integer>();
    	HashMap<Integer, Integer> steps = new HashMap<Integer, Integer>();
    	fringe.push(startVertex);
        if (pathExists(startVertex, stopVertex) == false) {
            ArrayList<Integer> a = new ArrayList<Integer>();
        	return a;
        }
        else if (startVertex == stopVertex) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            a.add(startVertex);
        	return a;
        }
        else {
        	Iterator iter = new DFSIterator(startVertex);
        	while (iter.hasNext()) {
        		Integer i = (Integer) iter.next();
        		if (i == stopVertex) {
        			break;
        		}
        		visited.add(i);
        		for (Edge e : myAdjLists[i]) {
        			if (!visited.contains(e.to())) {
        				steps.put(i, e.to());
        				fringe.push(e.to());
        			}
        		}
        	}
        	ArrayList<Integer> ret = new ArrayList<Integer>();
        	Integer currentStep = stopVertex;
        	while(currentStep != null) {
        		Integer prevStep = steps.get(currentStep);
        		if (prevStep != null) {
        			ret.add(prevStep);
        		}
        		currentStep = prevStep;
        	}  
        return ret;	        	
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

    private class Edge implements Comparable {

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
		public int compareTo(Object arg0) {
			if ((int)myEdgeInfo < ((int)((Edge) arg0).info())) {
				return -1;
			}
			else if ((int)myEdgeInfo > ((int)((Edge) arg0).info())) {
				return 1;
			}
			else {
				return 0;
			}
		}
        
        

    }

    public static void main(String[] args) {
        ArrayList<Integer> result;

        Graph g1 = new Graph(6);
        
        g1.addEdge(0, 1, 10);
        g1.addEdge(0, 3, 30);
        g1.addEdge(0, 4, 100);
        g1.addEdge(1, 2, 50);
        g1.addEdge(2, 4, 10);
        g1.addEdge(3, 2, 20);
        g1.addEdge(3, 4, 60);
        g1.addEdge(5, 4, 3);
       
        
        ArrayList<Integer> path = g1.shortestPath(5, 4);
        if (path.isEmpty()) {
        	System.out.print("No path.");
        	return;
        }
        System.out.println("Traversal starting at " + path.get(0));
        for(int i : path) {
        	System.out.println(i);
        }
        
	  
    }

}
