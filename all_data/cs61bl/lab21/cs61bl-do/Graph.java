import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;
    
    private Integer[] minDistance;
    private Integer[] prev;

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
    	Edge e = new Edge(v1, v2, edgeInfo);
    	if(!myAdjLists[v1].contains(e))
    		myAdjLists[v1].add(e);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge e1 = new Edge(v1, v2, edgeInfo);
    	if(!myAdjLists[v1].contains(e1))
    		myAdjLists[v1].add(e1);
    	Edge e2 = new Edge(v2, v1, edgeInfo);
    	if(!myAdjLists[v2].contains(e2))
    		myAdjLists[v2].add(e2);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
        return myAdjLists[from].contains(new Edge(from, to, null));
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	List<Integer> l = new LinkedList<Integer>();
    	for(Edge e: myAdjLists[vertex])
    		l.add(e.myTo);
    	return l;
    }
    
    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
    	for(int i = 0; i < myVertexCount; i++){
    		if(myAdjLists[i].contains(new Edge(i, vertex, null)))
    			count++;
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
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
        	Integer i = fringe.pop();
        	visited.add(i);
        	for(Edge e : myAdjLists[i]) {
        		if(!visited.contains(e.myTo))
        			fringe.push(e.myTo);
        	}
            return i;
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
    	if(startVertex == stopVertex)
    		return true;
    	
        Iterator<Integer> iter = new DFSIterator(startVertex);
        while (iter.hasNext()) {
           if(iter.next() == stopVertex)
        	   return true;
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        // you supply the body of this method
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            Integer i = iter.next();
            result.add(i);
            if(i == stopVertex)
        		break;
        }
        
        int last = stopVertex;
        for(int i = result.size() - 2; i >=0; i--) {
        	if(isAdjacent(result.get(i), last)){
        		last = result.get(i);
        	} else {
        		result.remove(i);
        	}
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
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	
    	minDistance = new Integer[myVertexCount];
    	prev = new Integer[myVertexCount];
    	HashSet<Integer> visited = new HashSet<Integer>();
    	
    	for(int i = 0; i < myVertexCount; i ++)
    		minDistance[i] = Integer.MAX_VALUE;
    	
    	minDistance[startVertex] = 0;
    	EdgeComparator cmp = new EdgeComparator();
    	PriorityQueue<Integer> fringe = new PriorityQueue<Integer>(myVertexCount, cmp);
		
		for(int i = 0; i < myVertexCount; i++)
			fringe.add(i);
		
		while (!fringe.isEmpty()) {
			Integer curr = fringe.poll();
			visited.add(curr);
			List<Integer> l = neighbors(curr);
			for(int i : l) {
				if(visited.contains(i))
					continue;
				
				int newDistance = (Integer) getEdge(curr, i).myEdgeInfo;
				if(minDistance[i] > minDistance[curr] + newDistance) {
					minDistance[i] = minDistance[curr] + newDistance;
					prev[i] = curr;
					fringe.remove(i);
					fringe.add(i);
				}
			}
		}
		
		Stack<Integer> path = new Stack<Integer>();
		
		Integer c = endVertex;
		while(c != startVertex) {
			path.push(c);
			c = prev[c];
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		list.add(startVertex);
		while(!path.isEmpty())
			list.add(path.pop());
		
		return list;
    }
    
    public class EdgeComparator implements Comparator<Integer> {

		public int compare(Integer o1, Integer o2) {
			return (int) (minDistance[o1] - minDistance[o2]);
		}
    }
    
    public Edge getEdge(int from, int to) {
    	for(Edge e : myAdjLists[from])
    		if(e.equals(new Edge(from, to, null)))
    			return e;
    	return null;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;

        // more instance variables go here
        private int[] currentInDegree;
        
        
        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new int[myVertexCount];
            for(int i = 0; i < myVertexCount; i++) {
            	int k = inDegree(i);
            	currentInDegree[i] = k;
            	if(k == 0)
            		fringe.push(i);
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            // you supply the real body of this method
        	int curr = fringe.pop();
        	List<Integer> l = neighbors(curr);
        	for(int i : l) {
        		currentInDegree[i]--;
        		if(currentInDegree[i] == 0)
        			fringe.push(i);
        	}
        	return curr;
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

		public int compareTo(Object o) {
			if(!(o instanceof Edge))
				return -1;
			Edge e = (Edge) o;
			if(e.myFrom.equals(myFrom) && e.myTo.equals(myTo))
				return 0;
			return -1;
		}
		
		public boolean equals(Object o) {
			return compareTo(o) == 0;
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
        g3.addUndirectedEdge(0, 1, 10);
        g3.addUndirectedEdge(0, 2, 11);
        g3.addUndirectedEdge(0, 5, 16);
        g3.addUndirectedEdge(1, 5, 16);
        g3.addUndirectedEdge(2, 5, 6);
        g3.addUndirectedEdge(5, 4, 3);
        g3.addUndirectedEdge(5, 3, 17);
        g3.addUndirectedEdge(3, 6, 3);
        g3.addUndirectedEdge(4, 6, 2);
        
        System.out.println(g3.shortestPath(0,3));
    }
}
