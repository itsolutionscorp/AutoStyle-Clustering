import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.HashMap;
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
        //your code here
    	myAdjLists[v1].add(new Edge(v1,v2, edgeInfo));
    	
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
    	if(myAdjLists[from].contains(new Edge(from, to, null))){
    		return true;
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<?> neighbors(int vertex) {
        // your code here
        return myAdjLists[vertex];
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for(int i = 0; i<myVertexCount; i++){
        	if(myAdjLists[i].contains(new Edge(i, vertex, null))){
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
            //your code here
        	fringe = new Stack<Integer>();
        	fringe.push(start);
        	
        	visited = new HashSet<Integer>();
        }

        public boolean hasNext() {
            //your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
        	if(!visited.contains(fringe.peek())){
            	Integer temp = fringe.pop();
            	visited.add(temp);
            	for(Edge i: myAdjLists[temp]){
            		if(!visited.contains(i.to())){
            			fringe.push(i.to());
            		}
            	}
            	
                return temp;
            	}
            	else{
            		fringe.pop();
            		return next();
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
            result.add(iter.next());
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
    	if(!pathExists(startVertex, stopVertex)){
    		return new ArrayList<Integer>();
    	}
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	ArrayList<Integer> AllNodes = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        Integer temp = startVertex;
        while (iter.hasNext()&& temp != stopVertex) {
        	temp = iter.next();
            AllNodes.add(temp);
        }
       
        for(int i = 0; i<AllNodes.size(); i++){
        	if(temp == startVertex){
        		break;
        	}
        	if(myAdjLists[AllNodes.get(i)].contains(new Edge(AllNodes.get(i), temp, null))){
               
        		result.add(AllNodes.get(i));
        		temp = AllNodes.get(i);
        		
        	}
        }

        return result;
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
        //your code here...
    	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    	HashMap<Integer, Vertex> vertexMap = new HashMap<Integer, Vertex>();
    	
    	for(LinkedList<Edge> edj: myAdjLists){
    		for(Edge edge: edj){
    		Vertex v = new Vertex(edge.myFrom, Integer.MAX_VALUE, null);
    		if(!vertices.contains(v)){
    			vertices.add(v);
    			vertexMap.put(v.itemValue, v);
    		}
    		Vertex vTo = new Vertex(edge.myTo, Integer.MAX_VALUE, null);
    		if(!vertices.contains(vTo)){
    			vertices.add(vTo);
    			vertexMap.put(vTo.itemValue, vTo);
    		}
    		}
    	}
    	Vertex startVtx = vertexMap.get(new Integer(startVertex));
    	startVtx.distance = 0;
    	PriorityQueue<Vertex> vtxQ = new PriorityQueue<Vertex>();
    	for(Vertex vertex: vertices){
    		vtxQ.add(vertex);
    	}
    	
    	while(!vtxQ.isEmpty()){
    		Vertex minItemVertex = vtxQ.poll();
    		for(Edge edge: myAdjLists[minItemVertex.itemValue.intValue()]){
    			if(vertexMap.get(edge.myTo).distance > minItemVertex.distance + (Integer) edge.myEdgeInfo){
    				vtxQ.remove(vertexMap.get(edge.myTo));
    				vertexMap.get(edge.myTo).distance = minItemVertex.distance + (Integer) edge.myEdgeInfo;
    				vertexMap.get(edge.myTo ).previousNode  = minItemVertex;
    				vtxQ.add(vertexMap.get(edge.myTo));
    			}
    		}
    		
    	}
    	Vertex lastVertex = vertexMap.get(Integer.valueOf(endVertex));
    	ArrayList<Integer> rightPathVtx = new ArrayList<Integer>();
    	while(lastVertex != null){
    		rightPathVtx.add(0, lastVertex.itemValue);
    		lastVertex = lastVertex.previousNode;
    		
    	}
    	return rightPathVtx;
    	
    }
    
    private class Vertex implements Comparable<Object>{
    	Integer itemValue;
    	int distance;
    	Vertex previousNode;
    	
    	public Vertex(Integer item, int dist, Vertex prevNode){
    		itemValue = item;
    		distance = dist;
    		previousNode = prevNode;
    		
    	}
    	
    	public int  compareTo(Object vertex){
    		if(this.distance < ((Vertex) vertex).distance){
    			return -1;
    		} else if(this.distance == ((Vertex) vertex).distance){
    			return 0;
    		} else {
    			return 1;
    		}
    		
    	}
    	
    	public boolean equals(Object o){
    		if(itemValue.equals(((Vertex) o).itemValue)){
    			return true;
    			
    		}
    		return false;
    		
    	}
    	
    	
    	
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
        
        Graph g3 = new Graph(3);
        g3.addEdge(0, 1, 1);
        g3.addEdge(0, 2, 4);
        g3.addEdge(1, 2, 1);
        System.out.println(g3.shortestPath(0, 2));
    }

}
