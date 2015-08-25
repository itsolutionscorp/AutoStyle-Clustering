import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;
    static HashMap<Integer, Double> integerCost;
    static HashMap<Integer, LinkedList<Edge>> myPrevs;

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices];
        myStartVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            myAdjLists[k] = new LinkedList<Edge>();
        }
        myVertexCount = numVertices;
        integerCost = new HashMap<Integer, Double>();
        myPrevs = new HashMap<Integer, LinkedList<Edge>>();
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
    	for (int i = 0; i < myAdjLists.length; i++) {
    		if (myAdjLists[i].isEmpty()) {
    			myAdjLists[i] = new LinkedList<Edge>();
    			myAdjLists[i].add(new Edge(v1, v2, edgeInfo));
    			myPrevs.put(v1, null);
    			return;
    		}
    		else if (myAdjLists[i].peek() != null && myAdjLists[i].peek().myFrom.equals(v1)) {
    			myAdjLists[i].add(new Edge(v1, v2, edgeInfo));
//    			myPrevs.put(v1, );
    			return;
    		}
    	}
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	boolean firstCanBreak = false;
    	for (int i = 0; i < myAdjLists.length; i++) {
    		if (myAdjLists[i] == null) {
    			myAdjLists[i] = new LinkedList<Edge>();
    			myAdjLists[i].add(new Edge(v1, v2, edgeInfo));
    			return;
    		}
    		else if (myAdjLists[i].peek().myFrom.equals(v1)) {
    			myAdjLists[i].add(new Edge(v1, v2, edgeInfo));
    			firstCanBreak = true;
    		}
    		if (firstCanBreak == true) {
    			break;
    		}
    	}
    	boolean secondCanBreak = false;
    	for (int i = 0; i < myAdjLists.length; i++) {
    		if (myAdjLists[i] == null) {
    			myAdjLists[i] = new LinkedList<Edge>();
    			myAdjLists[i].add(new Edge(v2, v1, edgeInfo));
    			firstCanBreak = true;
    		}
    		else if (myAdjLists[i].peek().myFrom.equals(v2)) {
    			myAdjLists[i].add(new Edge(v2, v1, edgeInfo));
    			secondCanBreak = true;
    		}
    		if (secondCanBreak == true) {
    			break;
    		}
    	}
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
        for (int i = 0; i < myAdjLists.length; i++) {
        	if (myAdjLists[i] != null) {
        		if (myAdjLists[i].peek().myFrom.equals(from)) {
        			for (Edge e : myAdjLists[i]) {
        				if (e.myTo.equals(to)) {
        					return true;
        				}
        			}
        		}
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
        // your code here
        List<Integer> neighbors = new LinkedList<Integer>();
        for (int i = 0; i < myAdjLists.length; i++) {
        	if (myAdjLists[i].get(0).myFrom.equals(vertex)) {
        		for (Edge e : myAdjLists[i]) {
        			neighbors.add(e.myTo);
        		}
        	}
        }
        return neighbors;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < myAdjLists.length; i++) {
        	for (Edge e : myAdjLists[i]) {
        		if (e.myTo.equals(vertex)) {
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
        private Stack<Integer> fringe = new Stack<Integer>();
        private HashSet<Integer> visited = new HashSet<Integer>();

        public DFSIterator(Integer start) {
            //your code here
        	fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
        	return (!fringe.empty());
        }

        public Integer next() {
            //your code here
        	while (!fringe.isEmpty()) {
        		if (visited.contains(fringe.peek())) {
        			fringe.pop();
        		}
        		else {
        			Integer myVertex = fringe.pop();
        			visited.add(myVertex);
        			for (LinkedList<Edge> edgeList : myAdjLists) {
        				if (!edgeList.isEmpty() && edgeList.get(0).myFrom.equals(myVertex)) {
        					for (int i = 0; i < edgeList.size(); i++) {
        						Integer edgeToPush = edgeList.get(i).myTo;
//        						if (i != edgeList.size() - 1) {
//        							if (edgeToPush < edgeList.get(i+1).myTo) {
//        								edgeToPush = edgeList.get(i+1).myTo;
//        							}
//        						}
//        						if (edgeToPush != null) {
//        							fringe.push(edgeToPush);
//        						}
        						fringe.push(edgeToPush);
        					}
        				}
        			}
        			return myVertex;
        		}
        	}
        	return null;
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
        ArrayList<Integer> potentialStopVertexList = visitAll(startVertex);
        for (Integer v : potentialStopVertexList) {
        	if (v.equals(stopVertex)) {
        		return true;
        	}
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	
        //return new ArrayList<Integer>();
        // you supply the body of this method
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        result.add(startVertex);
        if (startVertex == stopVertex) {
        	return result;
        }

        while (iter.hasNext()) {
        	Integer nextItem = iter.next();
        	if (nextItem.equals(stopVertex)) {
        		result.add(nextItem);
        		return result;
        	}
        	result.add(iter.next());
        }
        return new ArrayList<Integer>();
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	
    	LinkedList<Integer> result = new LinkedList<Integer>();
    	PriorityQueue<Integer> fringe = new PriorityQueue<Integer>(100, new IntegerComparator());
    	HashMap<Integer, Integer> prevSet = new HashMap<Integer,Integer>();
    	integerCost.put(startVertex, 0.0);
    	fringe.add(startVertex);
    	ArrayList<Integer> all = visitAll(startVertex);
    	for(Integer v : all){ //put all in the fringe
    		if (v != null && !v.equals(startVertex)) {
    			integerCost.put(v, Double.POSITIVE_INFINITY);
        		fringe.add(v);
    		}
    	}
    	while (! fringe.isEmpty()){
    		int curr = fringe.poll();
    		for(Edge e : myAdjLists[curr]){
    			if(integerCost.get(curr) + (Integer) e.myEdgeInfo < integerCost.get(e.to())){
    				integerCost.put(e.to(), integerCost.get(curr)+ (Integer) e.myEdgeInfo);
    				fringe.remove(e.to());
    				fringe.add(e.to());
    				prevSet.put(e.to(),curr);
    			}
    		}
    	}
    	int node = endVertex;
    	result.add(node);
    	while( prevSet.containsKey(node)){
    		result.addFirst(prevSet.get(node));
    		node = prevSet.get(node);
    	}
    	ArrayList<Integer> finalResult = new ArrayList<Integer>();
    	for (int i = 0; i < result.size(); i++) {
    		finalResult.add(result.get(i));
    	}
    	return finalResult;
    }
    
//    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
//        //your code here...
//    	ArrayList<Integer> result = new ArrayList<Integer>();
//    	PriorityQueue<Integer> fringe = new PriorityQueue<Integer>(myAdjLists.length, new IntegerComparator());
//    	fringe.add(startVertex);
//    	integerCost.put(startVertex, 0.0);
//    	ArrayList<Integer> allVertices = visitAll(startVertex);
//    	
////    	return allVertices;
//    	for (Integer i : allVertices) {
//    		if (i != null && !i.equals(startVertex)) {
//    			integerCost.put(i, Double.POSITIVE_INFINITY);
//    			fringe.offer(i);
//    		}
//    	}
//    	if (!fringe.contains(endVertex)) {
//    		return result;
//    	}
//    	while(!fringe.isEmpty()) {
//    		Integer item = fringe.poll();
//    		for (Integer i : neighbors(item)) {
//    			if (integerCost.get(item) + (Double) myPrevs.get(item).myEdgeInfo < integerCost.get(myPrevs.get(item).myFrom)) {
//    				Double tempCost = integerCost.get(myPrevs.get(item).myFrom);
//    				tempCost = integerCost.get(item) + (Double) myPrevs.get(item).myEdgeInfo;
//    				result.add(item);
//    				fringe.add(i);
//    			}
//    		}
//    	}
//    	return result;
//    }
//
//    public ArrayList<Integer> topologicalSort() {
//        ArrayList<Integer> result = new ArrayList<Integer>();
//        Iterator<Integer> iter = new TopologicalIterator();
//        while (iter.hasNext()) {
//            result.add(iter.next());
//        }
//        return result;
//    }
    
    static class IntegerComparator implements Comparator<Integer> {
    	public int compare(Integer a, Integer b) {
    		if (integerCost.get(a) < integerCost.get(b)) {
    			return -1;
    		}
    		else if (integerCost.get(a).equals(integerCost.get(b))) {
    			return 0;
    		}
    		else {
    			return 1;
    		}
    	}
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;

        // more instance variables go here
        private Stack<Integer> otherFringe;
        private HashSet<Integer> visited;
        private int[] currentInDegree;

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            otherFringe = new Stack<Integer>();
            visited = new HashSet<Integer>();
            currentInDegree = new int[myAdjLists.length];
            for (int i = 0; i < myAdjLists.length; i++) {
            	currentInDegree[inDegree(myAdjLists[i].get(i).myFrom)] = myAdjLists[i].get(i).myFrom;
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
//            return new Integer(0);
            // you supply the real body of this method
//        	for (int neighbors = 1; neighbors < myAdjLists.length; neighbors++) {
        		for (int i = 0; i < currentInDegree.length; i++) {
//            		if (currentInDegree[i] == neighbors) {
            			fringe.push(currentInDegree[i]);
//            		}
            	}
//        	}
        	
        	while (!fringe.isEmpty()) {
        		otherFringe.push(fringe.pop());
        	}
//        	Integer toVisit = otherFringe.pop();
        	while (visited.contains(otherFringe.peek())) {
        		otherFringe.pop();
        	}
        	return otherFringe.pop();
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
        
        Graph g3 = new Graph(5);
        g3.addEdge(0, 1, 10);
        g3.addEdge(0, 4, 5);
        g3.addEdge(1, 2, 1);
        g3.addEdge(1, 4, 2);
        g3.addEdge(2, 3, 4);
        g3.addEdge(3, 0, 7);
        g3.addEdge(3, 2, 6);
        g3.addEdge(4, 1, 3);
        g3.addEdge(4, 2, 9);
        g3.addEdge(4, 3, 2);
        System.out.println();
        System.out.println();
        System.out.println("Shortest path");
        result = g3.shortestPath(0, 2);
        iter = result.iterator();
        while (iter.hasNext()) {
        	System.out.println(iter.next() + " ");
        }

//        Graph g2 = new Graph(5);
//        g2.addEdge(0, 1);
//        g2.addEdge(0, 2);
//        g2.addEdge(0, 4);
//        g2.addEdge(1, 2);
//        g2.addEdge(2, 3);
//        g2.addEdge(4, 3);
//        System.out.println();
//        System.out.println();
//        System.out.println("Topological sort");
//        result = g2.topologicalSort();
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
    }

}
