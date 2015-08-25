import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Graph implements Iterable<Integer> {

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
	public void setStartVertex(int v) {
		if (v < myVertexCount && v >= 0) {
			myStartVertex = v;
		} else {
			throw new IllegalArgumentException(
					"Cannot set iteration start vertex to " + v + ".");
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
		myAdjLists[v1].add(new Edge(v1, v2, null));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		// your code here
		myAdjLists[v1].add(new Edge(v1, v2, null));
		myAdjLists[v2].add(new Edge(v2, v1, null));
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		for (int i = 0; i < myAdjLists[from].size(); i++) {
			if (myAdjLists[from].get(i).myTo.equals(to))
				return true;
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List<Integer> neighbors(int vertex) {
		List<Integer> result = new ArrayList<Integer>();
		for (int j = 0; j < myAdjLists[vertex].size(); j++) {
			result.add(myAdjLists[vertex].get(j).myTo);
		}
		return result;
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		// add edges connect to this vertex
		for (int i = 0; i < myVertexCount; i++)
			for (int j = 0; j < myAdjLists[i].size(); j++)
				if (myAdjLists[i].get(j).myTo.equals(vertex))
					count++;
		// add edges that this vertex connect to
		//count = count + myAdjLists[vertex].size();
		return count;
	}

	public Iterator<Integer> iterator() {
		return new TopologicalIterator();
	}

	// A class that iterates through the vertices of this graph, starting with a
	// given vertex.
	// Does not necessarily iterate through all vertices in the graph: if the
	// iteration starts
	// at a vertex v, and there is no path from v to a vertex w, then the
	// iteration will not
	// include w
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited = new HashSet<Integer>();

        public DFSIterator(Integer start) {
            //your code here
        	fringe = new Stack();
        	fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
            return (!fringe.isEmpty());
        }

        public Integer next() {
            //your code here
        	if(!hasNext()){
        		throw new IllegalStateException("Nothing is left!");
        	}
        	Integer temp = (Integer) fringe.pop();
	        visited.add(temp);
	        for(int i = 0; i < myAdjLists[temp].size(); i++){
	        	if(!visited.contains(myAdjLists[temp].get(i).myTo)){
	        		fringe.push(myAdjLists[temp].get(i).myTo);
	        	}
	       	}
            return temp;
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

    public boolean pathExists(int startVertex, int stopVertex) {
        // your code here
    	DFSIterator iter = new DFSIterator(startVertex);
    	ArrayList<Integer> visited = new ArrayList<Integer>();
    	while(iter.hasNext()){
    		ArrayList<Integer> temp = visitAll(iter.next());
    		if(temp.contains(stopVertex))
    			return true;
    		else {
    			for(Integer inte : temp){
    				if(!visited.contains(inte)){
    					visited.add(inte);
    					iter.fringe.push(inte);
    				}
    			}
    		}
    	}
        return false;
    }
    
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	if(pathExists(startVertex, stopVertex)){
    		ArrayList<Integer> visited = new ArrayList<Integer>();
    		DFSIterator iter = new DFSIterator(startVertex);
    		boolean flag = true;
    		while(flag && iter.hasNext()){
    			Integer temp = iter.next();
    			for(int i = 0; i < myAdjLists[temp].size(); i++){
	    			if(myAdjLists[temp].get(i).myTo.equals(stopVertex)){
	    				if(!visited.contains(temp))
	    					visited.add(temp);
	    			flag = false;
	    			} else {
	    				if(!visited.contains(temp))
	    					visited.add(temp);
	    			}
    			}
    		}
    		visited.add(stopVertex);
    		return visited;
    		
    	} else 
    		return (new ArrayList<Integer>());
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
        private Integer[] currentInDegree;

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new Integer[myVertexCount];
            // more statements go here
            for(int i = 0; i < myVertexCount; i++){
            	currentInDegree[i] = inDegree(i);
            	if(currentInDegree[i] == 0)
            		fringe.push(i);
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {  
            // you supply the real body of this method
        	Integer temp = null;
        	boolean[] visited = new boolean[myVertexCount];
            if(hasNext()){
            	temp = fringe.pop();
            	visited[temp] = true;
            	//currentInDegree[temp] = -1;
            	for(int i = 0; i < myAdjLists[temp].size(); i++){
            		currentInDegree[myAdjLists[temp].get(i).myTo]--;
            		if(currentInDegree[myAdjLists[temp].get(i).myTo] == 0)
            			if(!visited[myAdjLists[temp].get(i).myTo])
            				fringe.push(myAdjLists[temp].get(i).myTo);
            	}
            }
            return temp;
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
