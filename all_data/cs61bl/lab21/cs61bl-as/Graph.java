
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;

    // Initialize a graph with the given number of vertices and no edges.
    @SuppressWarnings("unchecked")
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
        Edge a = new Edge(v1, v2, edgeInfo);
        myAdjLists[v1].add(a);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here, add to edge 
    	Edge a = new Edge(v1, v2, edgeInfo);
    	Edge b = new Edge(v2, v1, edgeInfo);
    	myAdjLists[v1].add(a);
    	myAdjLists[v2].add(b);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        if(from < myAdjLists.length && to <myAdjLists.length){
        	for(int i = 0; i< myAdjLists[from].size(); i++){
        		if(myAdjLists[from].get(i).to() == to){
        			return true;
        		}
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Edge> neighbors(int vertex) {
       if(vertex< myAdjLists.length){
    	  return myAdjLists[vertex]; 
       }
        return null;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
       for (int i = 0; i<myAdjLists.length; i++){
    	   for(int j = 0; j< myAdjLists[i].size(); j++){
    		   if(myAdjLists[i].get(j).to()==vertex){
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
        	if (myAdjLists[start] !=null){
        		for (int i = 0; i < myAdjLists[start].size();i++){
        			int v = myAdjLists[start].get(i).to();
        			fringe.push(v);
        			visited.add(v);
        		}
        	}
        }

        public boolean hasNext() {
            return (!fringe.isEmpty());
        }

        public Integer next() {
            int start = fringe.pop();
            visited.add(start);
            // go to the for loop
            for (int i = 0; i < myAdjLists[start].size();i++){
            	int v = myAdjLists[start].get(i).to();
            	if(!visited.contains(v)){
            		fringe.push(v);
            	}
            }
            return start;
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
		// check to finish the loop
		Iterator<Integer> iter = new DFSIterator(startVertex);
		if (startVertex == stopVertex) {
			return true;
		} else {
			while (iter.hasNext()) {
				if (stopVertex == iter.next()) {
					return true;
				}

			}
		}
		return false;
	}

    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Map<Integer, Integer> steps = new HashMap<Integer, Integer>();
		
		if (this.pathExists(startVertex, stopVertex)) {
			Iterator<Integer> iter = new DFSIterator(startVertex);
			HashSet<Integer> visted = new HashSet<Integer>();
			visted.add(startVertex);	
			
			while (iter.hasNext()) {
				int after = iter.next();
				for (int node : visted) {
					if(isAdjacent(node,after)){
						steps.put(after, node);	
						}
					}
				visted.add(after);				
				if (after == stopVertex) {
					break;
				}
			}
			
			System.out.print(steps.toString());
			Stack <Integer> b = new Stack<Integer>();
			int newVertex = stopVertex;
			while(newVertex != startVertex){
				b.push(steps.get(newVertex));
				newVertex = steps.get(newVertex);
			}
			while(!b.isEmpty()){
				a.add(b.pop());
			}
			a.add(stopVertex);
		}else{
			System.out.print("no path");
		}
		return a;

		// you supply the body of this method

	}
	

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		if (pathExists(startVertex, endVertex)) {
			int tableCol = myAdjLists.length;
			ArrayList<valuePair>[] myTable = new ArrayList[tableCol];
			// initialize the path
			for (int i = 0; i < tableCol; i++) {
				// the first is always infinite value
				myTable[i] = new ArrayList<valuePair>();
				valuePair e = new valuePair(-1, Integer.MAX_VALUE);
				myTable[i].add(e);
			}
			myTable[startVertex].add(new valuePair(startVertex, 0));

			while (lowestCostPath(myTable, visited) != endVertex) {
				int newNode = lowestCostPath(myTable, visited);
				visited.add(newNode);
				for (Edge edge : this.neighbors(newNode)) {
					int distance = (int) edge.myEdgeInfo;
					int from = edge.myFrom;
					int to = edge.myTo;
					// if the pervious distance is not max_value, add the
					// distance
					if (myTable[from].size() == 1) {// have not being visited
													// before
						valuePair e = new valuePair(from, distance);
						myTable[to].add(e);
					} else {
						int pos = myTable[from].size();
						int perDistance = myTable[from].get(pos - 1)
								.getDistance();
						valuePair e = new valuePair(from, distance
								+ perDistance);
						myTable[to].add(e);
					}
				}
			}
			// add everything to a queue;
			Stack<Integer> b = new Stack<Integer>();
			b.push(endVertex);
			int pervious = endVertex;
			while (pervious != startVertex) {
				valuePair e = myTable[pervious].get(myTable[pervious].size() - 1);
				b.push(e.getFrom());
				pervious = e.getFrom();
				
			}

			while (!b.isEmpty()) {
				a.add(b.pop());
			}
		} else {
			System.out.print("no path");
		}
		// a is read from the table

		return a;
	}
	

	
	public static int lowestCostPath(ArrayList<valuePair>[] table, HashSet<Integer>visited){
		int smallest = Integer.MAX_VALUE;
		int nextloc =0;
		for(int i = 0; i <table.length; i++){
			int pos = table[i].size()-1;
			int currentDistance =table[i].get(pos).getDistance();
			//System.out.println("distance"+currentDistance);
			
			if(currentDistance < smallest && !visited.contains(i)){
				smallest = currentDistance;
				nextloc = i;
			}
		}
		//System.out.println("hi" +nextloc);
		return nextloc;
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
    
    public class valuePair {
    	private int from;
    	private int distance;
    	   
    	   public valuePair(int a, int b){
    		   from = a;
    		   distance = b;
    	   }
    	   public int getFrom (){
    		   return from;
    	   }
    	   public int getDistance(){
    		   return distance;
    	   }
    }

    
    public static void main(String[] args) {
        ArrayList<Integer> result;        
        Graph g3 = new Graph(5);
        g3.addEdge(0, 1, 10);
        g3.addEdge(0, 3, 30);
        g3.addEdge(0, 4, 100);
       // g3.addEdge(0, 3, 30);
        g3.addEdge(1, 2, 50);
        g3.addEdge(2, 4, 10);
        g3.addEdge(3, 2, 20);
        g3.addEdge(3, 4, 60);
        System.out.println();
        System.out.println();
        System.out.println("Dijkstra's algorithm");
        result = g3.shortestPath(3, 4);
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    
    }

 
}