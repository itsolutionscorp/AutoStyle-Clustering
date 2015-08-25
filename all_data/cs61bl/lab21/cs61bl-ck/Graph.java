import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
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
    	Edge e =new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(e);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge e =new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(e);
    	
    	Edge e1 =new Edge(v2, v1, edgeInfo);
    	myAdjLists[v2].add(e1);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	for (Edge e : myAdjLists[from]) {
    		if (e.myTo.equals(to)) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
        // your code here
    	List<Integer> l = new ArrayList<Integer>();
    	for (Edge e : myAdjLists[vertex]) {
    		l.add(e.myTo);
    	}
        return l;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (LinkedList<Edge> l : myAdjLists) {
        	for (Edge e : l) {
        		if (e.myTo.equals(vertex)) {
        			count ++;
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
        	return !fringe.isEmpty();
        }

		public Integer next() {
			// your code here
			if (hasNext()) {
				int from = fringe.pop();
				if (!visited.contains(from)) {
					visited.add(from);
					for (Edge e : myAdjLists[from]) {
						if (!fringe.contains(e.myTo) && !visited.contains(e.myTo)) {
							fringe.push(e.myTo);
						}
					}
					return from;
				} else {
					return next();
				}
			} else {
				return null;
			}
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
        	int v = iter.next();
            result.add(v);
        }
        return result;
    }

    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
        // your code here
        return visitAll(startVertex).contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    
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
    
   
    
    public ArrayList<Integer> visitAll(int startVertex, int end) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
        	int v = iter.next();
        	if (v == end) {
        		result.add(v);
        		break;
        	}
            result.add(v);
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
        private Integer[] inDegree;

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            inDegree = new Integer[myVertexCount];
            for(int i = 0; i < myVertexCount; i++){
            	inDegree[i] = inDegree(i);
            	if(inDegree[i] == 0)
            		fringe.push(i);
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

		public Integer next() {
			Integer temp = null;
			boolean[] visited = new boolean[myVertexCount];
			if (hasNext()) {
				temp = fringe.pop();
				visited[temp] = true;
				for (int i = 0; i < myAdjLists[temp].size(); i++) {
					inDegree[myAdjLists[temp].get(i).myTo]--;
					if (inDegree[myAdjLists[temp].get(i).myTo] == 0 && !visited[myAdjLists[temp].get(i).myTo]) {
						fringe.push(myAdjLists[temp].get(i).myTo);
					}
				}
				return temp;
			} else {
				return null;
			}
		}

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }
    }
       
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
    	double inf = Double.POSITIVE_INFINITY;
    	HashMap<Integer, Integer> currprior = new HashMap<Integer, Integer>();
    	for (Integer i : visitAll(startVertex)) {
    		if (i == startVertex) {
    			heap.insert(i, 0);
    		} else {
    			heap.insert(i, inf);
    		}
    	}
    	ArrayList<Integer> check = new ArrayList<Integer>();
    	HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();
    	Integer temp = startVertex;
		while (!check.contains(endVertex) && !heap.isEmpty()) {
			for (Edge e : myAdjLists[temp]) {
				if (!currprior.containsKey(e.myTo) || currprior.get(e.myTo) > (Integer) e.myEdgeInfo + heap.peek().priority()) {
					heap.changePriority(e.myTo, (Integer) e.myEdgeInfo + heap.peek().priority());
					currprior.put(e.myTo, (int) ((Integer) e.myEdgeInfo + heap.peek().priority()));
					prev.put(e.myTo, e.myFrom);
				}	
			}
    		System.out.println(heap.peek());
    		Integer min = (Integer) heap.removeMin().item();
    		check.add(min);
    		if (!heap.isEmpty()) 
    		temp = (Integer) heap.peek().item();
    	}
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		
		Integer curr = endVertex;
		while (curr != startVertex) {
			toReturn.add(curr);
			curr = prev.get(curr);
		}
		toReturn.add(startVertex);
		Collections.reverse(toReturn);
    	return toReturn;
    }
    
    public Edge getEdge(int curr, int end) {
    	for (Edge e : myAdjLists[curr]) {
    		if (e.myTo.equals(end)) {
    			return e;
    		}
    	}
    	return null;
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
        System.out.println("Traversal starting at 1");
        result = g1.visitAll(1);
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
        g3.addUndirectedEdge(0,2); 
        g3.addUndirectedEdge(0,3); 
        g3.addUndirectedEdge(1,4); 
        g3.addUndirectedEdge(1,5); 
        g3.addUndirectedEdge(2,3); 
        g3.addUndirectedEdge(2,6); 
        g3.addUndirectedEdge(4,5);
        System.out.println("Traversal starting at 0");
        result = g3.visitAll(0);
        Iterator<Integer> iter1;
        iter1 = result.iterator();
        while (iter1.hasNext()) {
            System.out.println(iter1.next() + " ");
        }
        
        System.out.println(g3.pathExists(0, 6));
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 6");
        ArrayList<Integer> result1 = g3.path(0, 6);
        iter = result1.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        
        
        
        
        Graph g4 = new Graph(8);
        g4.addEdge(1, 3);
        g4.addEdge(1, 4);
        g4.addEdge(3, 6);
        g4.addEdge(4, 6);
        g4.addEdge(6, 0);
        g4.addEdge(2, 5);
        g4.addEdge(5, 7);
        g4.addEdge(7, 0);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort");
        result = g4.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + "  *");
        }
        
        
        System.out.println();
        System.out.println();
        Graph g5 = new Graph(5);
        g5.addEdge(0, 1, 10);
        g5.addEdge(0, 4, 5);
        g5.addEdge(1, 2, 1);
        g5.addEdge(2, 3, 4);
        g5.addEdge(3, 2, 6);
        g5.addEdge(4, 3, 2);
        g5.addEdge(4, 2, 9);
        g5.addEdge(3, 0, 7);
        g5.addEdge(4, 1, 3);
        g5.addEdge(1, 4, 2);
        for (Integer i : g5.shortestPath(0, 2)) {
        	System.out.println(i);
        }
        
        
        System.out.println();
        System.out.println();
        Graph g6 = new Graph(5);
        g6.addEdge(0, 1, 10);
        g6.addEdge(0, 4, 100);
        g6.addEdge(0, 3, 30);
        g6.addEdge(1, 2, 50);
        g6.addEdge(2, 4, 10);
        g6.addEdge(3, 2, 20);
        g6.addEdge(3, 4, 60);
        for (Integer i : g6.shortestPath(0, 4)) {
        	System.out.println(i);
        }
        
        
    }

}
