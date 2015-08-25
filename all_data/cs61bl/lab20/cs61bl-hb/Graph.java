import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    	
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
    	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        if(myAdjLists[from].contains(to)){
        	return true;
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public ArrayList<Integer> neighbors(int vertex) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        for(Edge myE : myAdjLists[vertex]){
        	neighbors.add(myE.to());
        }
        return neighbors;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for(LinkedList<Edge> myL : myAdjLists){
        	for(Edge myE : myL){
        		if(myE.to() == vertex){
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
            fringe.push(start);
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next(){
        	int temp;
        	temp = fringe.pop();
        	if(visited.contains(temp)){
        		return null;
        	}
        	visited.add(temp);
        	for(Integer myInt : neighbors(temp)){
        		if(!visited.contains(myInt)){
        			fringe.push(myInt);
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

    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
        if(startVertex == stopVertex){
        	return true;
        }
        if(visitAll(startVertex).contains(stopVertex)){
        	return true;
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	ArrayList<Integer> temp1 = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        Stack<Integer> temp = new Stack<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        temp1= this.pathHelper(startVertex, stopVertex, path, iter, temp, visited);
        if(!temp1.isEmpty()){
        	path=new ArrayList<Integer>();
        	path.add(startVertex);
        	for(int i=temp1.size()-1;i>=0;i--){
        		path.add(temp1.get(i));
        	}
        	return path;
        }
        return new ArrayList<Integer>();
    }
    
    public ArrayList<Integer> pathHelper(int startVertex, int stopVertex,ArrayList<Integer> path,
    		Iterator<Integer> iter,Stack<Integer> temp,HashSet<Integer> visited){
    	if(!visitAll(startVertex).contains(stopVertex)){
    		return new ArrayList<Integer>();
    	}
    	path.add(stopVertex);
    	for(Edge e : myAdjLists[startVertex]){
    		if(e.to() == stopVertex){
    			while(!temp.isEmpty()){
    				path.add(temp.pop());
    			}
    			return path;
    		}
    	} 	
        while (iter.hasNext()) {
        	int curr;
        	curr = iter.next();
        	visited.add(curr);
            if(curr == stopVertex){
            	if(!visited.contains(curr)){
            		temp.push(curr);
                	break;
            	}
            }
        }
        for(int i = 1; i< myAdjLists.length; i++){
        	for(Edge e : myAdjLists[visitAll(startVertex).get(visitAll(startVertex).indexOf(stopVertex)-i)]){
            	if(e.to() == stopVertex){
            		return this.pathHelper(startVertex, visitAll(startVertex).get(visitAll(startVertex).indexOf(stopVertex)-i), 
                    		path, iter, temp, visited);
            	}
            }
        }
        
        return null;
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
        private int[] currentIndegree;
        private HashSet<Integer> mySet;
        // more instance variables go here
     
        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            mySet = new HashSet<Integer>();
            int max = 0;
            for(LinkedList<Edge> myL : myAdjLists){
            	for(Edge myE : myL){
            		mySet.add(myE.from());
            		mySet.add(myE.to());
            	}
            }
            for(int i:mySet){
                if(max<i){
                	max = i;
                }
            }
            currentIndegree = new int[max+1];
            for(int i : mySet){
            	currentIndegree[i] = inDegree(i);
            }
            for(int i : mySet){
            	if(currentIndegree[i] == 0){
            		fringe.push(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	int a;
        	Stack<Integer> temp = new Stack<Integer>();
        	//System.out.println(fringe);
        	a = fringe.pop();
        	mySet.remove(a);
        	for(int i : neighbors(a)){
        		currentIndegree[i]--;
        	}
        	while(!fringe.isEmpty()){
        		temp.push(fringe.pop());
        	}
        	for(int i : mySet){
        		if(currentIndegree[i] == 0){
               		fringe.push(i);
               	}
       		}   	
            return a;
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
        public Integer from() {
            return myFrom;
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
