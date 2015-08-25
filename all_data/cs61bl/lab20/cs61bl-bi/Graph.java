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
    	myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
    	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    	
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	for (int i = 0; i < myAdjLists.length; i++) {
    		if (i < myAdjLists[from].size() && i >= 0 && myAdjLists[from].get(i).to() == to) {
    			return true;
    		}
    	}
    	return false;

    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	ArrayList<Integer> lst = new ArrayList<Integer>();
        for (Edge e: myAdjLists[vertex] ) {
        	lst.add(e.to());
        }
    	return  lst;
    }

    
    
    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (int i = 0; i < myAdjLists.length ; i ++ ) {
        	for (int j = 0; j < myAdjLists[i].size(); j ++) {
        		if (myAdjLists[i].get(j).to() == vertex) {
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
        	visited.add(start);
        	for (int i = 0; i < myAdjLists[start].size(); i++) {
        		fringe.push(new Integer(myAdjLists[start].get(i).to()));
        	}
        	fringe.push(start);
        }
    

        public boolean hasNext() {
            //your code here
        	return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
        	Integer i = fringe.pop();
        	if (!visited.contains(i)) {
        	 	for (int j = 0; j < myAdjLists[i].size(); j ++) {
            		if (!visited.contains(myAdjLists[i].get(j).to())) {
            			fringe.push(new Integer(myAdjLists[i].get(j).to()));	
            		}
            	}
            	visited.add(i);
        	}
            return i;
        }

        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException("vertex removal not implemented");
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
    	ArrayList<Integer> result  = visitAll(startVertex);
    	return result.contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	if (!pathExists(startVertex, stopVertex)) {
    		return new ArrayList<Integer>();
    	}
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        Integer saved = startVertex;
        while (iter.hasNext() && saved != stopVertex) {
            saved = iter.next();
        	result.add(saved);
        }
        
        for (int i = 1; i < result.size();i ++) {
        	ArrayList<Integer> temp = new ArrayList<Integer>();
        	for (Edge e : myAdjLists[result.get(i-1)]) {
        		temp.add(e.to());
        	}
        	if (!temp.contains(result.get(i))) {
        		result.remove(i-1);
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

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private ArrayList<Integer> currentInDegree;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new ArrayList<Integer>();
            for (int i = 0; i < myAdjLists.length; i ++) {	
            	currentInDegree.add(inDegree(i));
            	if (inDegree(i) == 0) {
            		fringe.push(i);
                	currentInDegree.remove(i);
            		currentInDegree.add(i, -1);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {	
            Integer i = fringe.pop();
            for (Edge e: myAdjLists[i]) {
            	int val = currentInDegree.get(e.to());
            	currentInDegree.remove(e.to());
        		currentInDegree.add(e.to() , val-1); 
            	if (currentInDegree.get(e.to()) == 0) {
            		fringe.push(e.to());
            	}
            }
        	return i;
            
        }

        public void remove() {
            throw new UnsupportedOperationException("vertex removal not implemented");
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

        public Integer from() {
        	return myFrom;
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
        System.out.println("In degree " + g1.inDegree(0));
        System.out.println("Is Adjacent 0 to 1" + g1.isAdjacent(0, 1));
        System.out.println("Is Adjacent 0 to 2" + g1.isAdjacent(0, 2));
        System.out.println("Is Adjacent 0 to 3" + g1.isAdjacent(0, 3));
        System.out.println("Is Adjacent 0 to 4" + g1.isAdjacent(0, 4));
        
        result = g1.visitAll(0);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 2");
        System.out.println("In degree " + g1.inDegree(2));
        System.out.println("Is Adjacent 2 to 1" + g1.isAdjacent(2, 1));
        System.out.println("Is Adjacent 2 to 0" + g1.isAdjacent(2, 0));
        System.out.println("Is Adjacent 2 to 3" + g1.isAdjacent(2, 3));
        System.out.println("Is Adjacent 2 to 4" + g1.isAdjacent(2, 4));
        result = g1.visitAll(2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 3");
        System.out.println("In degree " + g1.inDegree(3));
        System.out.println("Is Adjacent 3 to 0" + g1.isAdjacent(3, 0));
        System.out.println("Is Adjacent 3 to 1" + g1.isAdjacent(3, 1));
        System.out.println("Is Adjacent 3 to 2" + g1.isAdjacent(3, 2));
        System.out.println("Is Adjacent 3 to 4" + g1.isAdjacent(3, 4));
        result = g1.visitAll(3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 4");
        System.out.println("In degree " + g1.inDegree(4));
        System.out.println("Is Adjacent 4 to 0" + g1.isAdjacent(4, 0));
        System.out.println("Is Adjacent 4 to 1" + g1.isAdjacent(4, 1));
        System.out.println("Is Adjacent 4 to 2" + g1.isAdjacent(4, 2));
        System.out.println("Is Adjacent 4 to 3" + g1.isAdjacent(4, 3));
        result = g1.visitAll(4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 3");
        System.out.println("PATH EXISTS " + g1.pathExists(0, 3));
        System.out.println("PATH: " + g1.path(0, 3).toString());
        result = g1.path(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        System.out.println("PATH EXISTS " + g1.pathExists(0, 4));
        System.out.println("PATH: " + g1.path(0, 4).toString());
        result = g1.path(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        System.out.println("PATH EXISTS " + g1.pathExists(1, 3));
        System.out.println("PATH: " + g1.path(1, 3).toString());
        result = g1.path(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        System.out.println("PATH EXISTS " + g1.pathExists(1, 4));
        System.out.println("PATH: " + g1.path(1, 4).toString());
        result = g1.path(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        System.out.println("PATH EXISTS " + g1.pathExists(4, 0));
        System.out.println("PATH: " + g1.path(4, 0).toString());
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
      
        System.out.println();
        System.out.println("Graph G3");
     
        
        System.out.println();
        System.out.println("Graph G3");
        Graph g3 = new Graph(4);
        g3.addEdge(1, 2);
        g3.addEdge(0, 2);
        g3.addEdge(0, 3);
        g3.addEdge(3, 1);
        
        System.out.println();
        System.out.println("NEIGBORS 0");
        System.out.println(g3.neighbors(0).toString());
        System.out.println("NEIGBORS 1");
        System.out.println(g3.neighbors(1).toString());
        System.out.println("NEIGBORS 2");
        System.out.println(g3.neighbors(2).toString());
        System.out.println("NEIGBORS 3");
        System.out.println(g3.neighbors(3).toString());
        
        
        System.out.println("Path from 1 to 3");
        System.out.println("PATH EXISTS " + g3.pathExists(1, 3));
        System.out.println("PATH: " + g3.path(1, 3).toString());
        
        System.out.println("Path from 3 to 2");
        System.out.println("PATH EXISTS " + g3.pathExists(3, 2));
        System.out.println("PATH: " + g3.path(3, 2).toString());
      
        
        result = g3.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

}
